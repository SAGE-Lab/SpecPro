package it.sagelab.specpro.consistency;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsistencyCheckerTests {


    @ParameterizedTest
    @ValueSource(strings = {"test1.req", "test3.req", "test4.req", "test5.req", "test6.req", "test7.req", "test8.req"})
    @EnabledIfEnvironmentVariable(named = "SPECPRO_NUSMV", matches = ".*")
    public void testConsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(60);
        Snl2FlParser parser = new Snl2FlParser();
        ConsistencyChecker cc = new ConsistencyChecker(mc, parser, "test.nusmv");

        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        parser.parseFile(inputFile.getAbsolutePath());

        ConsistencyChecker.Result result = cc.run();
        assertEquals(ConsistencyChecker.Result.CONSISTENT, result, mc.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test2.req", "test9.req", "inconsistency1.req", "inconsistency2.req"})
    @EnabledIfEnvironmentVariable(named = "SPECPRO_NUSMV", matches = ".*")
    public void testInconsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(60);
        Snl2FlParser parser = new Snl2FlParser();
        ConsistencyChecker cc = new ConsistencyChecker(mc, parser, "test.nusmv");

        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        parser.parseFile(inputFile.getAbsolutePath());

        ConsistencyChecker.Result result = cc.run();
        assertEquals(ConsistencyChecker.Result.INCONSISTENT, result, mc.getMessage());
    }

}
