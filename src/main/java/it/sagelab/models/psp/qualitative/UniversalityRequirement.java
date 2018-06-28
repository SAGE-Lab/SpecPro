package it.sagelab.models.psp.qualitative;

import java.util.Collections;

import it.sagelab.models.psp.Scope;
import it.sagelab.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.models.psp.expressions.Expression;

/**
 * The Class UniversalityRequirement.
 *
 * @author Simone Vuotto
 */
public class UniversalityRequirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new universality requirement.
     *
     * @param scope the scope
     * @param expr the expr
     */
    public UniversalityRequirement(Scope scope, Expression expr) {
        super(scope, Collections.singletonList(expr));
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitUniversalityRequirement(this);
    }
}
