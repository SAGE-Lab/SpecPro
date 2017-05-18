package snl2fl.req.requirements.realtime;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class MaxDurationRequirement extends RealTimeRequirement {
    public MaxDurationRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Arrays.asList(expr), t);
    }

    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitMaxDurationRequirement(this);
    }
}
