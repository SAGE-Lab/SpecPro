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
    void visitBooleanExpression(BooleanExpression exp);

    /**
     * Visit unary expression.
     *
     * @param exp the exp
     */
    void visitUnaryExpression(UnaryExpression exp);

    /**
     * Visit compare expression.
     *
     * @param exp the exp
     */
    void visitCompareExpression(CompareExpression exp);

    /**
     * Visit boolean variable expression.
     *
     * @param exp the exp
     */
    void visitBooleanVariableExpression(BooleanVariableExpression exp);

    /**
     * Visit number expression.
     *
     * @param exp the exp
     */
    void visitNumberExpression(NumberExpression exp);

    /**
     * Visit float variable expression.
     *
     * @param exp the exp
     */
    void visitFloatVariableExpression(FloatVariableExpression exp);
}
