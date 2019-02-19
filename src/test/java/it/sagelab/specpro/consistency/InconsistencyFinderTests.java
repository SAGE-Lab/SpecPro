package it.sagelab.specpro.consistency;

import it.sagelab.specpro.reasoners.ModelChecker;
import it.sagelab.specpro.reasoners.NuSMV;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.psp.Requirement;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InconsistencyFinderTests {

    @ParameterizedTest
    //@ValueSource(strings = {"test7.req", "fault1.req", "fault2.req", "fault3.req", "fault4.req", "fault5.req", "fault6.req"})
    @ValueSource(strings = {"inconsistency1.req", "inconsistency2.req"})
    @EnabledIfEnvironmentVariable(named = "SPECPRO_NUSMV", matches = ".*")
    public void testConsistencyWithNuSMV(String filePath) throws IOException {
        ModelChecker mc = new NuSMV(30);
        Snl2FlParser parser = new Snl2FlParser();
        ConsistencyChecker cc = new ConsistencyChecker(mc, parser, "test.nusmv");

        BinaryInconsistencyFinder incFinder = new BinaryInconsistencyFinder(cc);

        File inputFile = new File(getClass().getResource("/" + filePath).getFile());
        parser.parseFile(inputFile.getAbsolutePath());

        List<Requirement> requirementList = incFinder.run();

//        for(Requirement r : requirementList)
//            System.out.println(r.getText());

    }


}

