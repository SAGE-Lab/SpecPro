package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
        super(scope, Arrays.asList(expr));
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitUniversalityRequirement(this);
    }
}
