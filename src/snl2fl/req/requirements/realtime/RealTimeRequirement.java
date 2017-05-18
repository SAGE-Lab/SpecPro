package snl2fl.req.requirements.realtime;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public abstract class RealTimeRequirement extends Requirement {
    private final Time time;

    public RealTimeRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions);
        this.time = t;
    }

    public abstract void accept(RealTimeRequirementVisitor visitor);
}
