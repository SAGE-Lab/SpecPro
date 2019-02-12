package it.sagelab.specpro.models.ltl;

/**
 * The Class Formula.
 *
 * @author Simone Vuotto
 */
public abstract class Formula {

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public abstract void accept(FormulaVisitor visitor);
}
