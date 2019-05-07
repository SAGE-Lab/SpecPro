package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ContextBasedVisitor;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.UnaryOperator;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class FormulaPrinter extends ContextBasedVisitor<PrintStream> {

    protected final Map<String, String> operators;
    protected boolean decomposeXor;
    protected boolean decomposeImplication;

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


    public abstract void print(Formula f);

    /**
     * Prints the string.
     *
     * @param s the s
     */
    protected void print(String s) {
        getContext().print(s);
    }

    protected String opToStr(BinaryOperator op) {
        return operators.getOrDefault(op.getOperator().toString(), op.getOperator().toString());
    }

    protected String opToStr(UnaryOperator op) {
        return operators.getOrDefault(op.getOperator().toString(), op.getOperator().toString());
    }

    protected BinaryOperator decompose(BinaryOperator op) {
        if(op.getOperator() == BinaryOperator.Operator.WEAK_UNTIL) {
            // Weak Until is not directly mapped in AALTA, therefore (p W q) is translated into (([]p) | (p U q)).
            UnaryOperator f1 = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.GLOBALLY);
            BinaryOperator f2 = new BinaryOperator(op.getLeftFormula(), op.getRightFormula(), BinaryOperator.Operator.UNTIL);
            BinaryOperator f3 = new BinaryOperator(f1, f2, BinaryOperator.Operator.OR);

            return f3;
        }

        else if(op.getOperator() == BinaryOperator.Operator.XOR && decomposeXor) {
            // decompose XOR operator, therefore (p XOR q) is translated into ((!p&q) | (p&!q))).
            Formula p = op.getLeftFormula();
            Formula q = op.getRightFormula();
            UnaryOperator not_p = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.NOT);
            UnaryOperator not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
            BinaryOperator not_p_and_q = new BinaryOperator(not_p, q, BinaryOperator.Operator.AND);
            BinaryOperator p_and_not_q = new BinaryOperator(p, not_q, BinaryOperator.Operator.AND);
            BinaryOperator xor = new BinaryOperator(not_p_and_q, p_and_not_q, BinaryOperator.Operator.OR);
            return xor;
        }

        else if(op.getOperator() == BinaryOperator.Operator.IMPLICATION && decomposeImplication) {
            // decompose implication operator, therefore (p -> q) is translated into (!p | q).
            UnaryOperator not_p = new UnaryOperator(op.getLeftFormula(), UnaryOperator.Operator.NOT);
            BinaryOperator implication = new BinaryOperator(not_p, op.getRightFormula(), BinaryOperator.Operator.OR);
            return implication;
        }

        else if(op.getOperator() == BinaryOperator.Operator.EQUIVALENCE && decomposeImplication) {
            // decompose equivalence operator, therefore (p <-> q) is translated into (!p & !q) | (p & q).
            Formula p = op.getLeftFormula();
            Formula q = op.getRightFormula();
            UnaryOperator not_p = new UnaryOperator(p, UnaryOperator.Operator.NOT);
            UnaryOperator not_q = new UnaryOperator(q, UnaryOperator.Operator.NOT);
            BinaryOperator not_p_and_not_q = new BinaryOperator(not_p, not_q, BinaryOperator.Operator.AND);
            BinaryOperator p_and_q = new BinaryOperator(p, q, BinaryOperator.Operator.AND);
            BinaryOperator equi = new BinaryOperator(not_p_and_not_q, p_and_q, BinaryOperator.Operator.OR);

            return equi;
        } else {
            return op;
        }
    }

}
