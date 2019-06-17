package it.sagelab.specpro.models.ltl;

public class Const extends Formula {

    public static final Const TRUE  = new Const(true);
    public static final Const FALSE = new Const(false);

    private final boolean value;

    public Const(boolean value) {
        this.value = value;
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visitConst(this);
    }

    @Override
    public String toString() {
        return value ? "true" : "false";
    }
}
