package snl2fl.fl.elements;

import snl2fl.fl.visitor.FormulaVisitor;

/**
 * @author Simone Vuotto
 */
public class UnaryOperator extends Formula {

    public enum Operator {
        NOT("!"), GLOBALLY("[]"), EVENTUALLY("<>"), NEXT("o");

        private final String description;

        Operator(String description){ this.description = description; }

        @Override
        public String toString() { return description; }

        public static Operator getOp(String description) {
            for(UnaryOperator.Operator op: UnaryOperator.Operator.values())
                if(op.toString().equals(description))
                    return op;
            return null;
        }

    };
    private final Formula child;
    private final Operator op;

    public UnaryOperator(Formula child, Operator op) {
        this.child = child;
        this.op = op;
    }

    public Formula getChild() { return  child; }

    public Operator getOperator() { return op; }

    @Override
    public String toString(){
        return op + "(" + child + ")";
    }


    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitUnaryOperator(this);
    }

}
