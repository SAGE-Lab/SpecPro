package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;


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
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitInvariantRequirement(this);
    }
}
