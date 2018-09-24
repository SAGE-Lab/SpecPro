package it.sagelab.specpro.models.psp.qualitative;

import java.util.Collections;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class BoundedExistenceRequirement.
 *
 * @author Simone Vuotto
 */
public class BoundedExistenceRequirement extends QualitativeRequirement {
    
    /** The n times. */
    private final int nTimes;

    /**
     * Instantiates a new bounded existence requirement.
     *
     * @param scope the scope
     * @param expr the expr
     * @param nTimes the n times
     */
    public BoundedExistenceRequirement(Scope scope, Expression expr, int nTimes) {
        super(scope, Collections.singletonList(expr));
        this.nTimes = nTimes;
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitBoundedExistenceRequirement(this);
    }

    /**
     * Gets the n times.
     *
     * @return the n times
     */
    public int getNTimes() { return nTimes; }
}
