package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * The Class BooleanExpression.
 *
 * @author = Simone Vuotto
 */
public class BooleanExpression extends Expression {
    
    /** The right exp. */
    protected Expression leftExp, rightExp;
    
    /** The operator. */
    protected Operator operator;

    /* (non-Javadoc)
     * @see snl2fl.req.expressions.Expression#accept(snl2fl.req.visitor.ExpressionVisitor)
     */
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitBooleanExpression(this);
    }

    /**
     * The Enum Operator.
     */
    public enum Operator {
        
        /** The and. */
    	AND("and"), 
    	/** The or. */
    	OR("or"), 
    	/** The xor. */
    	XOR("xor");

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
     * Instantiates a new boolean expression.
     *
     * @param leftExp the left exp
     * @param rightExp the right exp
     * @param operator the operator
     */
    public BooleanExpression(Expression leftExp, Expression rightExp, Operator operator) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }

    /**
     * Gets the left exp.
     *
     * @return the left exp
     */
    public Expression getLeftExp(){
        return leftExp;
    }

    /**
     * Gets the right exp.
     *
     * @return the right exp
     */
    public Expression getRightExp(){
        return rightExp;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }

    /* (non-Javadoc)
     * @see snl2fl.req.expressions.Expression#value()
     */
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

    /**
     * Gets the operator.
     *
     * @param operator the operator
     * @return the operator
     */
    public static Operator getOperator(String operator) {
        for(Operator op : Operator.values())
            if(op.toString().equals(operator))
                return op;
        throw new IllegalArgumentException("BinaryOperator " + operator + " not found");
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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
