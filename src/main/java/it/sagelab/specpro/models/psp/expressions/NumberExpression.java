package it.sagelab.specpro.models.psp.expressions;

import it.sagelab.specpro.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class NumberExpression.
 *
 * @author Simone Vuotto
 * creation date  =  02/09/15.
 */
public class NumberExpression extends Expression{
    
    /** The value. */
    final float value;

    /**
     * Instantiates a new number expression.
     *
     * @param value the value
     */
    public NumberExpression(float value){
        this.value = value;
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitNumberExpression(this);
    }

    /**
     * Float value.
     *
     * @return the float
     */
    public float floatValue(){ return  value; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
