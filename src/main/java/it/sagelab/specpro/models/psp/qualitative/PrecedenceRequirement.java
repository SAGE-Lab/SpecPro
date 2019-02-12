package it.sagelab.specpro.models.psp.qualitative;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class PrecedenceRequirement.
 *
 * @author Simone Vuotto
 */
public class PrecedenceRequirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new precedence requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public PrecedenceRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitPrecedenceRequirement(this);
    }
}
