package it.sagelab.models.psp.realtime;

import java.util.List;

import it.sagelab.models.psp.Scope;
import it.sagelab.models.psp.Time;
import it.sagelab.fe.snl2fl.visitor.RealTimeRequirementVisitor;
import it.sagelab.models.psp.expressions.Expression;

/**
 * The Class BoundedResponseRequirement.
 *
 * @author Simone Vuotto
 */
public class BoundedResponseRequirement extends RealTimeRequirement {
    
    /**
     * Instantiates a new bounded response requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     * @param t the t
     */
    public BoundedResponseRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions, t);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.realtime.RealTimeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedResponseRequirement(this);
    }
}
