package snl2fl.req.requirements.realtime;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * @author Simone Vuotto
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
