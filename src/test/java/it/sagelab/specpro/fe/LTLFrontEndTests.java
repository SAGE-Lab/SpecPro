package it.sagelab.specpro.fe;

import it.sagelab.specpro.fe.psp.Snl2FlException;
import it.sagelab.specpro.models.ltl.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LTLFrontEndTests {

    @Test
    public void testParseString() {
        LTLFrontEnd frontEnd = new LTLFrontEnd();

        LTLSpec spec = frontEnd.parseString("G(x | y)\n<>(y U z)");
        assertEquals(2, spec.getRequirements().size());
        assertEquals(0, spec.getInvariants().size());
        assertEquals(3, spec.getSymbolTable().size());

        assertTrue(spec.getRequirements().get(0) instanceof UnaryOperator);
        UnaryOperator f1 = (UnaryOperator) spec.getRequirements().get(0);
        assertEquals(UnaryOperator.Operator.GLOBALLY, f1.getOperator());
        assertTrue(f1.getChild() instanceof BinaryOperator);
        BinaryOperator f2 = (BinaryOperator) f1.getChild();
        assertEquals(BinaryOperator.Operator.OR, f2.getOperator());
        assertTrue(f2.getLeftFormula() instanceof Atom);
        assertTrue(f2.getRightFormula() instanceof Atom);
        assertEquals("x", ((Atom)f2.getLeftFormula()).getName());
        assertEquals("y", ((Atom)f2.getRightFormula()).getName());

        assertTrue(spec.getRequirements().get(1) instanceof UnaryOperator);
        UnaryOperator f3 = (UnaryOperator) spec.getRequirements().get(1);
        assertEquals(UnaryOperator.Operator.EVENTUALLY, f3.getOperator());
        assertTrue(f3.getChild() instanceof BinaryOperator);
        BinaryOperator f4 = (BinaryOperator) f3.getChild();
        assertEquals(BinaryOperator.Operator.UNTIL, f4.getOperator());
        assertTrue(f4.getLeftFormula() instanceof Atom);
        assertTrue(f4.getRightFormula() instanceof Atom);
        assertEquals("y", ((Atom)f4.getLeftFormula()).getName());
        assertEquals("z", ((Atom)f4.getRightFormula()).getName());

    }

    @Test
    public void testParseStringWrongSyntax() {
        LTLFrontEnd frontEnd = new LTLFrontEnd();
        assertThrows(Snl2FlException.class, () -> {
                    LTLSpec spec = frontEnd.parseString("G(x | y)\n<(? U z)");
        });
    }

    @Test
    public void testParseFile() {
        File inputFile = new File(getClass().getResource("/input1.ltl").getFile());
        LTLFrontEnd frontEnd = new LTLFrontEnd();;
        assertDoesNotThrow(() -> {
            LTLSpec spec = frontEnd.parseFile(inputFile.getAbsolutePath());
            assertEquals(6, spec.getRequirements().size());
            assertEquals(4, spec.getSymbolTable().size());
        });

    }

    @Test
    public void testParseFileWrongPath() {
        LTLFrontEnd frontEnd = new LTLFrontEnd();
        assertThrows(IOException.class, () -> {
            LTLSpec spec = frontEnd.parseFile("wrong/path/file.txt");
        });
    }


}
