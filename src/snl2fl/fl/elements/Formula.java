package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

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
