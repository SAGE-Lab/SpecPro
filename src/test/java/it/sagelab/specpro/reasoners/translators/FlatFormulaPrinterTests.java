package it.sagelab.specpro.reasoners.translators;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.BinaryOperator;
import it.sagelab.specpro.models.ltl.UnaryOperator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlatFormulaPrinterTests {

    @Test
    public void testPrintFormula() {
        BinaryOperator f0 = new BinaryOperator(new Atom("a"),
                                                new UnaryOperator(new Atom("b"), UnaryOperator.Operator.NOT),
                                                BinaryOperator.Operator.AND);

        BinaryOperator f1 = new BinaryOperator(f0, new Atom("c"), BinaryOperator.Operator.OR);
        BinaryOperator f2 = new BinaryOperator(new UnaryOperator(new Atom("a"), UnaryOperator.Operator.NOT),
                                                new UnaryOperator(new Atom("c"), UnaryOperator.Operator.NOT),
                                                BinaryOperator.Operator.AND);

        BinaryOperator f3 = new BinaryOperator(f1, f2, BinaryOperator.Operator.UNTIL);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true);
        FlatFormulaPrinter formulaPrinter = new FlatFormulaPrinter(ps);
        formulaPrinter.print(f3);
        String output = baos.toString();
        assertEquals("((a & ! b) | c) U (! a & ! c)", output);
    }
}
