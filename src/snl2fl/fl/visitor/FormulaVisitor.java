package snl2fl.fl.visitor;

import snl2fl.fl.elements.Atom;
import snl2fl.fl.elements.BinaryOperator;
import snl2fl.fl.elements.UnaryOperator;

/**
 * The Interface FormulaVisitor.
 *
 * @author Simone Vuotto
 */
public interface FormulaVisitor {

    /**
     * Visit unary operator.
     *
     * @param op the op
     */
    public void visitUnaryOperator(UnaryOperator op);

    /**
     * Visit binary operator.
     *
     * @param op the op
     */
    public void visitBinaryOperator(BinaryOperator op);

    /**
     * Visit atom.
     *
     * @param at the at
     */
    public void visitAtom(Atom at);
}
