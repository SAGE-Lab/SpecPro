package it.sagelab.specpro.models.psp.expressions;

/**
 * The Class CompareExpression.
 *
 * @author Simone Vuotto
 * creation date  =  03/09/15.
 */
public class CompareExpression extends Expression {
    
    /** The right exp. */
    protected final Expression leftExp, rightExp;
    
    /** The operator. */
    protected final Operator operator;

    /**
     * Instantiates a new compare expression.
     *
     * @param leftExp the left exp
     * @param rightExp the right exp
     * @param operator the operator
     */
    public CompareExpression(Expression leftExp, Expression rightExp, Operator operator) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.operator = operator;
    }

    /**
     * The Enum Operator.
     */
    public enum Operator {
        
        /** The equal. */
        EQUAL("="), 
        /** The greater. */
        GREATER(">"), 
        /** The greater equal. */
        GREATER_EQUAL(">="), 
        /** The lower. */
        LOWER("<"), 
        /** The lower equal. */
        LOWER_EQUAL("<="), 
        /** The not equal. */
        NOT_EQUAL("!=");

        /** The op. */
        private final String op;
        
        /**
         * Instantiates a new operator.
         *
         * @param op the op
         */
        Operator(String op){ this.op = op; }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString(){
            return op;
        }

    }

    /**
     * Gets the left exp.
     *
     * @return the left exp
     */
    public Expression getLeftExp() {
        return leftExp;
    }

    /**
     * Gets the right exp.
     *
     * @return the right exp
     */
    public Expression getRightExp() {
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

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitCompareExpression(this);
    }

    /**
     * Gets the operator.
     *
     * @param operator the operator
     * @return the operator
     */
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
