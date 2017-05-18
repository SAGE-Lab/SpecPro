package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * @author Simone Vuotto
 * creation date  =  02/09/15.
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
