package it.sagelab.specpro.testing;

import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.testing.generators.TestGenerator;
import it.sagelab.specpro.testing.oracles.TestOracle;
import it.sagelab.specpro.models.ltl.assign.Trace;

import java.util.HashMap;
import java.util.function.Consumer;

public class TestingEnvironment {

    private final TestGenerator testGenerator;
    private final TestOracle oracle;
    private final SUT sut;
    private int maxTraceLength;
    private boolean stopIfError;

    public TestingEnvironment(TestGenerator testGenerator, TestOracle oracle, SUT sut) {
        this.testGenerator = testGenerator;
        this.oracle = oracle;
        this.sut = sut;
        this.maxTraceLength = 100;
        this.stopIfError = false;
    }

    public TestingEnvironment(TestGenerator testGenerator, SUT sut) {
        this.testGenerator = testGenerator;
        this.oracle = testGenerator.getOracle();
        this.sut = sut;
        this.maxTraceLength = 100;
        this.stopIfError = false;
    }

    public boolean isStopIfError() {
        return stopIfError;
    }

    public void setStopIfError(boolean stopIfError) {
        this.stopIfError = stopIfError;
    }

    public int getMaxTraceLength() {
        return maxTraceLength;
    }

    public void setMaxTraceLength(int maxTraceLength) {
        this.maxTraceLength = maxTraceLength;
    }

    public HashMap<Trace, TestOracle.Value> runTests() {
        return runTests(null);
    }

    public HashMap<Trace, TestOracle.Value> runTests(Consumer<HashMap<Trace, TestOracle.Value>> consumer) {
        HashMap<Trace, TestOracle.Value> results = new HashMap<>();
        Trace trace = new Trace();
        TestOracle.Value value = null;
        testGenerator.reset();
        sut.reset();

        if(consumer != null) {
            consumer.accept(results);
        }

        while(testGenerator.hasNext()) {

            Trace input = testGenerator.next();
            Assignment currentInput = null;
            try {
                for (Assignment i : input) {
                    currentInput = i;
                    Assignment o = sut.exec(i);
                    trace.add(i.combine(o));

                    if(trace.last() == null) {
                        throw new RuntimeException(trace.toString());
                    }

                    if (trace.size() >= maxTraceLength) {
                        break;
                    }
                }

                value = oracle.evaluatePartial(trace);

            } catch (InvalidInputException e) {
                trace.add(currentInput);
                value = TestOracle.Value.FALSE;
            }

            if(value == TestOracle.Value.FALSE
                    || trace.size() >= maxTraceLength
                    || testGenerator.isCurrentTraceComplete(trace, value)) {
                testGenerator.traceCompleted(trace);
                value = oracle.evaluateComplete(trace);
                results.put(trace, value);
                trace = new Trace();
                sut.reset();
                oracle.reset();

                if(consumer != null) {
                    consumer.accept(results);
                }
            }

            if(stopIfError && value == TestOracle.Value.FALSE) {
                break;
            }

        }

        return results;
    }


}
