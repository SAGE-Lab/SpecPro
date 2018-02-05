package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitPrecedenceChain12Requirement(this);
    }
}
