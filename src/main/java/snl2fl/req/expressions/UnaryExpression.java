package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * The Class UnaryExpression.
 *
 * @author = Simone Vuotto
 */
public class UnaryExpression extends Expression {

    /** The expression on which the operator apply */
    protected Expression exp;

    /** The operator */
    protected Operator operator;

    /** The enum of available unary operators */
    public enum Operator {

        NOT("not");

        /** The op. */
        private final String op;

        /**
         * Instantiates a new operator.
         *
         * @param op the op
         */
        Operator(String op) { this.op = op; }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString(){
            return op;
        }
    }

    /**
     * Instantiates a new unary expression.
     *
     * @param exp the left exp
     * @param operator the operator
     */
    public UnaryExpression(Expression exp, UnaryExpression.Operator operator) {
        this.exp= exp;
        this.operator = operator;
    }

    public Expression getExp() {
        return exp;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    boolean value() {
        switch (operator) {
            case NOT:
                return ! exp.value();
            default:
                return false;
        }

    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitUnaryExpression(this);
    }

    @Override
    public String toString() {
        return "!(" + exp + ")";
    }

    /**
     * Gets the operator.
     *
     * @param operator the operator
     * @return the operator
     */
    public static UnaryExpression.Operator getOperator(String operator) {
        for(UnaryExpression.Operator op : UnaryExpression.Operator.values())
            if(op.toString().equals(operator))
                return op;
        throw new IllegalArgumentException("UnaryOperator " + operator + " not found");
    }
}
