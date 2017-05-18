package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;


/**
 * @author Simone Vuotto
 */
public class PrecedenceChain21Requirement extends QualitativeRequirement {
    public PrecedenceChain21Requirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitPrecedenceChain21Requirement(this);
    }
}
