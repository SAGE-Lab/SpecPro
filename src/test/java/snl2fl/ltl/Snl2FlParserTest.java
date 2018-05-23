package snl2fl.ltl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.jupiter.api.Test;
import snl2fl.Snl2FlParser;
import snl2fl.Snl2FlTranslator;
import snl2fl.ltl.aalta.AALTATranslator;
import snl2fl.ltl.nusmv.NuSMVTranslator;
import snl2fl.req.expressions.BooleanVariableExpression;
import snl2fl.req.requirements.Requirement;
import snl2fl.req.requirements.Scope;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Snl2FlParserTest {


    @Test
    public void translateFileTest1ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test1.req", "test1.nusmv");
    }

    @Test
    public void translateFileTest1ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test1.req", "test1.aalta");
    }

    @Test
    public void translateFileTest2ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test2.req", "test2.nusmv");
    }

    @Test
    public void translateFileTest2ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test2.req", "test2.aalta");
    }

    @Test
    public void translateFileTest3ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test3.req", "test3.nusmv");
    }

    @Test
    public void translateFileTest3ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test3.req", "test3.aalta");
    }

    @Test
    public void translateFileTest4ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test4.req", "test4.nusmv");
    }

    @Test
    public void translateFileTest4ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test4.req", "test4.aalta");
    }

    @Test
    public void translateFileTest5ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test5.req", "test5.nusmv");
    }

    @Test
    public void translateFileTest5ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test5.req", "test5.aalta");
    }

    @Test
    public void translateFileTest6ForNuSMV() throws IOException {
        translateAndCheck(new NuSMVTranslator(), "test6.req", "test6.nusmv");
    }

    @Test
    public void translateFileTest6ForAalta() throws IOException {
        translateAndCheck(new AALTATranslator(), "test6.req", "test6.aalta");
    }


//    @Test
//    public void parseFromFile() throws IOException {
//        Snl2FlParser parser = new Snl2FlParser();
//        File dir = new File(getClass().getResource("/data").getFile());
//
//        FileFilter reqFilter = new WildcardFileFilter("*.req");
//        Snl2FlTranslator nuSMVTranslator = new NuSMVTranslator();
//
//        File[] files = dir.listFiles(reqFilter);
//        for(File file : files) {
//            parser.reset().parseFile(file.getAbsolutePath());
//
//            String fileName = FilenameUtils.removeExtension(file.getName());
//
//            FileFilter nusmvFilter = new WildcardFileFilter(fileName + ".nusmv");
//            File[] nusmvFiles = dir.listFiles(nusmvFilter);
//
//            if (nusmvFiles.length > 0) {
//                System.out.println("Testing nusmv translation of " +file.getName());
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                parser.translate(nuSMVTranslator, stream);
//                assertEquals(FileUtils.readFileToString(nusmvFiles[0]), stream.toString());
//            }
//
//
//        }
//
//
//    }

    @Test
    public void parseFromFileWrongPath() {
        assertThrows(IOException.class, () -> { new Snl2FlParser().parseFile("/wrongPath/file.req"); });
    }

    @Test
    public void parseGloballyUniversalityPattern() {
        String pattern = "Globally, it is always the case that X holds.";

        Snl2FlParser parser = new Snl2FlParser();

        parser.parseString(pattern);

        assertNotNull(parser.getRequirements());
        assertEquals(1, parser.getRequirements().size());

        checkGloballyUniversalityPattern(parser, "REQ1");
    }

    @Test
    public void parseGloballyUniversalityPatternWithoutScope() {
        String pattern = "It is always the case that X holds.";

        Snl2FlParser parser = new Snl2FlParser();

        parser.parseString(pattern);

        checkGloballyUniversalityPattern(parser, "REQ1");
    }

    @Test
    public void parseGloballyUniversalityPatternWithReqID() {
        Snl2FlParser parser = new Snl2FlParser();

        String pattern = "[ReqId=R1] Globally, it is always the case that X holds.";
        parser.parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R1");

        pattern = "[REQID=R2] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R2");

        pattern = "[ID=R3] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R3");

        pattern = "[id=R4] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R4");

        pattern = "[Id=R5] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R5");

        pattern = "[R6] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R6");

        pattern = "[reqid=R7] Globally, it is always the case that X holds.";
        parser.reset().parseString(pattern);
        checkGloballyUniversalityPattern(parser, "R7");
    }

    /* Help methods */

    private void translateAndCheck(Snl2FlTranslator translator, String inputFileName, String outputFileName) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        File inputFile = new File(getClass().getResource("/" + inputFileName).getFile());
        new Snl2FlParser().parseFile(inputFile.getAbsolutePath()).translate(translator, stream);

        System.out.println(stream.toString());

        File outputFile = new File(getClass().getResource("/" + outputFileName).getFile());
        assertEquals(FileUtils.readFileToString(outputFile), stream.toString());

    }

    private void checkGloballyUniversalityPattern(Snl2FlParser parser, String reqId) {
        assertNotNull(parser.getRequirements());
        assertEquals(1, parser.getRequirements().size());
        Requirement r = parser.getRequirement(0);

        assertEquals(reqId, r.getReqId());
        assertEquals(Scope.Type.GLOBALLY, r.getScope().getType());
        assertEquals(Collections.emptyList(), r.getScope().getExpressions());
        assertEquals(1, r.getExpressions().size());
        assertTrue(r.getExpressions().get(0) instanceof BooleanVariableExpression);
        assertEquals("X", ((BooleanVariableExpression) r.getExpressions().get(0)).getName());
    }

}
