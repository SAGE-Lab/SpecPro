package it.unige.pat2fl.req.requirements.qualitative;

import java.util.Arrays;

import it.unige.pat2fl.req.expressions.Expression;
import it.unige.pat2fl.req.requirements.Scope;
import it.unige.pat2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * Created by Simone Vuotto on 04/09/15.
 */
public class BoundedExistenceRequirement extends QualitativeRequirement {
    private final int nTimes;

    public BoundedExistenceRequirement(Scope scope, Expression expr, int nTimes) {
        super(scope, Arrays.asList(expr));
        this.nTimes = nTimes;
    }

    @Override
    public void accept(QualitativeRequirementVisitor visitor) {
        visitor.visitBoundedExistenceRequirement(this);
    }

    public int getNTimes() { return nTimes; }
}
