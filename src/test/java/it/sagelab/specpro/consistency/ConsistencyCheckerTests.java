package it.sagelab.specpro.consistency;

import it.sagelab.specpro.fe.PSPFrontEnd;
import it.sagelab.specpro.models.ltl.LTLSpec;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.fe.psp.Snl2FlParser;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsistencyCheckerTests {


    @EnabledIf("systemEnvironment.get('SPECPRO_NUSMV') != null")
    @ParameterizedTest
    @ValueSource(strings = {"test1.req", "test3.req", "test4.req", "test5.req", "test6.req", "test7.req", "test8.req"})
    public void testConsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(60);
        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseFile(inputFile.getAbsolutePath());

        Snl2FlParser parser = new Snl2FlParser();
        ConsistencyChecker cc = new ConsistencyChecker(mc, spec, "test.nusmv");

        ConsistencyChecker.Result result = cc.run();
        assertEquals(ConsistencyChecker.Result.CONSISTENT, result, mc.getMessage());
    }

    @EnabledIf("systemEnvironment.get('SPECPRO_NUSMV') != null")
    @ParameterizedTest
    @ValueSource(strings = {"test2.req", "test9.req", "inconsistency1.req", "inconsistency2.req"})
    public void testInconsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(60);
        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        PSPFrontEnd fe = new PSPFrontEnd();
        LTLSpec spec = fe.parseFile(inputFile.getAbsolutePath());
        ConsistencyChecker cc = new ConsistencyChecker(mc, spec, "test.nusmv");

        ConsistencyChecker.Result result = cc.run();
        assertEquals(ConsistencyChecker.Result.INCONSISTENT, result, mc.getMessage());
    }

}
