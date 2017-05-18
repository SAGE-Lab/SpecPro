package snl2fl.req.requirements.realtime;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class MinDurationRequirement extends RealTimeRequirement {

    public MinDurationRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Arrays.asList(expr), t);
    }

    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitMinDurationRequiremnt(this);
    }
}
