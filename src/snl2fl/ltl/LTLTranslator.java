package snl2fl.ltl;

import org.json.JSONException;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.Formula;
import snl2fl.fl.patterns.Pattern;
import snl2fl.fl.patterns.PatternUnifier;
import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.qualitative.QualitativeRequirement;

import java.io.IOException;
import java.util.*;

/**
 * @author Simone Vuotto
 */
public class LTLTranslator {

    private List<QualitativeRequirement> requirements;
    private LTLContext context;
    private LTLExpressionVisitor expressionVisitor;

    public LTLTranslator(List<QualitativeRequirement> requirements, LTLContext context) throws IOException, JSONException {
        this.requirements = requirements;
        this.context = context;
        expressionVisitor = new LTLExpressionVisitor(context);
    }

    public List<Formula> translate() {
        PatternUnifier patternUnifier = new PatternUnifier();
        ArrayList<Formula> formulae = new ArrayList<>();
        for(QualitativeRequirement r : requirements) {
            Pattern pattern = context.getPattern(r.key());
            if(pattern == null)
                throw new RuntimeException("Pattern "+ r.key()+" not found!");
            List<Formula> scopeFormulae = parseExpressions(r.getScope().getExpressions());
            List<Formula> bodyFormulae = parseExpressions(r.getExpressions());
            formulae.add(patternUnifier.unify(pattern, scopeFormulae, bodyFormulae));
        }
        return formulae;
    }

    public List<Formula> getInvariants() {
        ArrayList<Formula> invariants = new ArrayList<>();
        Map<String, TreeMap<Float, Atom[]>> rangeMap = context.getRangeMap();
        for(String varName : rangeMap.keySet()) {
            Atom[] prevA = null;
            TreeMap<Float, Atom[]> rangesToAtoms = rangeMap.get(varName);
            for(Atom[] a : rangesToAtoms.values()) {
                invariants.add(new BinaryOperator(a[0], a[1], BinaryOperator.Operator.XOR));
                if(prevA != null) {
                    Formula f1 = new BinaryOperator(prevA[0], prevA[1], BinaryOperator.Operator.OR);
                    Formula f2 = new BinaryOperator(f1, a[0], BinaryOperator.Operator.IMPLICATION);
                    invariants.add(f2);
                }
                prevA = a;
            }
        }
        return invariants;
    }

    private List<Formula> parseExpressions(List<Expression> expressions) {
        ArrayList<Formula> formulae = new ArrayList<>(expressions.size());
        for(Expression e : expressions) {
            e.accept(expressionVisitor);
            formulae.add(expressionVisitor.getFormula());
        }
        return formulae;
    }

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

    public LTLContext getContext() {
        return context;
    }

}
