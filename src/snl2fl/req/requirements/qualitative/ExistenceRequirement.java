package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class ExistenceRequirement extends QualitativeRequirement {
    public ExistenceRequirement(Scope scope, Expression expr) {
        super(scope, Arrays.asList(expr));
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitExistenceRequirement(this);
    }
}
