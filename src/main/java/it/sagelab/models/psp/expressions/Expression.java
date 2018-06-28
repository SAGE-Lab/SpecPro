package it.sagelab.models.psp.expressions;

import it.sagelab.fe.snl2fl.visitor.ExpressionVisitor;

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
