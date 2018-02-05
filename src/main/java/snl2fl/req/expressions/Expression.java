package snl2fl.req.expressions;

import snl2fl.req.visitor.ExpressionVisitor;

/**
 * The Class Expression.
 *
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
abstract public class Expression {

    /**
     * Value.
     *
     * @return true, if successful
     */
    abstract boolean value();

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public abstract void accept(ExpressionVisitor visitor);
}
