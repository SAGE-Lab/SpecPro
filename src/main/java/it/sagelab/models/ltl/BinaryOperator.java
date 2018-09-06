package it.sagelab.models.ltl;

import it.sagelab.fe.ltl.visitor.FormulaVisitor;

/**
 * The Class BinaryOperator.
 *
 * @author Simone Vuotto
 */
public class BinaryOperator extends Formula {

    /**
     * The Enum Operator.
     */
    public enum Operator {
        
        /** The and. */
        AND("&"), 
         /** The or. */
         OR("|"),
         /** The xor. */
         XOR("^"),
         /** The until. */
         UNTIL("U"),
         /** The weak until. */
         WEAK_UNTIL("W"),
         /** The implication. */
         IMPLICATION("->"),
         /** The equivalence. */
         EQUIVALENCE("<->");

        /** The description. */
        private final String description;
        
        /**
         * Instantiates a new operator.
         *
         * @param description the description
         */
        Operator(String description){ this.description = description;}

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return description;
        }

        /**
         * Gets the op.
         *
         * @param description the description
         * @return the op
         */
        public static Operator getOp(String description) {
            for(BinaryOperator.Operator op : BinaryOperator.Operator.values())
                if(op.toString().equals(description))
                    return op;
            return null;
        }
    }

    /** The right formula. */
    private Formula leftFormula, rightFormula;
    
    /** The op. */
    private Operator op;

    /**
     * Instantiates a new binary operator.
     *
     * @param leftFormula the left formula
     * @param rightFormula the right formula
     * @param op the op
     */
    public BinaryOperator(Formula leftFormula, Formula rightFormula, Operator op) {
        this.leftFormula = leftFormula;
        this.rightFormula = rightFormula;
        this.op = op;
    }

    /**
     * Gets the left formula.
     *
     * @return the left formula
     */
    public Formula getLeftFormula() { return  leftFormula; }

    /**
     * Gets the right formula.
     *
     * @return the right formula
     */
    public Formula getRightFormula() { return  rightFormula; }

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
    public String toString() {
        return "("+leftFormula + " " + op + " " + rightFormula +")";
    }

    /* (non-Javadoc)
     * @see Formula#accept(FormulaVisitor)
     */
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitBinaryOperator(this);
    }
}
