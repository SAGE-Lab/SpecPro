package it.sagelab.specpro.models.psp.qualitative;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class PrecedenceChain12Requirement.
 *
 * @author Simone Vuotto
 */
public class PrecedenceChain12Requirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new precedence chain 12 requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public PrecedenceChain12Requirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.psp.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitPrecedenceChain12Requirement(this);
    }
}
