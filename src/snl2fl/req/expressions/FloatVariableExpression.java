package it.unige.pat2fl.req.expressions;

import it.unige.pat2fl.req.visitor.ExpressionVisitor;

/**
 * Created by Simone Vuotto on 03/09/15.
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
