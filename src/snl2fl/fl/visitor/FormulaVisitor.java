package it.unige.pat2fl.fl.visitor;

import it.unige.pat2fl.fl.elements.Atom;
import it.unige.pat2fl.fl.elements.BinaryOperator;
import it.unige.pat2fl.fl.elements.UnaryOperator;

/**
 * Created by Simone Vuotto on 24/11/15.
 */
public interface FormulaVisitor {

    public void visitUnaryOperator(UnaryOperator op);

    public void visitBinaryOperator(BinaryOperator op);

    public void visitAtom(Atom at);
}
