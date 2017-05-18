package it.unige.pat2fl.req.expressions;

import it.unige.pat2fl.req.visitor.ExpressionVisitor;

/**
 * Created by Simone Vuotto on 02/09/15.
 */
public class NumberExpression extends Expression{
    final float value;

    public NumberExpression(float value){
        this.value = value;
    }

    @Override
    boolean value() {
        return false;
        //throw new Exception("Not a boolean expression");
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitNumberExpression(this);
    }

    public float floatValue(){ return  value; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
