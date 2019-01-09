package it.sagelab.specpro.models.psp.expressions;

import it.sagelab.specpro.models.psp.expressions.*;

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
     * Visit number expression.
     *
     * @param exp the exp
     */
    void visitNumberExpression(NumberExpression exp);

    /**
     * Visit variable expression.
     *
     * @param exp the exp
     */
    void visitVariableExpression(VariableExpression exp);
}
