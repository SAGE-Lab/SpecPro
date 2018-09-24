package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;

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
