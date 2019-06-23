package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.reasoners.LTL2BA;
import it.sagelab.specpro.reasoners.translators.SpotTranslator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Set;

public abstract class LTLTestGenerator {

    protected final ArrayList<BuchiAutomaton> buchiAutomata = new ArrayList<>();

    /**
     * Generates a set of test sequences starting from the input ltl specification
     * @param spec The input ltl specification
     * @return The test suite generated
     */
    public abstract Set<TestSequence> generate(LTLSpec spec) throws IOException;


    public void addFormula(String ltlFormula) {
        final LTL2BA ltl2ba = new LTL2BA();

        buchiAutomata.add(ltl2ba.translate(ltlFormula));
    }

    public void parseRequirements(LTLSpec spec, boolean conjunctionBA) throws IOException {

        SpotTranslator translator = new SpotTranslator();

        if(conjunctionBA) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {

                translator.translate(ps, spec);
                addFormula(new String(baos.toByteArray(), StandardCharsets.UTF_8));
            }
        } else {


            for (Formula f: spec.getRequirements()) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
                    LTLSpec newSpec = new LTLSpec();
                    newSpec.addRequirement(f);
                    translator.translate(ps, newSpec);
                    String ltlFormula = new String(baos.toByteArray(), StandardCharsets.UTF_8);

                    addFormula(ltlFormula);
                }
            }
        }
    }

    public void expandTransitions() {
        buchiAutomata.forEach(BuchiAutomaton::expandImplicitTransitions);
    }


}
