package snl2fl.req.visitor;

import snl2fl.req.expressions.*;

/**
 * The Interface ExpressionVisitor.
 *
 * @author Simone Vuotto
 * creation date 15/10/15.
 */
public interface ExpressionVisitor {

    /**
     * Visit boolean expression.
     *
     * @param exp the exp
     */
    public void visitBooleanExpression(BooleanExpression exp);

    /**
     * Visit compare expression.
     *
     * @param exp the exp
     */
    public void visitCompareExpression(CompareExpression exp);

    /**
     * Visit boolean variable expression.
     *
     * @param exp the exp
     */
    public void visitBooleanVariableExpression(BooleanVariableExpression exp);

    /**
     * Visit number expression.
     *
     * @param exp the exp
     */
    public void visitNumberExpression(NumberExpression exp);

    /**
     * Visit float variable expression.
     *
     * @param exp the exp
     */
    public void visitFloatVariableExpression(FloatVariableExpression exp);
}
