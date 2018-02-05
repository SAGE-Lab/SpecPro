package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;


/**
 * The Class BooleanVariableExpression.
 *
 * @author = Simone Vuotto
 * creation date  =  02/09/15.
 */
public class BooleanVariableExpression extends VariableExpression {
    
    /** The value. */
    private boolean value;

    /**
     * Instantiates a new boolean variable expression.
     *
     * @param name the name
     */
    public BooleanVariableExpression(String name) {
        super(name);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.expressions.Expression#value()
     */
    @Override
    boolean value() {
        return value;
    }

    /* (non-Javadoc)
     * @see snl2fl.req.expressions.Expression#accept(snl2fl.req.visitor.ExpressionVisitor)
     */
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitBooleanVariableExpression(this);
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(boolean value){
        this.value = value;
    }
}
