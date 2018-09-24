package it.sagelab.specpro.models.translators;

import it.sagelab.specpro.fe.snl2fl.Snl2FlException;
import it.sagelab.specpro.fe.snl2fl.visitor.ExpressionVisitor;
import it.sagelab.specpro.models.psp.expressions.*;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.fe.ltl.patterns.Pattern;
import it.sagelab.specpro.fe.ltl.patterns.PatternUnifier;
import it.sagelab.specpro.fe.snl2fl.parser.RequirementsBuilder;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.qualitative.QualitativeRequirement;

import java.io.IOException;
import java.util.*;

/**
 * The Class PSP2LTL.
 *
 * @author Simone Vuotto
 */
public class PSP2LTL implements ExpressionVisitor {

    /** The list of qualitative requirements. */
    private List<QualitativeRequirement> requirements;

    /** The pattern map. */
    private Map<String, Pattern> patternMap;

    /** The range map. */
    private Map<String, TreeMap<Float, Atom[]>> rangeMap;

    /** The formula. */
    private Formula formula;

    /** Boolean Vars */
    private Map<String, Atom> booleanAtoms;

    public PSP2LTL() {
        this.requirements = new ArrayList<>();
    }

    public Map<String, TreeMap<Float, Atom[]>> getRangeMap() {
        return rangeMap;
    }

    public Map<String, Atom> getBooleanAtoms() {
        return booleanAtoms;
    }

    /**
     * Set the context for translation
     *
     * @param builder the requirement builder containing the input parsed
     */
    public void setContext(RequirementsBuilder builder) {

        this.requirements = new ArrayList<>();
        this.booleanAtoms = new HashMap<>();
        for(Requirement r : builder.getContext().getRequirementList()) {
            if (r instanceof QualitativeRequirement)
                this.requirements.add((QualitativeRequirement)r);
            else
                System.err.println("Requirement " + requirements.indexOf(r) + " is not a qualitative requirement, it is skipped.");
        }

        rangeMap = computeRangeMap(requirements);
    }

    /**
     * Translate.
     *
     * @return the list
     */
    public List<Formula> translate() throws IOException {
        if(patternMap == null)
            this.patternMap = Pattern.loadPatterns(Pattern.PATTERNS_FILE);
        PatternUnifier patternUnifier = new PatternUnifier();
        ArrayList<Formula> formulae = new ArrayList<>();
        for(QualitativeRequirement r : requirements) {

            try {
                Pattern pattern = patternMap.get(r.key());
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
        for(String varName : rangeMap.keySet()) {
            TreeMap<Float, Atom[]> rangesToAtoms = rangeMap.get(varName);
            // With LOWER_EQUAL and the last key, all the Atoms associated with the variables are returned
            List<Atom> variables = getAtoms(varName,rangesToAtoms.lastKey(),CompareExpression.Operator.LOWER_EQUAL);
            for (int i = 0; i < variables.size()-1; i++) {
            	for (int j = i+1; j < variables.size(); j++) {
            		invariants.add(new BinaryOperator(new UnaryOperator(variables.get(i), UnaryOperator.Operator.NOT),
            				                          new UnaryOperator(variables.get(j), UnaryOperator.Operator.NOT),
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
            e.accept(this);
            formulae.add(formula);
        }
        return formulae;
    }

    /*********************************************
     *  Expression Visitor Methods
     *********************************************/

    @Override
    public void visitBooleanExpression(BooleanExpression exp) {
        exp.getLeftExp().accept(this);
        Formula a = formula;
        exp.getRightExp().accept(this);
        Formula b = formula;
        Formula formula = null;
        switch (exp.getOperator()) {
            case AND:
                formula = new BinaryOperator(a, b, BinaryOperator.Operator.AND);
                break;
            case OR:
                formula = new BinaryOperator(a, b, BinaryOperator.Operator.OR);
                break;
            case XOR:
                formula = new BinaryOperator(a, b, BinaryOperator.Operator.XOR);
                break;
        }
        this.formula = formula;
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.getExp().accept(this);
        switch (exp.getOperator()) {
            case NOT:
                this.formula = new UnaryOperator(this.formula, UnaryOperator.Operator.NOT);
                break;
        }

    }

    @Override
    public void visitCompareExpression(CompareExpression exp) {
        Float threshold = null;
        String varName = null;
        if(exp.getLeftExp() instanceof NumberExpression)
            threshold =  ((NumberExpression)exp.getLeftExp()).floatValue();
        if(exp.getLeftExp() instanceof FloatVariableExpression)
            varName = ((FloatVariableExpression)exp.getLeftExp()).getName();
        if(exp.getRightExp() instanceof NumberExpression)
            threshold =  ((NumberExpression)exp.getRightExp()).floatValue();
        if(exp.getRightExp() instanceof FloatVariableExpression)
            varName = ((FloatVariableExpression)exp.getRightExp()).getName();
        if(threshold == null)
            throw new IllegalArgumentException("The case with two variables in a comparison expression is not handled.");
        if(varName == null)
            throw new IllegalArgumentException("The case with two constants in a comparison expression is not handled.");

        formula = getFormulaFromConstraint(varName, threshold, exp.getOperator());
    }

    @Override
    public void visitBooleanVariableExpression(BooleanVariableExpression exp) {
        booleanAtoms.putIfAbsent(exp.getName(), new Atom(exp.getName()));
        formula = booleanAtoms.get(exp.getName());
    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {  }

    @Override
    public void visitFloatVariableExpression(FloatVariableExpression exp) {  }

    /*********************************************
     *  Util Methods
     *********************************************/

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

    /**
     * Gets the ltl formula from the numerical constraint.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @param operator the operator
     * @return the formula
     */
    public Formula getFormulaFromConstraint(String varName, Float threshold, CompareExpression.Operator operator) {
        Formula f = null;
        if (operator == CompareExpression.Operator.EQUAL) {
            f = getEqualAtom(varName, threshold);
        } else {
            List<Atom> variables = getAtoms(varName,threshold,operator);
            for (Atom a : variables) {
                if (f == null) {
                    if ((operator == CompareExpression.Operator.GREATER) ||
                            (operator == CompareExpression.Operator.GREATER_EQUAL)) {
                        f = new UnaryOperator(a, UnaryOperator.Operator.NOT);
                    } else {
                        f = a;
                    }
                } else {
                    if ((operator == CompareExpression.Operator.GREATER) ||
                            (operator == CompareExpression.Operator.GREATER_EQUAL)) {
                        f = new BinaryOperator(f, new UnaryOperator(a, UnaryOperator.Operator.NOT),
                                BinaryOperator.Operator.AND);
                    } else {
                        // TODO: thrown exception if operator is not LOWER or LOWER_EQUAL
                        f = new BinaryOperator(f, a, BinaryOperator.Operator.OR);
                    }
                }
            }
        }
        return f;
    }

    /**
     * Gets the set of the Atoms of a given compare expression.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @param operator the operator
     * @return the set of booleanAtoms in the compare expression formula.
     */
    public List<Atom> getAtoms(String varName, Float threshold, CompareExpression.Operator operator) {
        TreeMap<Float, Atom[]> treeMap = rangeMap.get(varName);
        if(treeMap == null) {
            throw new RuntimeException("Variable " + varName + " not found");
        }

        NavigableMap<Float,Atom[]> submap = treeMap.headMap(threshold, true);
        Collection<Atom[]> c = submap.values();
        //obtain an Iterator for Collection
        Iterator<Atom[]> itr = c.iterator();
        //iterate through TreeMap values iterator
        ArrayList<Atom> vars = new ArrayList<>();
        while(itr.hasNext()) {
            Atom[] a = itr.next();
            vars.add(a[0]);
            if (itr.hasNext() ||
                    (operator == CompareExpression.Operator.GREATER) ||
                    (operator == CompareExpression.Operator.LOWER_EQUAL)) {
                vars.add(a[1]);
            }
        }
        return vars;
    }
    /**
     * Gets the equal atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the equal atom
     */
    public Atom getEqualAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[1];
    }

    /**
     * Gets the lower atom.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @return the lower atom
     */
    public Atom getLowerAtom(String varName, Float threshold) {
        return rangeMap.get(varName).get(threshold)[0];
    }
}
