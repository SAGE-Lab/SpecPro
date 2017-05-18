package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * @author = Simone Vuotto
 * creation date  =  02/09/15.
 */
public class BooleanVariableExpression extends VariableExpression {
    private boolean value;

    public BooleanVariableExpression(String name) {
        super(name);
    }

    @Override
    boolean value() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitBooleanVariableExpression(this);
    }

    public void setValue(boolean value){
        this.value = value;
    }
}
