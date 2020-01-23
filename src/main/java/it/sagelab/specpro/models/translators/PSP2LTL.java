package it.sagelab.specpro.models.translators;

import it.sagelab.specpro.models.ltl.*;
import it.sagelab.specpro.models.psp.expressions.ExpressionVisitor;
import it.sagelab.specpro.models.psp.expressions.*;
import it.sagelab.specpro.fe.ltl.patterns.Pattern;
import it.sagelab.specpro.fe.ltl.patterns.PatternUnifier;
import it.sagelab.specpro.models.psp.Requirement;

import java.util.*;

/**
 * The Class PSP2LTL.
 *
 * @author Simone Vuotto
 */
public class PSP2LTL implements ExpressionVisitor {


    /** The patterns map. */
    private static final Map<String, Pattern> patternMap = Pattern.loadPatterns(Pattern.PATTERNS_FILE);

    private LTLDcSpec spec;

    /** The formula. */
    private Formula formula;

    public PSP2LTL() {

    }

    public LTLSpec translate(List<? extends Requirement> requirements) {
        PatternUnifier patternUnifier = new PatternUnifier();
        spec = new LTLDcSpec(requirements);
        for(Requirement r : requirements) {

                Pattern pattern = patternMap.get(r.key());
                if(pattern == null)
                    throw new RuntimeException("Pattern " + r.key() + " not found!");
                List<Formula> scopeFormulae = parseExpressions(r.getScope().getExpressions());
                List<Formula> bodyFormulae = parseExpressions(r.getExpressions());
                Formula f = patternUnifier.unify(pattern, scopeFormulae, bodyFormulae);
                spec.addRequirement(f, r);

        }

        List<Formula> invariants = getInvariants();
        for(Formula i: invariants) {
            spec.addInvariant(i);
        }

        for(TreeMap<Float, Atom[]> map: spec.getRangeMap().values()) {
            for(Atom[] atoms : map.values()) {
                spec.getSymbolTable().put(atoms[0].getLabel(), atoms[0]);
                spec.getSymbolTable().put(atoms[1].getLabel(), atoms[1]);
            }
        }

        return spec;
    }

    /**
     * Gets the invariants.
     *
     * @return the invariants
     */
    private List<Formula> getInvariants() {
        ArrayList<Formula> invariants = new ArrayList<>();
        for(String varName : spec.getRangeMap().keySet()) {
            TreeMap<Float, Atom[]> rangesToAtoms = spec.getRangeMap().get(varName);
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

        exp.getLeftExp().accept(this);
        exp.getRightExp().accept(this);

        if(exp.getLeftExp() instanceof NumberExpression)
            threshold =  ((NumberExpression)exp.getLeftExp()).floatValue();
        if(exp.getLeftExp() instanceof VariableExpression)
            varName = ((VariableExpression)exp.getLeftExp()).getLabel();
        if(exp.getRightExp() instanceof NumberExpression)
            threshold =  ((NumberExpression)exp.getRightExp()).floatValue();
        if(exp.getRightExp() instanceof VariableExpression)
            varName = ((VariableExpression)exp.getRightExp()).getLabel();
        if(threshold == null)
            throw new IllegalArgumentException("The case with two variables in a comparison expression is not handled.");
        if(varName == null)
            throw new IllegalArgumentException("The case with two constants in a comparison expression is not handled.");

        formula = getFormulaFromConstraint(varName, threshold, exp.getOperator());
    }

    @Override
    public void visitVariableExpression(VariableExpression exp) {
        if(exp.getType() == VariableExpression.Type.BOOLEAN) {
            Atom a = spec.getOrCreateAtom(exp.getLabel());
            formula = a;
            if(exp.isInput()) {
                spec.addInputVariable(a);
            }
            if(exp.isOutput()) {
                spec.addOututVariable(a);
            }
        } else {
            for(Atom[] atoms: spec.getRangeMap().get(exp.getLabel()).values()) {
                if(exp.isInput()) {
                    spec.addNumericInputVariable(exp.getLabel());
                    spec.addInputVariable(atoms[0]);
                    spec.addInputVariable(atoms[1]);
                }
                if(exp.isOutput()) {
                    spec.addNumericOutputVariable(exp.getLabel());
                    spec.addOututVariable(atoms[0]);
                    spec.addOututVariable(atoms[1]);
                }
            }
        }
    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {  }


    /**
     * Gets the ltl formula from the numerical constraint.
     *
     * @param varName the var name
     * @param threshold the threshold
     * @param operator the operator
     * @return the formula
     */
    private Formula getFormulaFromConstraint(String varName, Float threshold, CompareExpression.Operator operator) {
        Formula f = null;
        if (operator == CompareExpression.Operator.EQUAL) {
            f = spec.getEqualAtom(varName, threshold);
        } else if(operator == CompareExpression.Operator.NOT_EQUAL) {
            f = new UnaryOperator(spec.getEqualAtom(varName, threshold), UnaryOperator.Operator.NOT);
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
    private List<Atom> getAtoms(String varName, Float threshold, CompareExpression.Operator operator) {
        TreeMap<Float, Atom[]> treeMap = spec.getRangeMap().get(varName);
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

}
