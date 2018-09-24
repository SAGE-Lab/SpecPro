package it.sagelab.specpro.models.psp.expressions;

import it.sagelab.specpro.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class FloatVariableExpression.
 *
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
public class FloatVariableExpression extends VariableExpression {
    
    /** The value. */
    float value;

    /**
     * Instantiates a new float variable expression.
     *
     * @param name the name
     */
    public FloatVariableExpression(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.expressions.Expression#value()
     */
    @Override
    public boolean value(){ return value != 0; }

    /* (non-Javadoc)
     * @see it.sagelab.it.sagelab.fe.snl2fl.req.expressions.Expression#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.ExpressionVisitor)
     */
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitFloatVariableExpression(this);
    }

    /**
     * Float value.
     *
     * @return the float
     */
    public float floatValue(){ return  value; }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(float value) { this.value = value; }

}
