package snl2fl.req.visitor;

import snl2fl.req.expressions.*;
/**
 * @author Simone Vuotto
 * creation date 15/10/15.
 */
public interface ExpressionVisitor {

    public void visitBooleanExpression(BooleanExpression exp);

    public void visitCompareExpression(CompareExpression exp);

    public void visitBooleanVariableExpression(BooleanVariableExpression exp);

    public void visitNumberExpression(NumberExpression exp);

    public void visitFloatVariableExpression(FloatVariableExpression exp);
}
