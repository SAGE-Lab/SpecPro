package it.sagelab.specpro.models.psp;

import it.sagelab.specpro.models.psp.expressions.Expression;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;

import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * The Class Requirement.
 *
 * @author Simone Vuotto
 */
public abstract class Requirement {

    /** The requirement ID */
    private String reqId;
    
    /** The scope. */
    private final Scope scope;
    
    /** The expressions. */
    private final List<Expression> expressions;

    private String text;

    /**
     * Instantiates a new requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public Requirement(Scope scope, List<Expression> expressions){
        this.reqId = null;
        if(scope == null)
            this.scope = new Scope(Scope.Type.GLOBALLY, Collections.emptyList());
        else
            this.scope = scope;
        this.expressions = expressions;
    }


    /**
     * Gets the requirement id
     *
     * @return the reqId
     */
    public String getReqId() { return reqId; }

    /**
     * Sets the psp id
     *
     * @param reqId the new requirement id
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * Gets the text representation of the requirement
     * @return the requirement in text format
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the requirement text
     * @param text the text of the requirement
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the scope.
     *
     * @return the scope
     */
    public Scope getScope() { return scope; }

    /**
     * Gets the expressions.
     *
     * @return the expressions
     */
    public List<Expression> getExpressions() { return expressions; }

    /**
     * Gets the set of variables used in the requirement.
     *
     * @return the set of variable
     */
    public Set<VariableExpression> getVariables() {
        VariablesCollectorVisitor visitor = new VariablesCollectorVisitor();
        for(Expression e: expressions) {
            e.accept(visitor);
        }

        for(Expression e : scope.getExpressions()) {
            e.accept(visitor);
        }

        return visitor.getVariables();
    }

    /**
     * Key.
     *
     * @return the string
     */
    public String key() {
        return this.getClass().toString() + "_" + scope.getType().toString();
    }
}

