package it.sagelab.specpro.reasoners.translators.nusmv;

import java.io.PrintStream;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;
import it.sagelab.specpro.fe.snl2fl.visitor.ContextBasedVisitor;

/**
 * The Class LTLNuSMVVisitor.
 *
 * @author Simone Vuotto
 */
public class LTLNuSMVVisitor extends ContextBasedVisitor<PrintStream> implements FormulaVisitor{
    
    /** The formula. */
    private Formula formula;

    /**
     * Instantiates a new LTL nu SMV visitor.
     *
     * @param c the c
     */
    public LTLNuSMVVisitor(PrintStream c) {
        super(c);

    }

    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        switch(op.getOperator()){
            case NOT:
                print("!");
                print(op.getChild());
                break;
            case GLOBALLY:
                print("G ");
                print(op.getChild());
                break;
            case EVENTUALLY:
                print("F ");
                print(op.getChild());
                break;
            case NEXT:
                print("X ");
                print(op.getChild());
                break;
        }
    }

    @Override
    public void visitBinaryOperator(BinaryOperator op) {
        switch(op.getOperator()){
            case AND:
                print(op.getLeftFormula());
                print(" & ");
                print(op.getRightFormula());
                break;
            case OR:
                print(op.getLeftFormula());
                print(" | ");
                print(op.getRightFormula());
                break;
            case XOR:
                print(op.getLeftFormula());
                print(" xor ");
                print(op.getRightFormula());
                break;
            case UNTIL:
                print(op.getLeftFormula());
                print(" U ");
                print(op.getRightFormula());
                break;
            case WEAK_UNTIL:
                // Weak Until is not directly mapped in NuSMV, therefore (p W q) is translated into (([]p) | (p U q)).
                Formula f1 = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.GLOBALLY);
                Formula f2 = new BinaryOperator(op.getLeftFormula(), op.getRightFormula(), BinaryOperator.Operator.UNTIL);
                Formula f3 = new BinaryOperator(f1, f2, BinaryOperator.Operator.OR);
                f3.accept(this);
                break;
            case IMPLICATION:
                print(op.getLeftFormula());
                print(" -> ");
                print(op.getRightFormula());
                break;
            case EQUIVALENCE:
                print(op.getLeftFormula());
                print(" <-> ");
                print(op.getRightFormula());
                break;
        }
    }

    @Override
    public void visitAtom(Atom at) {
        print(at.getName());
    }

    private void print(Formula f) {
        if(f instanceof Atom) {
            f.accept(this);
        } else {
            print("(");
            f.accept(this);
            print(")");
        }
    }

    private void print(String s) {
        getContext().print(s);
    }

}
