package it.sagelab.specpro.fe;

import it.sagelab.specpro.models.InputRequirement;
import it.sagelab.specpro.models.ltl.LTLDcSpec;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;
import it.sagelab.specpro.models.psp.qualitative.QualitativeRequirement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PSPFrontEndTests {


    @Test
    public void testParseGloballyUniversalityPattern() {
        String pattern = "Globally, it is always the case that X holds.";

        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseString(pattern);

        assertNotNull(spec.getRequirements());
        assertEquals(1, spec.getRequirements().size());

        checkGloballyUniversalityPattern(spec, "REQ1");
    }

    @Test
    public void testParseGloballyUniversalityPatternWithoutScope() {
        String pattern = "It is always the case that X holds.";

        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseString(pattern);

        checkGloballyUniversalityPattern(spec, "REQ1");
    }

    @Test
    public void testParseGloballyUniversalityPatternWithReqID() {
        PSPFrontEnd fe = new PSPFrontEnd();

        String pattern = "[ReqId=R1] Globally, it is always the case that X holds.";
        LTLSpec spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R1");

        pattern = "[REQID=R2] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R2");

        pattern = "[ID=R3] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R3");

        pattern = "[id=R4] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R4");

        pattern = "[Id=R5] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R5");

        pattern = "[R6] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R6");

        pattern = "[reqid=R7] Globally, it is always the case that X holds.";
        spec = fe.parseString(pattern);
        checkGloballyUniversalityPattern(spec, "R7");
    }

    @Test
    public void testExistencePatternAfterScopeWithNumericVariables() {
        String pattern = "After X > 0, Y < 2 and Z eventually holds.";
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseString(pattern);
        assertEquals(2, spec.getInvariants().size());
        assertEquals(5, spec.getAtoms().size());
        assertEquals(1, spec.getRequirements().size());
        InputRequirement req = spec.getInputRequirement(0);
        assertNotNull(req);
        assertTrue(req instanceof QualitativeRequirement);
        QualitativeRequirement qReq = (QualitativeRequirement) req;
        assertEquals(Scope.Type.AFTER, qReq.getScope().getType());
        assertEquals(3, qReq.getVariables().size());

        assertEquals("(!(_lower_X0) | !(_equal_X0))", spec.getInvariants().get(0).toString());
        assertEquals("(!(_lower_Y0) | !(_equal_Y0))", spec.getInvariants().get(1).toString());
        assertEquals("([](!((!(_lower_X0) & !(_equal_X0)))) | <>(((!(_lower_X0) & !(_equal_X0)) & <>((_lower_Y0 & Z)))))",
                spec.getRequirements().get(0).toString());

        assertTrue(spec instanceof LTLDcSpec);
        LTLDcSpec dcSpec = (LTLDcSpec) spec;
        assertEquals(2, dcSpec.getNumericVariableNames().size());
        assertEquals(1, dcSpec.getThresholds("X").size());
        assertEquals(1, dcSpec.getThresholds("Y").size());
    }

    @Test
    public void testInvariantPatternBetweenScopeWithNumericalVariables() {
        String pattern = "Between X > 0 and X < 10, it is always the case that if X != 5 holds, then Z holds as well.";
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseString(pattern);
        assertEquals(15, spec.getInvariants().size());
        assertEquals(7, spec.getAtoms().size());
        assertEquals(1, spec.getRequirements().size());
        InputRequirement req = spec.getInputRequirement(0);
        assertNotNull(req);
        assertTrue(req instanceof QualitativeRequirement);
        QualitativeRequirement qReq = (QualitativeRequirement) req;
        assertEquals(Scope.Type.BETWEEN, qReq.getScope().getType());
        assertEquals(2, qReq.getVariables().size());

        assertEquals("(!(_lower_X0) | !(_equal_X0))", spec.getInvariants().get(0).toString());
        assertEquals("(!(_lower_X0) | !(_lower_X2))", spec.getInvariants().get(1).toString());
        assertEquals("(!(_lower_X0) | !(_equal_X2))", spec.getInvariants().get(2).toString());
        assertEquals("(!(_lower_X0) | !(_lower_X1))", spec.getInvariants().get(3).toString());
        assertEquals("(!(_lower_X0) | !(_equal_X1))", spec.getInvariants().get(4).toString());
        assertEquals("(!(_equal_X0) | !(_lower_X2))", spec.getInvariants().get(5).toString());
        assertEquals("(!(_equal_X0) | !(_equal_X2))", spec.getInvariants().get(6).toString());
        assertEquals("(!(_equal_X0) | !(_lower_X1))", spec.getInvariants().get(7).toString());
        assertEquals("(!(_equal_X0) | !(_equal_X1))", spec.getInvariants().get(8).toString());
        assertEquals("(!(_lower_X2) | !(_equal_X2))", spec.getInvariants().get(9).toString());
        assertEquals("(!(_lower_X2) | !(_lower_X1))", spec.getInvariants().get(10).toString());
        assertEquals("(!(_lower_X2) | !(_equal_X1))", spec.getInvariants().get(11).toString());
        assertEquals("(!(_equal_X2) | !(_lower_X1))", spec.getInvariants().get(12).toString());
        assertEquals("(!(_equal_X2) | !(_equal_X1))", spec.getInvariants().get(13).toString());
        assertEquals("(!(_lower_X1) | !(_equal_X1))", spec.getInvariants().get(14).toString());

        assertEquals("[](((((!(_lower_X0) & !(_equal_X0)) & !(((((_lower_X0 | _equal_X0) | _lower_X2) | _equal_X2) | _lower_X1)))" +
                        " & <>(((((_lower_X0 | _equal_X0) | _lower_X2) | _equal_X2) | _lower_X1))) -> " +
                        "((!(!(_equal_X2)) | Z) U ((((_lower_X0 | _equal_X0) | _lower_X2) | _equal_X2) | _lower_X1))))",
                spec.getRequirements().get(0).toString());

        assertTrue(spec instanceof LTLDcSpec);
        LTLDcSpec dcSpec = (LTLDcSpec) spec;
        assertEquals(1, dcSpec.getNumericVariableNames().size());
        assertEquals(3, dcSpec.getThresholds("X").size());
        assertEquals(Arrays.asList(0.0f, 5.0f, 10.0f), dcSpec.getThresholds("X"));
    }


    /* Help methods */

    private void checkGloballyUniversalityPattern(LTLSpec spec, String reqId) {
        assertNotNull(spec.getRequirements());
        assertEquals(1, spec.getRequirements().size());
        Requirement r = (Requirement) spec.getInputRequirement(0);

        assertEquals(reqId, r.getReqId());
        Assertions.assertEquals(Scope.Type.GLOBALLY, r.getScope().getType());
        assertEquals(Collections.emptyList(), r.getScope().getExpressions());
        assertEquals(1, r.getExpressions().size());
        assertTrue(r.getExpressions().get(0) instanceof VariableExpression);
        assertEquals(((VariableExpression)r.getExpressions().get(0)).getType(), VariableExpression.Type.BOOLEAN);
        assertEquals("X", ((VariableExpression) r.getExpressions().get(0)).getLabel());
    }

}
