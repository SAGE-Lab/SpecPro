package it.unige.pat2fl.req.requirements.qualitative;

import java.util.Arrays;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
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
