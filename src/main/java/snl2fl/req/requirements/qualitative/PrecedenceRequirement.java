package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitPrecedenceRequirement(this);
    }
}
