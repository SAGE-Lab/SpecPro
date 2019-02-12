package it.sagelab.specpro.models.ltl;

import java.util.List;

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

    @Override
    public String toString() {
        return "("+leftFormula + " " + op + " " + rightFormula +")";
    }

    /**
     * Visitor accept method
     */
    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitBinaryOperator(this);
    }

    /**
     * Compute the conjunctive formula of the formulas given as parameter
     * @param formulaList the list of formulas to be put in and
     * @return The conjunctive formula
     */
    public static Formula conjunctiveFormula(List<Formula> formulaList) {
        if(formulaList == null || formulaList.size() == 0)
            throw new IllegalArgumentException("The list of forumulas is empty");
        Formula conjFormula = formulaList.get(0);
        for(int i = 1; i < formulaList.size(); ++i) {
            conjFormula = new BinaryOperator(conjFormula, formulaList.get(i), Operator.AND);
        }
        return conjFormula;
    }


}
