package snl2fl.ltl;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.Formula;
import snl2fl.req.expressions.*;
import snl2fl.req.visitor.ContextBasedVisitor;

/**
 * @author Simone Vuotto
 */
public class LTLExpressionVisitor extends ContextBasedVisitor<LTLContext> implements snl2fl.req.visitor.ExpressionVisitor {

    private Formula formula;

    public LTLExpressionVisitor(LTLContext context) {
        super(context);
    }

    public Formula getFormula() { return formula; }

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
        if(threshold == null || varName == null)
            return; // Not handled the case of two constants or two variables.
        formula = getContext().getFormula(varName, threshold, exp.getOperator());
    }

    @Override
    public void visitBooleanVariableExpression(BooleanVariableExpression exp) {
        formula = new Atom(exp.getName());
    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    @Override
    public void visitFloatVariableExpression(FloatVariableExpression exp) {

    }
}
