package it.sagelab.specpro.models.psp;

import it.sagelab.specpro.models.psp.qualitative.QualitativeRequirementVisitor;
import it.sagelab.specpro.models.psp.realtime.RealTimeRequirementVisitor;

/**
 * The Interface RequirementVisitor.
 *
 * @author Simone Vuotto
 */
public interface RequirementVisitor extends QualitativeRequirementVisitor, RealTimeRequirementVisitor {

}
