package it.unige.pat2fl.req.requirements.qualitative;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Requirement;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

public abstract class QualitativeRequirement extends Requirement {

    public QualitativeRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    public abstract void accept(QualitativeRequirementVisitor visitor);

}
