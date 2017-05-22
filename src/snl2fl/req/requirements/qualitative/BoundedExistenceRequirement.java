package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
        super(scope, Arrays.asList(expr));
        this.nTimes = nTimes;
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
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
