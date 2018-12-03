package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.coverage.*;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.fe.snl2fl.Snl2FlParser;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
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
    private BACoverage coverageCriterion;
    private final BAProductHandler baProductHandler;

    /** Internal use only data **/
    private ArrayList<BuchiAutomaton> buchiAutomata = new ArrayList<>();
    private Map<BuchiAutomaton, Set<TestSequence>> generatedTests;

    private final HashSet<List<Assignment>> uniqueAssignments = new HashSet<>();

    private PrintStream cout;


    public AutomaticTestGenerator() {
        this(2, 10);
    }

    public AutomaticTestGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        coverageCriterion = new TransitionCoverage();
        baProductHandler = new BAProductHandler();
    }

    public void setCoverageCriterion(BACoverage coverageCriterion) {
        this.coverageCriterion = coverageCriterion;
    }

    public BAProductHandler getBaProductHandler() {
        return baProductHandler;
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
    }

    public void expandTransitions() {
        buchiAutomata.forEach(BuchiAutomaton::expandImplicitTransitions);
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
        computeCrossCoverageWithConjBA(filePath, false);
    }

    public void computeCrossCoverageWithConjBA(String input, boolean ltlFormula) throws IOException {
        ArrayList<BuchiAutomaton> oldBAs = new ArrayList<>(buchiAutomata);
        buchiAutomata.clear();
        if (ltlFormula) {
            addFormula(input);
        } else {
            parseRequirements(input, true);
        }
        cout.println("\n\n");
        cout.println("*** Cross Coverage Statistics *** ");
        coverageCriterion.reset(buchiAutomata.get(0));
        generatedTests.put(buchiAutomata.get(0), new HashSet<>());
        updateCoverage(buchiAutomata.get(0));
        printStatistics(buchiAutomata.get(0));
        buchiAutomata = oldBAs;
    }

    private void generate(BuchiAutomaton ba) {
        ArrayList<BuchiAutomaton> automataList = new ArrayList<>(buchiAutomata);
        automataList.remove(ba);
        baProductHandler.setBuchiAutomataList(automataList);
        coverageCriterion.reset(ba);
        generatedTests.putIfAbsent(ba, new HashSet<>());
        updateCoverage(ba);

        int length = minLength;
        Iterator<List<Edge>> itr = ba.iterator(length);
        while(!coverageCriterion.covered() && length <= maxLength) {
            if(itr.hasNext()) {
                List<Edge> path = itr.next();
                if(coverageCriterion.evaluate(path)) {
                    generatedTests.get(ba).addAll(evaluate(path));
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

    private Set<TestSequence> evaluate(List<Edge> path) {
        Set<TestSequence> tests = new HashSet<>();
        List<Integer> betaIndexes = LassoShapedAcceptanceCondition.getValidBetaIndexes(path);
        if(betaIndexes.size() == 0) {
            throw new RuntimeException("The path evaluated is not lasso shaped!");
        }
        for(List<Assignment> test: new SequenceBuilder<Assignment>(path)) {
            if(!coverageCriterion.evaluateTest(path, test)) {
                continue;
            }
            List<Assignment> processedTest = null;
            for(Integer beta: betaIndexes) {
                // We create a copy of the assignment for which we want to set the startBeta flag because the list may
                // contains multiple references of the same assignments object
                test.set(beta, new Assignment(test.get(beta)));
                test.get(beta).setStartBeta(true);
                processedTest = baProductHandler.process(test);
                test.get(beta).setStartBeta(false);
                if(processedTest != null) {
                    break;
                }
            }

            if(processedTest != null) {
                coverageCriterion.accept(path, processedTest);
                tests.add(new TestSequence(new ArrayList<>(path), processedTest));
                uniqueAssignments.add(processedTest);
                cout.println(path);
                cout.println(processedTest);

                if(coverageCriterion.covered(path)) {
                    break;
                }
            }
        }
        return tests;
    }


    private void printStatistics(BuchiAutomaton ba) {
        cout.println("###############################");
        cout.println("Stats");
        cout.println("# Vertexes:   " + ba.vertexSet().size());
        cout.println("# Acc. States:" + ba.vertexSet().stream().filter(v -> v.isAcceptingState()).count());
        cout.println("# Edges:      " + ba.edgeSet().size());
        cout.println("# Conditions: " + ba.edgeSet().stream().map(e -> e.getAssigments()).mapToInt(Set::size).sum());

        Set<TestSequence> tests = generatedTests.get(ba);
        StateCoverage sc = new StateCoverage(ba);
        AcceptanceStateCoverage asc = new AcceptanceStateCoverage(ba);
        TransitionCoverage tc = new TransitionCoverage(ba);
        ConditionCoverage cc = new ConditionCoverage(ba);
        for(TestSequence t : tests) {
            sc.accept(t.getPath(), t.getAssignmentList());
            asc.accept(t.getPath(), t.getAssignmentList());
            tc.accept(t.getPath(), t.getAssignmentList());
            cc.accept(t.getPath(), t.getAssignmentList());
        }

        cout.println("State Coverage:      " + sc.coverage());
        cout.println("Acc. State Coverage: " + asc.coverage());
        cout.println("Transition Coverage: " + tc.coverage());
        cout.println("Condition Coverage:  " + cc.coverage());
        cout.println("Target Coverage:     " + coverageCriterion.coverage());
        cout.println("###############################");
    }

}
