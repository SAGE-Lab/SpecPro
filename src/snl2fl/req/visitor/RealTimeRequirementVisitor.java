package it.unige.pat2fl.req.visitor;

import it.unige.pat2fl.req.requirements.realtime.*;

/**
 * Created by Simone Vuotto on 02/10/15.
 */
public interface RealTimeRequirementVisitor {

    public void visitBoundedInvarianceRequirement(BoundedInvarianceRequirement requirement);

    public void visitBoundedRecurrenceRequirement(BoundedRecurrenceRequirement requirement);

    public void visitBoundedResponseRequirement(BoundedResponseRequirement requirement);

    public void visitMaxDurationRequirement(MaxDurationRequirement requirement);

    public void visitMinDurationRequiremnt(MinDurationRequirement requirement);
}
