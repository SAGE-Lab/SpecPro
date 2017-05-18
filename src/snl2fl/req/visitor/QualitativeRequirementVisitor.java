package snl2fl.req.visitor;

import snl2fl.req.requirements.qualitative.*;

/**
 * @author Simone Vuotto
 * creation date 02/10/15.
 */
public interface QualitativeRequirementVisitor {

    public void visitAbsenceRequirement(AbsenceRequirement requirement);

    public void visitBoundedExistenceRequirement(BoundedExistenceRequirement requirement);

    public void visitExistenceRequirement(ExistenceRequirement requirement);

    public void visitPrecedenceChain12Requirement(PrecedenceChain12Requirement requirement);

    public void visitPrecedenceChain21Requirement(PrecedenceChain21Requirement requirement);

    public void visitPrecedenceRequirement(PrecedenceRequirement requirement);

    public void visitResponseChain12Requirement(ResponseChain12Requirement requirement);

    public void visitResponseChain21Requirement(ResponseChain21Requirement requirement);

    public void visitResponseRequirement(ResponseRequirement requirement);

    public void visitUniversalityRequirement(UniversalityRequirement requirement);

    public void visitInvariantRequirement(InvariantRequirement requirement);

    public void visitConstrainedChain12Requirement(ConstrainedChain12Requriement requriement);

}
