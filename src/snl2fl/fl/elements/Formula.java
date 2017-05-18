package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

/**
 * @author Simone Vuotto
 */
public abstract class Formula {

    public abstract void accept(FormulaVisitor visitor);
}
