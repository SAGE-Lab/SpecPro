package snl2fl.req.visitor;

import snl2fl.req.requirements.qualitative.*;

/**
 * The Interface QualitativeRequirementVisitor.
 *
 * @author Simone Vuotto
 * creation date 02/10/15.
 */
public interface QualitativeRequirementVisitor {

    /**
     * Visit absence requirement.
     *
     * @param requirement the requirement
     */
    public void visitAbsenceRequirement(AbsenceRequirement requirement);

    /**
     * Visit bounded existence requirement.
     *
     * @param requirement the requirement
     */
    public void visitBoundedExistenceRequirement(BoundedExistenceRequirement requirement);

    /**
     * Visit existence requirement.
     *
     * @param requirement the requirement
     */
    public void visitExistenceRequirement(ExistenceRequirement requirement);

    /**
     * Visit precedence chain 12 requirement.
     *
     * @param requirement the requirement
     */
    public void visitPrecedenceChain12Requirement(PrecedenceChain12Requirement requirement);

    /**
     * Visit precedence chain 21 requirement.
     *
     * @param requirement the requirement
     */
    public void visitPrecedenceChain21Requirement(PrecedenceChain21Requirement requirement);

    /**
     * Visit precedence requirement.
     *
     * @param requirement the requirement
     */
    public void visitPrecedenceRequirement(PrecedenceRequirement requirement);

    /**
     * Visit response chain 12 requirement.
     *
     * @param requirement the requirement
     */
    public void visitResponseChain12Requirement(ResponseChain12Requirement requirement);

    /**
     * Visit response chain 21 requirement.
     *
     * @param requirement the requirement
     */
    public void visitResponseChain21Requirement(ResponseChain21Requirement requirement);

    /**
     * Visit response requirement.
     *
     * @param requirement the requirement
     */
    public void visitResponseRequirement(ResponseRequirement requirement);

    /**
     * Visit universality requirement.
     *
     * @param requirement the requirement
     */
    public void visitUniversalityRequirement(UniversalityRequirement requirement);

    /**
     * Visit invariant requirement.
     *
     * @param requirement the requirement
     */
    public void visitInvariantRequirement(InvariantRequirement requirement);

    /**
     * Visit constrained chain 12 requirement.
     *
     * @param requriement the requriement
     */
    public void visitConstrainedChain12Requirement(ConstrainedChain12Requriement requriement);

}
