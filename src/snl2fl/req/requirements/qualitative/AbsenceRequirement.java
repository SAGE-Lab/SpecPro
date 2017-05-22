package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

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
        super(scope, Arrays.asList(expression));
    }

    /* (non-Javadoc)
     * @see snl2fl.req.requirements.qualitative.QualitativeRequirement#accept(snl2fl.req.visitor.QualitativeRequirementVisitor)
     */
    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitAbsenceRequirement(this);
    }
}
