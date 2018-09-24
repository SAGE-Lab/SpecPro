package it.sagelab.specpro.models.psp.qualitative;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class ResponseChain21Requirement.
 *
 * @author Simone Vuotto
 */
public class ResponseChain21Requirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new response chain 21 requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public ResponseChain21Requirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitResponseChain21Requirement(this);
    }
}
