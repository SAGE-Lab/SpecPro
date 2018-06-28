package it.sagelab.fe.ltl;

import it.sagelab.models.ltl.LTLContext;
import org.junit.jupiter.api.Test;
import it.sagelab.models.ltl.elements.Atom;
import it.sagelab.fe.ltl.patterns.Pattern;
import it.sagelab.models.psp.expressions.VariableExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LTLContextTests {

    @Test
    public void testGetPattern() {

        Map<String, VariableExpression> symbolTable = new HashMap<>();
        Map<String, TreeMap<Float, Atom[]>> rangeMap = new HashMap<>();
        Map<String, Pattern> patterns = null;

        try {
            patterns = Pattern.loadPatterns(Pattern.PATTERNS_FILE);
        }
        catch(Exception e) { e.printStackTrace(); }

        assertNotNull(patterns);

        LTLContext context = new LTLContext(symbolTable, rangeMap, patterns);

        for(String s: patterns.keySet())
            assertEquals(context.getPattern(s), patterns.get(s));

    }
}
