package it.sagelab.models.ltl;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.ltl.elements.BinaryOperator;
import it.sagelab.models.ltl.elements.Formula;
import it.sagelab.models.ltl.elements.UnaryOperator;
import it.sagelab.models.psp.expressions.*;
import it.sagelab.fe.snl2fl.visitor.ContextBasedVisitor;
import it.sagelab.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class LTLExpressionVisitor.
 *
 * @author Simone Vuotto
 */
public class LTLExpressionVisitor extends ContextBasedVisitor<LTLContext> implements ExpressionVisitor {

    /** The formula. */
    private Formula formula;

    /**
     * Instantiates a new LTL expression visitor.
     *
     * @param context the context
     */
    public LTLExpressionVisitor(LTLContext context) {
        super(context);
    }

    /**
     * Gets the formula.
     *
     * @return the formula
     */
    public Formula getFormula() { return formula; }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitBooleanExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.BooleanExpression)
     */
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

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitUnaryExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.UnaryExpression)
     */
    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.getExp().accept(this);
        switch (exp.getOperator()) {
            case NOT:
                this.formula = new UnaryOperator(this.formula, UnaryOperator.Operator.NOT);
                break;
        }

    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitCompareExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.CompareExpression)
     */
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

        formula = getContext().getFormula(varName, threshold, exp.getOperator());
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitBooleanVariableExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.BooleanVariableExpression)
     */
    @Override
    public void visitBooleanVariableExpression(BooleanVariableExpression exp) {
        formula = new Atom(exp.getName());
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitNumberExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.NumberExpression)
     */
    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor#visitFloatVariableExpression(it.sagelab.it.sagelab.fe.snl2fl.req.expressions.FloatVariableExpression)
     */
    @Override
    public void visitFloatVariableExpression(FloatVariableExpression exp) {

    }
}
