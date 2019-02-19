package it.sagelab.specpro.reasoners;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@EnabledIfEnvironmentVariable(named = "SPECPRO_AALTA", matches = ".*")
public class AaltaTests {

    @ParameterizedTest
    @ValueSource(strings = {"test1.aalta", "test3.aalta", "test4.aalta", "test5.aalta", "test6.aalta", "test7.aalta", "test8.aalta"})
    public void testRunSat(String fileName) throws IOException {
        Aalta mc = new Aalta(60);

        String filePath = new File(getClass().getResource("/" + fileName).getFile()).getAbsolutePath();

        assertEquals(ModelChecker.Result.SAT, mc.run(filePath), mc.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test2.aalta", "test9.aalta"})
    public void testRunUnsat(String fileName) throws IOException {
        Aalta mc = new Aalta(60);

        String filePath = new File(getClass().getResource("/" + fileName).getFile()).getAbsolutePath();

        assertEquals(ModelChecker.Result.UNSAT, mc.run(filePath), mc.getMessage());
    }

}
