package it.unige.pat2fl.req.requirements.qualitative;

import java.util.List;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
 */
public class ResponseRequirement extends QualitativeRequirement {
    public ResponseRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitResponseRequirement(this);
    }
}
