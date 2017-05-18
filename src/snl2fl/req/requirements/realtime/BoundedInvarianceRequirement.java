package it.unige.pat2fl.req.requirements.realtime;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.requirements.Time;
import it.unige.pat2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
 */
public class BoundedInvarianceRequirement extends RealTimeRequirement {
    public BoundedInvarianceRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions, t);
    }

    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedInvarianceRequirement(this);
    }
}
