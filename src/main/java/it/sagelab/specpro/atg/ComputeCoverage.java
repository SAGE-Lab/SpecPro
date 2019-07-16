package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ComputeCoverage {

    private static final String INPUT_START = "-input: ";

    public static Assignment parseAssignment(String str) {
        Assignment assignment = new Assignment();
        String[] ass = str.split(", ");
        for(String a: ass) {
            boolean negated = a.startsWith("!");
            if(negated)
                a = a.substring(1);
            assignment.add(new Atom(a), !negated);
        }
        return assignment;
    }

    public static List<Assignment> parseSequence(String str) {
        ArrayList<Assignment> sequence = new ArrayList<>();

        if(!str.startsWith("[") || !str.endsWith("]")) {
            return null;
        }
        String delimiter = "\\}, \\{";
        String[] assignments = str.substring(2, str.length() - 2).split(delimiter);
        for(String a: assignments) {
            sequence.add(parseAssignment(a));
        }
        return sequence;
    }

    public static void main(String[] args) throws IOException {

        if(args.length < 2) {
            System.out.println("Usage: ComputeCoverage <kissFile> <outputFile>");
            System.exit(1);
        }

        MealyCoverage mealyCoverage = new MealyCoverage(args[0]);

        Scanner scanner = new Scanner(new File(args[1]));

        HashSet<TestCase> testCases = new HashSet<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith(INPUT_START)) {
                String inputStr = line.substring(INPUT_START.length());
                List<Assignment> input = parseSequence(inputStr);
                if(input != null) {
                    TestCase testCase = new TestCase(input);
                    int outputs = 0;
                    if (scanner.hasNextLine()) {
                        outputs = Integer.parseInt(scanner.nextLine());
                    }
                    while (scanner.hasNextLine() && outputs > 0) {
                        List<Assignment> o = parseSequence(scanner.nextLine());
                        if (o != null) {
                            testCase.addOutput(o);
                        }
                        --outputs;
                    }
                    testCases.add(testCase);
                }
            }

        }

        int totOutputs = testCases.stream().mapToInt(t -> t.getOutputs().size()).sum();
        System.out.print(testCases.size() + ", " + totOutputs + ", ");

        for(TestCase t: testCases) {
            mealyCoverage.evaluateTestCase(t);
        }
        mealyCoverage.printMeasures(System.out);
        System.out.println();

    }

}
