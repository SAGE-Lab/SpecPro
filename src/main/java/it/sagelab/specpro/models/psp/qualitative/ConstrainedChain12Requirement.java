package it.sagelab.specpro.models.psp.qualitative;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class ConstrainedChain12Requirement.
 *
 * @author Simone Vuotto
 */
public class ConstrainedChain12Requirement extends QualitativeRequirement {

    /**
     * Instantiates a new constrained chain 12 requriement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public ConstrainedChain12Requirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitConstrainedChain12Requirement(this);
    }
}
