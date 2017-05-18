package snl2fl.req.visitor;

import snl2fl.req.requirements.realtime.*;

/**
 * @author Simone Vuotto
 * creation date 02/09/15.
 */
public interface RealTimeRequirementVisitor {

    public void visitBoundedInvarianceRequirement(BoundedInvarianceRequirement requirement);

    public void visitBoundedRecurrenceRequirement(BoundedRecurrenceRequirement requirement);

    public void visitBoundedResponseRequirement(BoundedResponseRequirement requirement);

    public void visitMaxDurationRequirement(MaxDurationRequirement requirement);

    public void visitMinDurationRequiremnt(MinDurationRequirement requirement);
}
