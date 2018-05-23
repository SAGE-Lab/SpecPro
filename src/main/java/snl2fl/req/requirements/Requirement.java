package snl2fl.req.requirements;

import java.util.Collections;
import java.util.List;

import snl2fl.req.expressions.Expression;

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
     * Sets the requirements id
     *
     * @param reqId the new requirement id
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
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
     * Key.
     *
     * @return the string
     */
    public String key() {
        return this.getClass().toString() + "_" + scope.getType().toString();
    }
}

