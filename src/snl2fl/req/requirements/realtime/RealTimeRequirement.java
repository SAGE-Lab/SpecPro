package it.unige.pat2fl.req.requirements.realtime;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Requirement;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.requirements.Time;
import it.unige.pat2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * Created by Simone Vuotto on 03/09/15.
 */
public abstract class RealTimeRequirement extends Requirement {
    private final Time time;

    public RealTimeRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions);
        this.time = t;
    }

    public abstract void accept(RealTimeRequirementVisitor visitor);
}
