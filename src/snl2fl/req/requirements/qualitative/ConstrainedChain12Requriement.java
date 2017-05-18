package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * @author Simone Vuotto
 */
public class ConstrainedChain12Requriement extends QualitativeRequirement {

    public ConstrainedChain12Requriement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitConstrainedChain12Requirement(this);
    }
}
