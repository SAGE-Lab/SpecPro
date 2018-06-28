package it.sagelab.models.psp.realtime;

import java.util.Arrays;

import it.sagelab.models.psp.Scope;
import it.sagelab.models.psp.Time;
import it.sagelab.fe.snl2fl.visitor.RealTimeRequirementVisitor;
import it.sagelab.models.psp.expressions.Expression;

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
     * @see it.sagelab.models.psp.realtime.RealTimeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitMaxDurationRequirement(this);
    }
}