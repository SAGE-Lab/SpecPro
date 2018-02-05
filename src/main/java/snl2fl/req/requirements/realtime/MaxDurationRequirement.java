package snl2fl.req.requirements.realtime;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * The Class MaxDurationRequirement.
 *
 * @author Simone Vuotto
 */
public class MaxDurationRequirement extends RealTimeRequirement {
    
    /**
     * Instantiates a new max duration requirement.
     *
     * @param scope the scope
     * @param expr the expr
     * @param t the t
     */
    public MaxDurationRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Arrays.asList(expr), t);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.realtime.RealTimeRequirement#accept(snl2fl.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitMaxDurationRequirement(this);
    }
}
