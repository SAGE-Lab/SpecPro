package it.sagelab.specpro.models.psp.realtime;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.Time;
import it.sagelab.specpro.models.psp.expressions.Expression;

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
     * @see it.sagelab.models.psp.realtime.RealTimeRequirement#accept(it.sagelab.it.sagelab.fe.psp.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedInvarianceRequirement(this);
    }
}
