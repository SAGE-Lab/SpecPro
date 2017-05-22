package snl2fl.req.visitor;

import snl2fl.req.requirements.realtime.*;

/**
 * The Interface RealTimeRequirementVisitor.
 *
 * @author Simone Vuotto
 * creation date 02/09/15.
 */
public interface RealTimeRequirementVisitor {

    /**
     * Visit bounded invariance requirement.
     *
     * @param requirement the requirement
     */
    public void visitBoundedInvarianceRequirement(BoundedInvarianceRequirement requirement);

    /**
     * Visit bounded recurrence requirement.
     *
     * @param requirement the requirement
     */
    public void visitBoundedRecurrenceRequirement(BoundedRecurrenceRequirement requirement);

    /**
     * Visit bounded response requirement.
     *
     * @param requirement the requirement
     */
    public void visitBoundedResponseRequirement(BoundedResponseRequirement requirement);

    /**
     * Visit max duration requirement.
     *
     * @param requirement the requirement
     */
    public void visitMaxDurationRequirement(MaxDurationRequirement requirement);

    /**
     * Visit min duration requiremnt.
     *
     * @param requirement the requirement
     */
    public void visitMinDurationRequiremnt(MinDurationRequirement requirement);
}
