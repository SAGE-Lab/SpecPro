package it.unige.pat2fl.req.requirements;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;

/**
 * Created by Simone Vuotto on 03/09/15.
 */
public abstract class Requirement {
    private final Scope scope;
    private final List<Expression> expressions;

    public Requirement(Scope scope, List<Expression> expressions){
        this.scope = scope;
        this.expressions = expressions;
    }

    public Scope getScope() { return scope; }

    public List<Expression> getExpressions() { return expressions; }

    public String key() {
        return this.getClass().toString() + "_" + scope.getType().toString();
    }
}

