package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.FormulaVisitor;
import it.sagelab.specpro.models.ContextBasedVisitor;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class FormulaPrinter extends ContextBasedVisitor<PrintStream> implements FormulaVisitor {

    private final Map<String, String> operators;
    private boolean decomposeXor;
    private boolean decomposeImplication;

    /**
     * Instantiates a new formula printer
     *
     * @param c the PrintStream to write in
     */
    public FormulaPrinter(PrintStream c) {
        super(c);
        operators = new HashMap<>();
        decomposeXor = false;
        decomposeImplication = false;
    }

    public void setNotOperator(String value) {
        operators.put(UnaryOperator.Operator.NOT.toString(), value);
    }

    public void setGloballyOperator(String value) {
        operators.put(UnaryOperator.Operator.GLOBALLY.toString(), value);
    }

    public void setEventuallyOperator(String value) {
        operators.put(UnaryOperator.Operator.EVENTUALLY.toString(), value);
    }

    public void setNextOperator(String value) {
        operators.put(UnaryOperator.Operator.NEXT.toString(), value);
    }

    public void setAndOperator(String value) {
        operators.put(BinaryOperator.Operator.AND.toString(), value);
    }

    public void setOrOperator(String value) {
        operators.put(BinaryOperator.Operator.OR.toString(), value);
    }

    public void setXorOperator(String value) {
        operators.put(BinaryOperator.Operator.XOR.toString(), value);
    }

    public void setUntilOperator(String value) {
        operators.put(BinaryOperator.Operator.UNTIL.toString(), value);
    }

    public void setWeakUntilOperator(String value) {
        operators.put(BinaryOperator.Operator.WEAK_UNTIL.toString(), value);
    }

    public void setImplicationOperator(String value) {
        operators.put(BinaryOperator.Operator.IMPLICATION.toString(), value);
    }

    public void setEquivalenceOperator(String value) {
        operators.put(BinaryOperator.Operator.EQUIVALENCE.toString(), value);
    }

    public void setDecomposeXor(boolean decomposeXor) {
        this.decomposeXor = decomposeXor;
    }

    public void setDecomposeImplication(boolean decomposeImplication) {
        this.decomposeImplication = decomposeImplication;
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

    /**
     * Prints the string.
     *
     * @param s the s
     */
    private void print(String s) {
        getContext().print(s);
    }

    private void printOperator(String op) {
        print(operators.getOrDefault(op, op));
    }

    @Override
    public void visitUnaryOperator(UnaryOperator op) {
        printOperator(op.getOperator().toString());
        printFormula(op.getChild());
    }

    @Override
    public void visitBinaryOperator(BinaryOperator op) {

        if(op.getOperator() == BinaryOperator.Operator.WEAK_UNTIL) {
            // Weak Until is not directly mapped in AALTA, therefore (p W q) is translated into (([]p) | (p U q)).
            Formula f1 = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.GLOBALLY);
            Formula f2 = new BinaryOperator(op.getLeftFormula(), op.getRightFormula(), BinaryOperator.Operator.UNTIL);
            Formula f3 = new BinaryOperator(f1, f2, BinaryOperator.Operator.OR);
            f3.accept(this);
            return;
        }

        else if(op.getOperator() == BinaryOperator.Operator.XOR && decomposeXor) {
            // decompose XOR operator, therefore (p XOR q) is translated into ((!p&q) | (p&!q))).
            Formula p = op.getLeftFormula();
            Formula q = op.getRightFormula();
            Formula not_p = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.NOT);
            Formula not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
            Formula not_p_and_q = new BinaryOperator(not_p, q, BinaryOperator.Operator.AND);
            Formula p_and_not_q = new BinaryOperator(p, not_q, BinaryOperator.Operator.AND);
            Formula xor = new BinaryOperator(not_p_and_q, p_and_not_q, BinaryOperator.Operator.OR);
            xor.accept(this);
            return;
        }

        else if(op.getOperator() == BinaryOperator.Operator.IMPLICATION && decomposeImplication) {
            // decompose implication operator, therefore (p -> q) is translated into (!p | q).
            Formula not_p = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.NOT);
            Formula implication = new BinaryOperator(not_p, op.getRightFormula(), BinaryOperator.Operator.OR);
            implication.accept(this);
            return;
        }

        else if(op.getOperator() == BinaryOperator.Operator.EQUIVALENCE && decomposeImplication) {
            // decompose equivalence operator, therefore (p <-> q) is translated into (!p & !q) | (p & q).
            Formula p = op.getLeftFormula();
            Formula q = op.getRightFormula();
            Formula not_p = new UnaryOperator(p, UnaryOperator.Operator.NOT);
            Formula not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
            Formula not_p_and_not_q = new BinaryOperator(not_p, not_q, BinaryOperator.Operator.AND);
            Formula p_and_q = new BinaryOperator(p, q, BinaryOperator.Operator.AND);
            Formula equi = new BinaryOperator(not_p_and_not_q, p_and_q, BinaryOperator.Operator.OR);
            equi.accept(this);
            return;
        } else {
            printFormula(op.getLeftFormula());
            print(" ");
            printOperator(op.getOperator().toString());
            print(" ");
            printFormula(op.getRightFormula());
        }

    }

    @Override
    public void visitAtom(Atom at) {
        print(at.getName());
    }
}
