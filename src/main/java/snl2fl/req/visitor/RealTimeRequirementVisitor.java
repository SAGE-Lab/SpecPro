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
    void visitBoundedInvarianceRequirement(BoundedInvarianceRequirement requirement);

    /**
     * Visit bounded recurrence requirement.
     *
     * @param requirement the requirement
     */
    void visitBoundedRecurrenceRequirement(BoundedRecurrenceRequirement requirement);

    /**
     * Visit bounded response requirement.
     *
     * @param requirement the requirement
     */
    void visitBoundedResponseRequirement(BoundedResponseRequirement requirement);

    /**
     * Visit max duration requirement.
     *
     * @param requirement the requirement
     */
    void visitMaxDurationRequirement(MaxDurationRequirement requirement);

    /**
     * Visit min duration requiremnt.
     *
     * @param requirement the requirement
     */
    void visitMinDurationRequiremnt(MinDurationRequirement requirement);
}
