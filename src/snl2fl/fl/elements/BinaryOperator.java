/**
 * Created by Simone Vuotto on 16/10/15.
 */

package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

public class BinaryOperator extends Formula {

    public enum Operator {
        AND("&"), OR("|"), XOR("^"), UNTIL("U"), WEAK_UNTIL("W"), IMPLICATION("->"), EQUIVALENCE("<->");

        private final String description;
        Operator(String description){ this.description = description;}

        @Override
        public String toString() {
            return description;
        }

        public static Operator getOp(String description) {
            for(BinaryOperator.Operator op : BinaryOperator.Operator.values())
                if(op.toString().equals(description))
                    return op;
            return null;
        }
    };
    private Formula leftFormula, rightFormula;
    private Operator op;

    public BinaryOperator(Formula leftFormula, Formula rightFormula, Operator op) {
        this.leftFormula = leftFormula;
        this.rightFormula = rightFormula;
        this.op = op;
    }

    public Formula getLeftFormula() { return  leftFormula; }

    public Formula getRightFormula() { return  rightFormula; }

    public Operator getOperator() { return op; }

    @Override
    public String toString() {
        return "("+leftFormula + " " + op + " " + rightFormula +")";
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitBinaryOperator(this);
    }
}
