package it.sagelab.specpro.models.psp.realtime;

import java.util.Collections;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.Time;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class BoundedRecurrenceRequirement.
 *
 * @author Simone Vuotto
 */
public class BoundedRecurrenceRequirement extends RealTimeRequirement {
    
    /**
     * Instantiates a new bounded recurrence requirement.
     *
     * @param scope the scope
     * @param expr the expr
     * @param t the t
     */
    public BoundedRecurrenceRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Collections.singletonList(expr), t);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.realtime.RealTimeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitBoundedRecurrenceRequirement(this);
    }
}
