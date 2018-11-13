package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.coverage.BACoverage;
import it.sagelab.specpro.atg.coverage.TransitionCoverage;
import it.sagelab.specpro.atg.pipes.BAProductTestPipe;
import it.sagelab.specpro.atg.pipes.InputVarsTestPipe;
import it.sagelab.specpro.atg.pipes.TestPipe;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.psp.Requirement;
import it.sagelab.specpro.reasoners.translators.spot.LTL2BA;
import it.sagelab.specpro.reasoners.translators.spot.SpotTranslator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.stream.Collectors.toSet;

public class AutomaticTestGenerator {

    /** Public Properties **/
    private int minLength, maxLength;
    private boolean filterInputVars;
    private boolean addNegatedInput;
    private BACoverage coverageCriterion;

    /** Internal use only data **/
    private ArrayList<BuchiAutomaton> buchiAutomata = new ArrayList<>();
    private Set<String> inputVars;
    private Map<BuchiAutomaton, Set<TestSequence>> generatedTests;
    private PrintStream cout;


    public AutomaticTestGenerator() {
        this(2, 10);
    }

    public AutomaticTestGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        coverageCriterion = new TransitionCoverage();
        inputVars = new HashSet<>();
        addNegatedInput = true;
        filterInputVars = true;
    }

    public void setCoverageCriterion(BACoverage coverageCriterion) {
        this.coverageCriterion = coverageCriterion;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isFilterInputVars() {
        return filterInputVars;
    }

    public void setFilterInputVars(boolean filterInputVars) {
        this.filterInputVars = filterInputVars;
    }

    public boolean isAddNegatedInput() {
        return addNegatedInput;
    }

    public void setAddNegatedInput(boolean addNegatedInput) {
        this.addNegatedInput = addNegatedInput;
    }

    public void addFormula(String ltlFormula) {
        final LTL2BA ltl2ba = new LTL2BA();

        buchiAutomata.add(ltl2ba.translate(ltlFormula));
    }

    public void parseRequirements(String filePath) throws IOException {
        parseRequirements(filePath, false);
    }

    public void parseRequirements(String filePath, boolean conjunctionBA) throws IOException {

        Snl2FlParser parser = new Snl2FlParser();
        parser.parseFile(filePath);

        if(conjunctionBA) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
                parser.translate(new SpotTranslator(), ps);
                addFormula(new String(baos.toByteArray(), StandardCharsets.UTF_8));
            }
        } else {

            List<Requirement> requirements = new ArrayList<>(parser.getRequirements());
            for (Requirement r : requirements) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (PrintStream ps = new PrintStream(baos, true, "UTF-8")) {
                    parser.getRequirements().clear();
                    parser.getRequirements().add(r);
                    parser.translate(new SpotTranslator(), ps);

                    String ltlFormula = new String(baos.toByteArray(), StandardCharsets.UTF_8);

                    addFormula(ltlFormula);
                }
            }
        }

        inputVars = parser.getBuilder().getContext().getInputVariables().stream().map(var -> var.getLabel()).collect(toSet());
    }

    public Map<BuchiAutomaton, Set<TestSequence>> generate() {
        return generate(System.out);
    }

    public Map<BuchiAutomaton, Set<TestSequence>> generate(PrintStream printStream)  {
        generatedTests = new HashMap<>();

        cout = printStream;

        int count = 0;
        for(BuchiAutomaton ba: buchiAutomata) {
            cout.println("Generating paths for req # " + (++count) + "/" + buchiAutomata.size());
            generate(ba);
            cout.println("*****************************************************************************");
        }


        return generatedTests;
    }

    private void generate(BuchiAutomaton ba) {
        List<TestPipe> pipes = getPipes(ba);
        coverageCriterion.reset(ba);
        generatedTests.putIfAbsent(ba, new HashSet<>());

        int length = minLength;
        Iterator<List<Edge>> itr = ba.iterator(length);
        while(!coverageCriterion.covered() && length <= maxLength) {
            if(itr.hasNext()) {
                List<Edge> path = itr.next();
                if(coverageCriterion.evaluate(path)) {
                    generatedTests.get(ba).addAll(evaluate(path, pipes));
                }
            } else {
                itr = ba.iterator(++length);
            }
        }

    }

    private Set<TestSequence> evaluate(List<Edge> path, List<TestPipe> pipes) {
        Set<TestSequence> tests = new HashSet<>();
        for(List<Assignment> test: new SequenceBuilder<Assignment>(path)) {
            for(TestPipe pipe: pipes) {
                test = pipe.process(test);
            }

            if(test != null) {
                coverageCriterion.accept(path, test);
                tests.add(new TestSequence(path, test));
                cout.println(test);

                if(coverageCriterion.covered(path)) {
                    break;
                }
            }
        }
        return tests;
    }

    private List<TestPipe> getPipes(BuchiAutomaton ba) {
        List<TestPipe> pipes = new ArrayList<>();
        if(filterInputVars && inputVars.size() > 0)
            pipes.add(new InputVarsTestPipe(inputVars, addNegatedInput));
        ArrayList<BuchiAutomaton> automataList = new ArrayList<>(buchiAutomata);
        automataList.remove(ba);
        pipes.add(new BAProductTestPipe(automataList));
        return pipes;
    }

}
