package it.sagelab.specpro.models.psp.qualitative;

import java.util.Collections;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.specpro.models.psp.expressions.Expression;

/**
 * The Class AbsenceRequirement.
 *
 * @author Simone Vuotto
 */
public class AbsenceRequirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new absence requirement.
     *
     * @param scope the scope
     * @param expression the expression
     */
    public AbsenceRequirement(Scope scope, Expression expression) {
        super(scope, Collections.singletonList(expression));
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitAbsenceRequirement(this);
    }
}
