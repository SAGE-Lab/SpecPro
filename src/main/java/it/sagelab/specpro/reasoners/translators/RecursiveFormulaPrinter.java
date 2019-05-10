package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.*;

import java.io.PrintStream;

public class RecursiveFormulaPrinter extends FormulaPrinter implements FormulaVisitor {

    public RecursiveFormulaPrinter(PrintStream printStream) {
        super(printStream);
    }

    public void print(Formula f) {
        f.accept(this);
    }

    /**
     * Prints the formula.
     *
     * @param f the f
     */
    private void printFormula(Formula f) {
        if(f instanceof Atom) {
            print(" ");
            f.accept(this);
        } else {
            print("(");
            f.accept(this);
            print(")");
        }
    }


    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        print(opToStr(op));
        printFormula(op.getChild());
    }

    @Override
    public void visitBinaryOperator(BinaryOperator op) {

        op = decompose(op);
        printFormula(op.getLeftFormula());
        print(" ");
        print(opToStr(op));
        print(" ");
        printFormula(op.getRightFormula());

    }

    @Override
    public void visitAtom(Atom at) {
        print(at.getLabel());
    }

}
