package it.unige.pat2fl.fl.elements;

import it.unige.pat2fl.fl.visitor.FormulaVisitor;

/**
 * Created by Simone Vuotto on 16/10/15.
 */
public abstract class Formula {

    public abstract void accept(FormulaVisitor visitor);
}
