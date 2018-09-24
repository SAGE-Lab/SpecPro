package it.sagelab.specpro.fe.snl2fl.visitor;

import it.sagelab.specpro.models.psp.realtime.*;

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
