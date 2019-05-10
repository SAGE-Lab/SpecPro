package it.sagelab.specpro.models.ltl;

/**
 * The Class UnaryOperator.
 *
 * @author Simone Vuotto
 */
public class UnaryOperator extends Formula {

    /**
     * The Enum Operator.
     */
    public enum Operator {
        
        /** The not. */
        NOT("!", "~"),
        /** The globally. */
        GLOBALLY("[]", "G"),
        /** The eventually. */
        EVENTUALLY("<>", "F"),
        /** The next. */
        NEXT("o", "X");

        /** The description. */
        private final String[] symbols;

        /**
         * Instantiates a new operator.
         *
         * @param symbols the symbols associated with the operator
         */
        Operator(String... symbols){ this.symbols = symbols; }

        /**
         * Check if the operator contains the given symbol.
         * @param symbol The symbol to check.
         * @return True if the symbols is associated with the operator.
         */
        public boolean contains(String symbol) {
            for(String s: symbols) {
                if(s.equals(symbol))
                    return true;
            }
            return false;
        }

        /**
         * String representation of the operator
         * @return One of the symbols representing the operator
         */
        @Override
        public String toString() { return symbols[0]; }

        /**
         * Gets the op.
         *
         * @param symbol the description
         * @return the op
         */
        public static Operator getOp(String symbol) {
            for(UnaryOperator.Operator op: UnaryOperator.Operator.values())
                if(op.contains(symbol))
                    return op;
            throw new RuntimeException("Operator '" + symbol + "' not found!");
        }

    }

    /** The child. */
    private final Formula child;
    
    /** The op. */
    private final Operator op;

    /**
     * Instantiates a new unary operator.
     *
     * @param child the child
     * @param op the op
     */
    public UnaryOperator(Formula child, Operator op) {
        this.child = child;
        this.op = op;
    }

    /**
     * Gets the child.
     *
     * @return the child
     */
    public Formula getChild() { return  child; }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public Operator getOperator() { return op; }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return op + "(" + child + ")";
    }


    /* (non-Javadoc)
     * @see Formula#accept(FormulaVisitor)
     */
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitUnaryOperator(this);
    }

}
