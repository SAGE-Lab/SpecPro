package it.sagelab.fe.ltl.visitor;

import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.models.ltl.elements.BinaryOperator;
import it.sagelab.models.ltl.elements.UnaryOperator;

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
    void visitUnaryOperator(UnaryOperator op);

    /**
     * Visit binary operator.
     *
     * @param op the op
     */
    void visitBinaryOperator(BinaryOperator op);

    /**
     * Visit atom.
     *
     * @param at the at
     */
    void visitAtom(Atom at);
}
