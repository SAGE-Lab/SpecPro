package it.unige.pat2fl.req.requirements.qualitative;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * Created by Simone Vuotto on 11/01/16.
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
