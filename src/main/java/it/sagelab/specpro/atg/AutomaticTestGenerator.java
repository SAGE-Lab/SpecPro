package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.coverage.BACoverage;
import it.sagelab.specpro.atg.coverage.ConditionCoverage;
import it.sagelab.specpro.atg.coverage.StateCoverage;
import it.sagelab.specpro.atg.coverage.TransitionCoverage;
import it.sagelab.specpro.atg.pipes.BAProductTestPipe;
import it.sagelab.specpro.atg.pipes.InputVarsTestPipe;
import it.sagelab.specpro.atg.pipes.TestPipe;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.models.ltl.Atom;
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

    private final HashSet<List<Assignment>> uniqueAssignments = new HashSet<>();

    private PrintStream cout;


    public AutomaticTestGenerator() {
        this(2, 10);
    }

    public void debug() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        Assignment a1 = new Assignment();
        a1.add(new Atom("state_init"), true);
        a1.add(new Atom("object_detected"), true);
        a1.add(new Atom("alarm_button_pressed"), false);
        a1.add(new Atom("proximity_sensor"), false);
        a1.add(new Atom("ef_open"), true);
        a1.add(new Atom("ef_idle"), true);
        a1.add(new Atom("arm_idle"), true);

        Assignment a2 = new Assignment();
        a2.add(new Atom("state_scanning"), true);
        a2.add(new Atom("object_detected"), true);
        a2.add(new Atom("alarm_button_pressed"), false);
        a2.add(new Atom("proximity_sensor"), false);
        a2.add(new Atom("ef_idle"), true);
        a2.add(new Atom("arm_idle"), true);


        Assignment a3 = new Assignment();
        a3.add(new Atom("state_moving_to_target"), true);
        a3.add(new Atom("object_detected"), true);
        a3.add(new Atom("alarm_button_pressed"), false);
        a3.add(new Atom("proximity_sensor"), false);
        a3.add(new Atom("arm_idle"), false);
        a3.add(new Atom("arm_moving"), true);

        Assignment a4 = new Assignment();
        a4.add(new Atom("state_target_reached"), true);
        a4.add(new Atom("object_detected"), true);
        a4.add(new Atom("alarm_button_pressed"), false);
        a4.add(new Atom("proximity_sensor"), false);
        a4.add(new Atom("arm_idle"), true);
        a4.add(new Atom("arm_moving"), false);

        Assignment a5 = new Assignment();
        a5.add(new Atom("state_grabbing"), true);
        a5.add(new Atom("object_detected"), true);
        a5.add(new Atom("alarm_button_pressed"), false);
        a5.add(new Atom("proximity_sensor"), false);

        Assignment a6 = new Assignment();
        a6.add(new Atom("state_init"), true);
        a6.add(new Atom("object_detected"), true);
        a6.add(new Atom("alarm_button_pressed"), false);
        a6.add(new Atom("proximity_sensor"), false);

        assignments.add(a1);
        assignments.add(a2);
        assignments.add(a3);
        assignments.add(a4);
        assignments.add(a5);
        assignments.add(a6);

        BAProductTestPipe pipe = new BAProductTestPipe(buchiAutomata);
        List<Assignment> test = pipe.process(assignments);

        System.out.println(test);
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
            long startTime = System.nanoTime();
            generate(ba);
            printStatistics(ba);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double seconds = (double)elapsedTime / 1_000_000_000.0;
            cout.println("Current Time: " + System.currentTimeMillis());
            cout.println(String.format("Elapsed Time: %.5f", seconds));
            cout.println("*****************************************************************************");
        }

        cout.println("Different tests generated: " + uniqueAssignments.size());
        uniqueAssignments.forEach(l -> cout.println(l));


        return generatedTests;
    }

    public void computeCrossCoverageWithConjBA(String filePath) throws IOException {
        ArrayList<BuchiAutomaton> oldBAs = new ArrayList<>(buchiAutomata);
        buchiAutomata.clear();
        parseRequirements(filePath, true);
        cout.println("\n\n");
        cout.println("*** Cross Coverage Statistics *** ");
        coverageCriterion.reset(buchiAutomata.get(0));
        generatedTests.put(buchiAutomata.get(0), new HashSet<>());
        updateCoverage(buchiAutomata.get(0));
        printStatistics(buchiAutomata.get(0));
        buchiAutomata = oldBAs;
    }



    private void generate(BuchiAutomaton ba) {
        List<TestPipe> pipes = getPipes(ba);
        coverageCriterion.reset(ba);
        generatedTests.putIfAbsent(ba, new HashSet<>());
        updateCoverage(ba);

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

    private void updateCoverage(BuchiAutomaton ba) {
        BAExplorer baExplorer = new BAExplorer();
        baExplorer.addAcceptanceCondition(new LassoShapedAcceptanceCondition());
        Set<TestSequence> testSet = generatedTests.get(ba);
            for(List<Assignment> test: uniqueAssignments) {
                baExplorer.setLength(test.size());
                Trie<Edge> inducedPaths = baExplorer.findInducedPaths(ba, test);

                if(inducedPaths.size() == 0) {
                    cout.println("** WARN: No induced path for test " + test);
                    cout.println("*****************************************************************************");
                }

                for(List<Edge> path: inducedPaths) {
                    coverageCriterion.accept(path, test);
                    testSet.add(new TestSequence(new ArrayList<>(path), test));
                    cout.println("** Test already generated **");
                    cout.println(path);
                    cout.println(test);
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
                tests.add(new TestSequence(new ArrayList<>(path), test));
                uniqueAssignments.add(test);
                cout.println(path);
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

    private void printStatistics(BuchiAutomaton ba) {
        cout.println("###############################");
        cout.println("Stats");
        cout.println("# Vertexes:   " + ba.vertexSet().size());
        cout.println("# Edges:      " + ba.edgeSet().size());
        cout.println("# Conditions: " + ba.edgeSet().stream().map(e -> e.getAssigments()).mapToInt(Set::size).sum());

        Set<TestSequence> tests = generatedTests.get(ba);
        StateCoverage sc = new StateCoverage(ba);
        TransitionCoverage tc = new TransitionCoverage(ba);
        ConditionCoverage cc = new ConditionCoverage(ba);
        for(TestSequence t : tests) {
            sc.accept(t.getPath(), t.getAssignmentList());
            tc.accept(t.getPath(), t.getAssignmentList());
            cc.accept(t.getPath(), t.getAssignmentList());
        }

        cout.println("State Coverage:      " + sc.coverage());
        cout.println("Transition Coverage: " + tc.coverage());
        cout.println("Condition Coverage:  " + cc.coverage());
        cout.println("Target Coverage:     " + coverageCriterion.coverage());
        cout.println("###############################");
    }

}
