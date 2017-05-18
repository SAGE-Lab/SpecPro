package snl2fl.req.requirements.qualitative;

import java.util.Arrays;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * @author Simone Vuotto
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
