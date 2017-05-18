package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class InvariantRequirement extends QualitativeRequirement {

    public InvariantRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitInvariantRequirement(this);
    }
}
