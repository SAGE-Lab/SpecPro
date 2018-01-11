package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * The Class ExistenceRequirement.
 *
 * @author Simone Vuotto
 */
public class ExistenceRequirement extends QualitativeRequirement {
    
    /**
     * Instantiates a new existence requirement.
     *
     * @param scope the scope
     * @param expr the expr
     */
    public ExistenceRequirement(Scope scope, Expression expr) {
        super(scope, Arrays.asList(expr));
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitExistenceRequirement(this);
    }
}
