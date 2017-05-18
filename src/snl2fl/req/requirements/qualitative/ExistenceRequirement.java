package it.unige.pat2fl.req.requirements.qualitative;

import java.util.Arrays;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
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
