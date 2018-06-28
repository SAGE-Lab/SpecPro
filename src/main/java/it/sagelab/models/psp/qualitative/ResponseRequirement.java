package it.sagelab.models.psp.qualitative;

import java.util.List;

import it.sagelab.models.psp.Scope;
import it.sagelab.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.models.psp.expressions.Expression;

/**
 * The Class ResponseRequirement.
 *
 * @author Simone Vuotto
 */
public class ResponseRequirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new response requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public ResponseRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitResponseRequirement(this);
    }
}
