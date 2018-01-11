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
    void visitAbsenceRequirement(AbsenceRequirement requirement);

    /**
     * Visit bounded existence requirement.
     *
     * @param requirement the requirement
     */
    void visitBoundedExistenceRequirement(BoundedExistenceRequirement requirement);

    /**
     * Visit existence requirement.
     *
     * @param requirement the requirement
     */
    void visitExistenceRequirement(ExistenceRequirement requirement);

    /**
     * Visit precedence chain 12 requirement.
     *
     * @param requirement the requirement
     */
    void visitPrecedenceChain12Requirement(PrecedenceChain12Requirement requirement);

    /**
     * Visit precedence chain 21 requirement.
     *
     * @param requirement the requirement
     */
    void visitPrecedenceChain21Requirement(PrecedenceChain21Requirement requirement);

    /**
     * Visit precedence requirement.
     *
     * @param requirement the requirement
     */
    void visitPrecedenceRequirement(PrecedenceRequirement requirement);

    /**
     * Visit response chain 12 requirement.
     *
     * @param requirement the requirement
     */
    void visitResponseChain12Requirement(ResponseChain12Requirement requirement);

    /**
     * Visit response chain 21 requirement.
     *
     * @param requirement the requirement
     */
    void visitResponseChain21Requirement(ResponseChain21Requirement requirement);

    /**
     * Visit response requirement.
     *
     * @param requirement the requirement
     */
    void visitResponseRequirement(ResponseRequirement requirement);

    /**
     * Visit universality requirement.
     *
     * @param requirement the requirement
     */
    void visitUniversalityRequirement(UniversalityRequirement requirement);

    /**
     * Visit invariant requirement.
     *
     * @param requirement the requirement
     */
    void visitInvariantRequirement(InvariantRequirement requirement);

    /**
     * Visit constrained chain 12 requirement.
     *
     * @param requriement the requriement
     */
    void visitConstrainedChain12Requirement(ConstrainedChain12Requriement requriement);

}
