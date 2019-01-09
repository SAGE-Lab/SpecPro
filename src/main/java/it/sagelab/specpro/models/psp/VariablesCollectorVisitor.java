package it.sagelab.specpro.models.psp;


import it.sagelab.specpro.models.psp.expressions.*;

import java.util.HashSet;
import java.util.Set;

public class VariablesCollectorVisitor implements ExpressionVisitor {

    private final HashSet<VariableExpression> variables;

    public VariablesCollectorVisitor() {
        variables = new HashSet<>();
    }

    public Set<VariableExpression> getVariables() {
        return variables;
    }

    @Override
    public void visitBooleanExpression(BooleanExpression exp) {
        exp.getLeftExp().accept(this);
        exp.getRightExp().accept(this);
    }

    @Override
    public void visitUnaryExpression(UnaryExpression exp) {
        exp.getExp().accept(this);
    }

    @Override
    public void visitCompareExpression(CompareExpression exp) {
        exp.getLeftExp().accept(this);
        exp.getRightExp().accept(this);
    }

    @Override
    public void visitNumberExpression(NumberExpression exp) {

    }

    @Override
    public void visitVariableExpression(VariableExpression exp) {
        variables.add(exp);
    }
}
