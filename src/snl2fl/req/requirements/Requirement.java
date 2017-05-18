package snl2fl.req.requirements;

import java.util.List;

import snl2fl.req.expressions.Expression;

/**
 * @author Simone Vuotto
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

