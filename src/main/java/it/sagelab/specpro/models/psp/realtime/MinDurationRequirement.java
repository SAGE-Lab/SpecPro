package it.sagelab.specpro.models.psp.realtime;

import java.util.Collections;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.Time;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class MinDurationRequirement.
 *
 * @author Simone Vuotto
 */
public class MinDurationRequirement extends RealTimeRequirement {

    /**
     * Instantiates a new min duration requirement.
     *
     * @param scope the scope
     * @param expr the expr
     * @param t the t
     */
    public MinDurationRequirement(Scope scope, Expression expr, Time t) {
        super(scope, Collections.singletonList(expr), t);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.realtime.RealTimeRequirement#accept(it.sagelab.it.sagelab.fe.psp.req.visitor.RealTimeRequirementVisitor)
     */
    @Override
    public void accept(RealTimeRequirementVisitor visitor) {
        visitor.visitMinDurationRequiremnt(this);
    }
}
