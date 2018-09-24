package it.sagelab.specpro.models.psp.qualitative;

import java.util.List;

import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.fe.snl2fl.visitor.QualitativeRequirementVisitor;
import it.sagelab.specpro.models.psp.expressions.Expression;


/**
 * The Class InvariantRequirement.
 *
 * @author Simone Vuotto
 */
public class InvariantRequirement extends QualitativeRequirement {

    /**
     * Instantiates a new invariant requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public InvariantRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see it.sagelab.models.psp.qualitative.QualitativeRequirement#accept(it.sagelab.it.sagelab.fe.snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitInvariantRequirement(this);
    }
}
