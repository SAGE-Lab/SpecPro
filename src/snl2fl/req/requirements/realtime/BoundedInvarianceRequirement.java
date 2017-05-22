package snl2fl.req.requirements.realtime;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * The Class BoundedInvarianceRequirement.
 *
 * @author Simone Vuotto
 */
public class BoundedInvarianceRequirement extends RealTimeRequirement {
    
    /**
     * Instantiates a new bounded invariance requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     * @param t the t
     */
    public BoundedInvarianceRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions, t);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.realtime.RealTimeRequirement#accept(snl2fl.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedInvarianceRequirement(this);
    }
}
