package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
public class FloatVariableExpression extends VariableExpression {
    float value;

    public FloatVariableExpression(String name) {
        super(name);
    }

    @Override
    public boolean value(){ return value != 0; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitFloatVariableExpression(this);
    }

    public float floatValue(){ return  value; }

    public void setValue(float value) { this.value = value; }

}
