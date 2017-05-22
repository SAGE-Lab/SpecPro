package snl2fl.req.requirements.qualitative;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.visitor.QualitativeRequirementVisitor;

/**
 * The Class QualitativeRequirement.
 *
 * @author Simone Vuotto
 */
public abstract class QualitativeRequirement extends Requirement {

    /**
     * Instantiates a new qualitative requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     */
    public QualitativeRequirement(Scope scope, List<Expression> expressions) {
        super(scope, expressions);
    }

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public abstract void accept(QualitativeRequirementVisitor visitor);

}
