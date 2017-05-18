package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class AbsenceRequirement extends QualitativeRequirement {
    public AbsenceRequirement(Scope scope, Expression expression) {
        super(scope, Arrays.asList(expression));
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitAbsenceRequirement(this);
    }
}
