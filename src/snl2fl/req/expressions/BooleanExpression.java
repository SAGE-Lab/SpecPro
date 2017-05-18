package it.unige.pat2fl.req.expressions;

import it.unige.pat2fl.req.visitor.ExpressionVisitor;

/**
 * Created by Simone Vuotto on 03/09/15.
 */
public class BooleanExpression extends Expression {
    protected Expression leftExp, rightExp;
    protected Operator operator;

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitBooleanExpression(this);
    }

    public enum Operator {
        AND("and"), OR("or"), XOR("xor");

        private final String op;
        Operator(String op) { this.op = op; }

        @Override
        public String toString(){
            return op;
        }
    };

    public BooleanExpression(Expression leftExp, Expression rightExp, Operator operator) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }

    public Expression getLeftExp(){
        return leftExp;
    }

    public Expression getRightExp(){
        return rightExp;
    }

    public Operator getOperator() {
        return operator;
    }

    boolean value() {
        switch (operator) {
            case AND:
                return leftExp.value() && rightExp.value();
            case OR:
                return leftExp.value() || rightExp.value();
            case XOR:
                return leftExp.value() ^  rightExp.value();
            default:
                return false;
        }
    }

    public static Operator getOperator(String operator) {
        for(Operator op : Operator.values())
            if(op.toString().equals(operator))
                return op;
        throw new IllegalArgumentException("BinaryOperator " + operator + " not found");
    }

    @Override
    public String toString(){
        String op = null;
        switch (operator) {
            case AND:
                op = "&&";
                break;
            case OR:
                op = "||";
                break;
            case XOR:
                op = "^";
                break;
        }
        return "(" + leftExp + ") " + op + " (" + rightExp + ")";
    }

}
