package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.coverage.*;
import it.sagelab.specpro.collections.SequenceBuilder;
import it.sagelab.specpro.collections.Trie;
import it.sagelab.specpro.models.ba.BAExplorer;
import it.sagelab.specpro.models.ba.BuchiAutomaton;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.ac.LassoShapedAcceptanceCondition;
import it.sagelab.specpro.models.ltl.Formula;
import it.sagelab.specpro.models.ltl.LTLSpec;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.reasoners.LTL2BA;
import it.sagelab.specpro.reasoners.translators.SpotTranslator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class AutomaticTestGenerator {

    private static final Logger logger = LogManager.getLogger();

    /** Public Properties **/
    private int minLength, maxLength;
    private BACoverage coverageCriterion;
    private final BAProductHandler baProductHandler;

    /** Internal use only data **/
    private ArrayList<BuchiAutomaton> buchiAutomata = new ArrayList<>();
    private Map<BuchiAutomaton, Set<TestSequence>> generatedTests;

    private final HashSet<List<Assignment>> uniqueAssignments = new HashSet<>();



    public AutomaticTestGenerator() {
        this(2, 10);
    }

    public AutomaticTestGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        coverageCriterion = new StateCoverage();
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

    public void parseRequirements(LTLSpec spec) throws IOException {
        parseRequirements(spec, false);
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

    public Map<BuchiAutomaton, Set<TestSequence>> generate() {
        return generate(System.out);
    }

    public Map<BuchiAutomaton, Set<TestSequence>> generate(PrintStream outStream)  {
        generatedTests = new HashMap<>();

        double totalTime = 0;
        int count = 0;
        for(BuchiAutomaton ba: buchiAutomata) {
            logger.info("Generating paths for BA # " + (++count) + "/" + buchiAutomata.size());
            long startTime = System.nanoTime();
            generate(ba);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            printStatistics(ba);
            double seconds = (double)elapsedTime / 1_000_000_000.0;
            totalTime += seconds;
            logger.info(String.format("Elapsed Time: %.3f s", seconds));
        }

        outStream.println("Different tests generated: " + uniqueAssignments.size());
        uniqueAssignments.forEach(l -> outStream.println(l));

        outStream.println();
        outStream.println(String.format("Total time: %.3f s", totalTime));

        return generatedTests;
    }

    public void computeCrossCoverageWithConjBA(LTLSpec spec) throws IOException {
        ArrayList<BuchiAutomaton> oldBAs = new ArrayList<>(buchiAutomata);
        buchiAutomata.clear();

        parseRequirements(spec, true);

        logger.info("*** Cross Coverage Statistics *** ");
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
                    logger.debug("** WARN: No induced path for test " + test);
                    logger.debug("*****************************************************************************");
                }

                for(List<Edge> path: inducedPaths) {
                    coverageCriterion.accept(path, test);
                    testSet.add(new TestSequence(new ArrayList<>(path), test));
                    logger.debug("** Test already generated **");
                    logger.debug(path);
                    logger.debug(test);
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

            List<Assignment> processedTest = null;
            for(Integer beta: betaIndexes) {

                if(!coverageCriterion.evaluateTest(path, test, beta)) {
                    processedTest = null;
                    continue;
                }

                // We create a copy of the assignment for which we want to set the startBeta flag because the list may
                // contains multiple references of the same assignments object
                test.set(beta, new Assignment(test.get(beta)));
                test.get(beta).setStartBeta(true);
                processedTest = baProductHandler.process(test);
                if(processedTest != null) {
                    break;
                }
                test.get(beta).setStartBeta(false);
            }

            if(processedTest != null) {
                coverageCriterion.accept(path, processedTest);
                tests.add(new TestSequence(new ArrayList<>(path), processedTest));
                uniqueAssignments.add(processedTest);
                //outStream.println(path);
                //outStream.println(processedTest);

                if(coverageCriterion.covered(path)) {
                    break;
                }
            }
        }
        return tests;
    }


    private void printStatistics(BuchiAutomaton ba) {
        logger.info(()-> {
            StringBuilder builder = new StringBuilder();
            builder.append("\n\t###############################\n");
            builder.append("\tStats\n");
            builder.append("\t# Vertexes:   " + ba.vertexSet().size() + "\n");
            builder.append("\t# Acc. States:" + ba.vertexSet().stream().filter(v -> v.isAcceptingState()).count() + "\n");
            builder.append("\t# Edges:      " + ba.edgeSet().size() + "\n");
            builder.append("\t# Conditions: " + ba.edgeSet().stream().map(e -> e.getAssigments()).mapToInt(Set::size).sum() + "\n");

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

            builder.append("\tState Coverage:      " + sc.coverage() + "\n");
            builder.append("\tAcc. State Coverage: " + asc.coverage() + "\n");
            builder.append("\tTransition Coverage: " + tc.coverage() + "\n");
            builder.append("\tCondition Coverage:  " + cc.coverage() + "\n");
            builder.append("\tTarget Coverage:     " + coverageCriterion.coverage() + "\n");
            builder.append("\t###############################");

            return builder.toString();
        });

    }

}
