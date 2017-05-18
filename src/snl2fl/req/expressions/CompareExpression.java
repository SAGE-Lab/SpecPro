package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * @author Simone Vuotto
 * creation date  =  03/09/15.
 */
public class CompareExpression extends Expression {
    protected final Expression leftExp, rightExp;
    protected final Operator operator;

    public CompareExpression(Expression leftExp, Expression rightExp, Operator operator) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }

    public enum Operator {
        EQUAL("="), GREATER(">"), GREATER_EQUAL(">="), LOWER("<"), LOWER_EQUAL("<="), NOT_EQUAL("!=");

        private final String op;
        Operator(String op){ this.op = op; }

        @Override
        public String toString(){
            return op;
        }

    };

    public Expression getLeftExp() {
        return leftExp;
    }

    public Expression getRightExp() {
        return rightExp;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    boolean value() {
        return false;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitCompareExpression(this);
    }

    public static Operator getOperator(String operator){
        for(Operator op : Operator.values())
            if(op.toString().equals(operator))
                return op;
            throw new IllegalArgumentException("BinaryOperator " + operator + " not found");
    }

    @Override
    public String toString() {
        return leftExp + " " +operator + " " +rightExp;
    }

}
