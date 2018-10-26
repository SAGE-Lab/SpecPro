package it.sagelab.specpro.models.psp.expressions;

import it.sagelab.specpro.fe.snl2fl.visitor.ExpressionVisitor;

/**
 * The Class Expression.
 *
 * @author = Simone Vuotto
 * creation date  =  03/09/15.
 */
abstract public class Expression {

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public abstract void accept(ExpressionVisitor visitor);
}
