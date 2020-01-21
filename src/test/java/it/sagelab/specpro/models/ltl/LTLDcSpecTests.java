package it.sagelab.specpro.models.ltl;

import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.psp.Scope;
import it.sagelab.specpro.models.psp.expressions.CompareExpression;
import it.sagelab.specpro.models.psp.expressions.NumberExpression;
import it.sagelab.specpro.models.psp.expressions.VariableExpression;
import it.sagelab.specpro.models.psp.qualitative.ExistenceRequirement;
import it.sagelab.specpro.models.psp.qualitative.ResponseRequirement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LTLDcSpecTests {

    @Test
    public void testsLTLDcSpecInitialization() {

        VariableExpression x = new VariableExpression("X", VariableExpression.Type.FLOAT);
        VariableExpression y = new VariableExpression("Y", VariableExpression.Type.FLOAT);
        CompareExpression c1 = new CompareExpression(x, new NumberExpression(0f), CompareExpression.Operator.GREATER);
        CompareExpression c2 = new CompareExpression(x, new NumberExpression(5.0f), CompareExpression.Operator.EQUAL);
        CompareExpression c3 = new CompareExpression(x, new NumberExpression(10.f), CompareExpression.Operator.LOWER_EQUAL);
        CompareExpression c4 = new CompareExpression(new NumberExpression(2.0f), y, CompareExpression.Operator.LOWER);
        Scope afterC1= new Scope(Scope.Type.AFTER, Arrays.asList(c1));
        Scope globally = new Scope(Scope.Type.GLOBALLY, Collections.emptyList());
        ResponseRequirement req1 = new ResponseRequirement(afterC1, Arrays.asList(c2, c4));
        ExistenceRequirement req2 = new ExistenceRequirement(globally, c3);


        LTLDcSpec spec = new LTLDcSpec(Arrays.asList(req1, req2));
        assertEquals(2, spec.getNumericVariableNames().size());
        assertTrue(spec.getNumericVariableNames().contains("X"));
        assertTrue(spec.getNumericVariableNames().contains("Y"));

        assertEquals(Arrays.asList(0.0f, 5.0f, 10.f), spec.getThresholds("X"));
        assertEquals(Arrays.asList(2.f), spec.getThresholds("Y"));
    }


    @Test
    public void testNumber2BoolAssignment() {
        VariableExpression x = new VariableExpression("X", VariableExpression.Type.FLOAT);
        VariableExpression y = new VariableExpression("Y", VariableExpression.Type.FLOAT);
        CompareExpression c1 = new CompareExpression(x, new NumberExpression(0f), CompareExpression.Operator.GREATER);
        CompareExpression c2 = new CompareExpression(x, new NumberExpression(5.0f), CompareExpression.Operator.EQUAL);
        CompareExpression c3 = new CompareExpression(x, new NumberExpression(10.f), CompareExpression.Operator.LOWER_EQUAL);
        CompareExpression c4 = new CompareExpression(new NumberExpression(2.0f), y, CompareExpression.Operator.LOWER);
        Scope afterC1= new Scope(Scope.Type.AFTER, Arrays.asList(c1));
        Scope globally = new Scope(Scope.Type.GLOBALLY, Collections.emptyList());
        ResponseRequirement req1 = new ResponseRequirement(afterC1, Arrays.asList(c2, c4));
        ExistenceRequirement req2 = new ExistenceRequirement(globally, c3);


        LTLDcSpec spec = new LTLDcSpec(Arrays.asList(req1, req2));

        Assignment ass1 = spec.getDcAssignment("X", 2.f);
        assertEquals(false, ass1.getValue(spec.getLowerAtom("X", 0f)));
        assertEquals(false, ass1.getValue(spec.getEqualAtom("X", 0f)));
        assertEquals(true, ass1.getValue(spec.getLowerAtom("X", 5.0f)));
        assertEquals(false, ass1.getValue(spec.getEqualAtom("X", 5.0f)));
        assertEquals(false, ass1.getValue(spec.getLowerAtom("X", 10f)));
        assertEquals(false, ass1.getValue(spec.getEqualAtom("X", 10f)));

        Assignment ass2 = spec.getDcAssignment("X", -10.f);
        assertEquals(true, ass2.getValue(spec.getLowerAtom("X", 0f)));
        assertEquals(false, ass2.getValue(spec.getEqualAtom("X", 0f)));
        assertEquals(false, ass2.getValue(spec.getLowerAtom("X", 5.0f)));
        assertEquals(false, ass2.getValue(spec.getEqualAtom("X", 5.0f)));
        assertEquals(false, ass2.getValue(spec.getLowerAtom("X", 10f)));
        assertEquals(false, ass2.getValue(spec.getEqualAtom("X", 10f)));

        Assignment ass3 = spec.getDcAssignment("X", 10.f);
        assertEquals(false, ass3.getValue(spec.getLowerAtom("X", 0f)));
        assertEquals(false, ass3.getValue(spec.getEqualAtom("X", 0f)));
        assertEquals(false, ass3.getValue(spec.getLowerAtom("X", 5.0f)));
        assertEquals(false, ass3.getValue(spec.getEqualAtom("X", 5.0f)));
        assertEquals(false, ass3.getValue(spec.getLowerAtom("X", 10f)));
        assertEquals(true, ass3.getValue(spec.getEqualAtom("X", 10f)));

        Assignment ass4 = spec.getDcAssignment("X", 10.1f);
        assertEquals(false, ass4.getValue(spec.getLowerAtom("X", 0f)));
        assertEquals(false, ass4.getValue(spec.getEqualAtom("X", 0f)));
        assertEquals(false, ass4.getValue(spec.getLowerAtom("X", 5.0f)));
        assertEquals(false, ass4.getValue(spec.getEqualAtom("X", 5.0f)));
        assertEquals(false, ass4.getValue(spec.getLowerAtom("X", 10f)));
        assertEquals(false, ass4.getValue(spec.getEqualAtom("X", 10f)));
    }
}
