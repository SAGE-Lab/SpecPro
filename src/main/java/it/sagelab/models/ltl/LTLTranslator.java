package it.sagelab.models.ltl;

import it.sagelab.fe.snl2fl.Snl2FlException;
import it.sagelab.models.psp.expressions.CompareExpression.Operator;

import org.json.JSONException;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.ltl.elements.BinaryOperator;
import it.sagelab.models.ltl.elements.UnaryOperator;
import it.sagelab.models.ltl.elements.Formula;
import it.sagelab.fe.ltl.patterns.Pattern;
import it.sagelab.fe.ltl.patterns.PatternUnifier;
import it.sagelab.models.psp.expressions.Expression;
import it.sagelab.models.psp.expressions.VariableExpression;
import it.sagelab.fe.snl2fl.parser.RequirementsBuilder;
import it.sagelab.models.psp.Requirement;
import it.sagelab.models.psp.qualitative.QualitativeRequirement;

import java.io.IOException;
import java.util.*;

/**
 * The Class LTLTranslator.
 *
 * @author Simone Vuotto
 */
public class LTLTranslator {

    /** The psp. */
    private List<QualitativeRequirement> requirements;
    
    /** The context. */
    private LTLContext context;
    
    /** The expression visitor. */
    private LTLExpressionVisitor expressionVisitor;

    /**
     * Instantiates a new LTL translator.
     *
     * @param requirements the psp
     * @param context the context
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws JSONException the JSON exception
     */
    public LTLTranslator(List<QualitativeRequirement> requirements, LTLContext context) throws JSONException {
        this.requirements = requirements;
        this.context = context;
        expressionVisitor = new LTLExpressionVisitor(context);
    }

    public LTLTranslator() {
        this.requirements = new ArrayList<>();
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public LTLContext getContext() {
        return context;
    }


    /**
     * Set the context for translation
     *
     * @param builder the requirement builder containing the input parsed
     * @throws IOException
     */
    public void setContext(RequirementsBuilder builder) throws IOException {
        List<Requirement> requirements = builder.getRequirementList();
        Map<String, VariableExpression> symbolTable = builder.getSymbolTable();

        this.requirements = new ArrayList<>();
        for(Requirement r : requirements) {
            if (r instanceof QualitativeRequirement)
                this.requirements.add((QualitativeRequirement)r);
            else
                System.err.println("Requirement "+requirements.indexOf(r)+" is not a qualitative requirement, it is skipped.");
        }


        this.context = new LTLContext(symbolTable, LTLTranslator.computeRangeMap(this.requirements),
                Pattern.loadPatterns(Pattern.PATTERNS_FILE));
        expressionVisitor = new LTLExpressionVisitor(context);
    }

    /**
     * Translate.
     *
     * @return the list
     */
    public List<Formula> translate() {
        PatternUnifier patternUnifier = new PatternUnifier();
        ArrayList<Formula> formulae = new ArrayList<>();
        for(QualitativeRequirement r : requirements) {

            try {
                Pattern pattern = context.getPattern(r.key());
                if(pattern == null)
                    throw new RuntimeException("Pattern " + r.key() + " not found!");
                List<Formula> scopeFormulae = parseExpressions(r.getScope().getExpressions());
                List<Formula> bodyFormulae = parseExpressions(r.getExpressions());
                formulae.add(patternUnifier.unify(pattern, scopeFormulae, bodyFormulae));

            } catch (RuntimeException e) {
                throw new Snl2FlException("Requirement " + requirements.indexOf(r) + ": " + e.getMessage());
            }
        }
        return formulae;
    }

    /**
     * Gets the invariants.
     *
     * @return the invariants
     */
    public List<Formula> getInvariants() {
        ArrayList<Formula> invariants = new ArrayList<>();
        Map<String, TreeMap<Float, Atom[]>> rangeMap = context.getRangeMap();
        for(String varName : rangeMap.keySet()) {
            TreeMap<Float, Atom[]> rangesToAtoms = rangeMap.get(varName);
            // With LOWER_EQUAL and the last key, all the Atoms associated with the variables are returned
            Atom[] variables = context.getAtoms(varName,rangesToAtoms.lastKey(),Operator.LOWER_EQUAL);
            for (int i = 0; i < variables.length-1; i++) {
            	for (int j = i+1; j < variables.length; j++) {
            		invariants.add(new BinaryOperator(new UnaryOperator(variables[i], UnaryOperator.Operator.NOT),
            				                          new UnaryOperator(variables[j], UnaryOperator.Operator.NOT),
            				                          BinaryOperator.Operator.OR));
            	}
            }
        }
        return invariants;
    }

    /**
     * Parses the expressions.
     *
     * @param expressions the expressions
     * @return the list
     */
    private List<Formula> parseExpressions(List<Expression> expressions) {
        ArrayList<Formula> formulae = new ArrayList<>(expressions.size());
        for(Expression e : expressions) {
            e.accept(expressionVisitor);
            formulae.add(expressionVisitor.getFormula());
        }
        return formulae;
    }

    /**
     * Compute range map.
     *
     * @param requirements the psp
     * @return the map
     */
    public static Map<String, TreeMap<Float, Atom[]>> computeRangeMap(List<QualitativeRequirement> requirements){
        RangeMapVisitor rangeMapVisitor = new RangeMapVisitor();
        for(QualitativeRequirement r : requirements) {
            for (Expression e : r.getScope().getExpressions())
                e.accept(rangeMapVisitor);
            for (Expression e : r.getExpressions())
                e.accept(rangeMapVisitor);
        }
        return rangeMapVisitor.getRangeMap();
    }

}
