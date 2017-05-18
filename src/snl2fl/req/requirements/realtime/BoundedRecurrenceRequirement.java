package it.unige.pat2fl.req.requirements.realtime;

import java.util.Arrays;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.requirements.Time;
import it.unige.pat2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
 */
public class BoundedRecurrenceRequirement extends RealTimeRequirement {
    public BoundedRecurrenceRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Arrays.asList(expr), t);
    }

    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedRecurrenceRequirement(this);
    }
}
