package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;

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
        NOT("!"), 
        /** The globally. */
        GLOBALLY("[]"), 
        /** The eventually. */
        EVENTUALLY("<>"), 
        /** The next. */
        NEXT("o");

        /** The description. */
        private final String description;

        /**
         * Instantiates a new operator.
         *
         * @param description the description
         */
        Operator(String description){ this.description = description; }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() { return description; }

        /**
         * Gets the op.
         *
         * @param description the description
         * @return the op
         */
        public static Operator getOp(String description) {
            for(UnaryOperator.Operator op: UnaryOperator.Operator.values())
                if(op.toString().equals(description))
                    return op;
            return null;
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
