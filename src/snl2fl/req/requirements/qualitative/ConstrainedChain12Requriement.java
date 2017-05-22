package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * The Class ConstrainedChain12Requriement.
 *
 * @author Simone Vuotto
 */
public class ConstrainedChain12Requriement extends QualitativeRequirement {

    /**
     * Instantiates a new constrained chain 12 requriement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public ConstrainedChain12Requriement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitConstrainedChain12Requirement(this);
    }
}
