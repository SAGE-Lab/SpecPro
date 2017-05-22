package snl2fl.req.requirements.realtime;

import java.util.List;

import snl2fl.req.expressions.Expression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;
import snl2fl.req.requirements.Time;
import snl2fl.req.visitor.RealTimeRequirementVisitor;

/**
 * The Class RealTimeRequirement.
 *
 * @author Simone Vuotto
 */
public abstract class RealTimeRequirement extends Requirement {
    
    /** The time. */
    private final Time time;

    /**
     * Instantiates a new real time requirement.
     *
     * @param scope the scope
     * @param expressions the expressions
     * @param t the t
     */
    public RealTimeRequirement(Scope scope, List<Expression> expressions, Time t) {
        super(scope, expressions);
        this.time = t;
    }

    /**
     * Accept.
     *
     * @param visitor the visitor
     */
    public abstract void accept(RealTimeRequirementVisitor visitor);

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}
    
    
}
