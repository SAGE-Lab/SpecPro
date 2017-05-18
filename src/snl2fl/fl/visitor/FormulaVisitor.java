package snl2fl.fl.visitor;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.UnaryOperator;

/**
 * @author Simone Vuotto
 */
public interface FormulaVisitor {

    public void visitUnaryOperator(UnaryOperator op);

    public void visitBinaryOperator(BinaryOperator op);

    public void visitAtom(Atom at);
}
