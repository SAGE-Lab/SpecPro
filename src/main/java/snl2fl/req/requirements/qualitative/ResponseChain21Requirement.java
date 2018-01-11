package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitResponseChain21Requirement(this);
    }
}
