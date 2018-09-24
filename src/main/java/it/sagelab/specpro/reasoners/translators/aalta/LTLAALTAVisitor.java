package it.sagelab.specpro.reasoners.translators.aalta;

import java.io.PrintStream;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import it.sagelab.specpro.fe.ltl.visitor.FormulaVisitor;
import it.sagelab.specpro.fe.snl2fl.visitor.ContextBasedVisitor;

/**
 * The Class LTLAALTAVisitor.
 *
 * @author Massimo Narizzano
 */
public class LTLAALTAVisitor extends ContextBasedVisitor<PrintStream> implements FormulaVisitor{
    
    /** The formula. */
    private Formula formula;

    /**
     * Instantiates a new LTL nu SMV visitor.
     *
     * @param c the c
     */
    public LTLAALTAVisitor(PrintStream c) {
        super(c);

    }

    /* (non-Javadoc)
     * @see FormulaVisitor#visitUnaryOperator(UnaryOperator)
     */
    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        switch(op.getOperator()){
            case NOT:
                print("~");
                print(op.getChild());
                break;
            case GLOBALLY:
                print("G");
                print(op.getChild());
                break;
            case EVENTUALLY:
                print("F");
                print(op.getChild());
                break;
            case NEXT:
                print("X");
                print(op.getChild());
                break;
        }
    }

    /* (non-Javadoc)
     * @see FormulaVisitor#visitBinaryOperator(BinaryOperator)
     */
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
                // XOR can not be directly mapped in AALTA input format, therefore (p XOR q) is translated into ((!p&q) | (p&!q))).
            	Formula p = op.getLeftFormula();
            	Formula q = op.getRightFormula();
            	Formula not_p = new UnaryOperator(p, UnaryOperator.Operator.NOT);
                Formula not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
                Formula not_p_and_q = new BinaryOperator(not_p, q, BinaryOperator.Operator.AND);
                Formula p_and_not_q = new BinaryOperator(p, not_q, BinaryOperator.Operator.AND);
                Formula xor = new BinaryOperator(not_p_and_q, p_and_not_q, BinaryOperator.Operator.OR);
                xor.accept(this);
                break;
            case UNTIL:
                print(op.getLeftFormula());
                print(" U ");
                print(op.getRightFormula());
                break;
            case WEAK_UNTIL:
            	// Weak Until is not directly mapped in AALTA, therefore (p W q) is translated into (([]p) | (p U q)).
                Formula f1 = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.GLOBALLY);
                Formula f2 = new BinaryOperator(op.getLeftFormula(), op.getRightFormula(), BinaryOperator.Operator.UNTIL);
                Formula f3 = new BinaryOperator(f1, f2, BinaryOperator.Operator.OR);
                f3.accept(this);
                break;
            case IMPLICATION:
            	// IMPLICATION(->) can not be directly mapped in AALTA input format, therefore (p -> q) is translated into (!p | q).
            	p = op.getLeftFormula();
            	q = op.getRightFormula();
            	not_p = new UnaryOperator(p, UnaryOperator.Operator.NOT);
                Formula implication = new BinaryOperator(not_p, q, BinaryOperator.Operator.OR);
            	implication.accept(this);
                break;
            case EQUIVALENCE:
            	// EQUIVALENCE(<->) can not be directly mapped in AALTA input format, therefore (p <-> q) is translated into (!p & !q) | (p & q).
            	 p = op.getLeftFormula();
            	 q = op.getRightFormula();
            	 not_p = new UnaryOperator(p, UnaryOperator.Operator.NOT);
            	 not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
                 Formula not_p_and_not_q = new BinaryOperator(not_p, not_q, BinaryOperator.Operator.AND);
                 Formula p_and_q = new BinaryOperator(p, q, BinaryOperator.Operator.AND);
                 Formula equi  = new BinaryOperator(not_p_and_not_q, p_and_q, BinaryOperator.Operator.OR);
                 equi.accept(this);
                 break;
        }
    }

    /* (non-Javadoc)
     * @see FormulaVisitor#visitAtom(Atom)
     */
    @Override
    public void visitAtom(Atom at) {
        print(at.getName());
    }

    /**
     * Prints the.
     *
     * @param f the f
     */
    private void print(Formula f) {
        if(f instanceof Atom) {
            print(" ");
            f.accept(this);
        } else {
            print("(");
            f.accept(this);
            print(")");
        }
    }

    /**
     * Prints the.
     *
     * @param s the s
     */
    private void print(String s) {
        getContext().print(s);
    }

}
