package it.unige.pat2fl.req.visitor;

import it.unige.pat2fl.req.expressions.*;

/**
 * Created by Simone Vuotto on 15/10/15.
 */
public interface ExpressionVisitor {

    public void visitBooleanExpression(BooleanExpression exp);

    public void visitCompareExpression(CompareExpression exp);

    public void visitBooleanVariableExpression(BooleanVariableExpression exp);

    public void visitNumberExpression(NumberExpression exp);

    public void visitFloatVariableExpression(FloatVariableExpression exp);
}
