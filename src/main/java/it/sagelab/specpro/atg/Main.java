package it.sagelab.specpro.atg;

import it.sagelab.specpro.atg.alg.*;
import it.sagelab.specpro.collections.Trie;

import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import sun.misc.IOUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Main {

    public static void printPathAsFormula(List<Assignment> path, PrintWriter pw) {

        for(int i = 0; i < path.size(); ++i) {
            for(int j = 0; j < i; ++j)
                pw.print("EX ");
            pw.print("(");
            Assignment a = path.get(i);
            int count = 0;
            for(Map.Entry<Atom, Boolean> entry: a.getAssignments().entrySet()) {
                if(entry.getValue().equals(Boolean.FALSE))
                    pw.print("!");
                pw.print(entry.getKey().getName());
                if(count < a.getAssignments().size() - 1)
                    pw.print(" & ");
                ++count;
            }
            pw.print(")");

            if(i < path.size() - 1)
                pw.print(" & ");
            else
                pw.println();
        }
    }


    public static void main(String[] args) throws IOException {

        String filePath = "robot.req";

        RequirementsTestGenerator rtg = new BARunnerTestGenerator(filePath);
        //RequirementsTestGenerator rtg = new RequirementsTestGenerator(filePath);
        //RequirementsTestGenerator rtg = new SplitReqTestGenerator(filePath, 10);

        for(int pathLenght = 3; pathLenght < 6; ++pathLenght) {
            Trie<Assignment> result = rtg.generate(pathLenght, true);

            int count = 0;
            int maxLength = 0;


            FileWriter fw = new FileWriter("tests_" + pathLenght + ".txt");
            PrintWriter printWriter = new PrintWriter(fw);

            for(List<Assignment> p : result) {
                maxLength = p.size() > maxLength ? p.size() : maxLength;
                printWriter.println(p);
                ++count;
            }

            printWriter.println("****************************************************************");

            for(List<Assignment> p: result) {
                printPathAsFormula(p, printWriter);
            }

            printWriter.close();

            System.out.println("Number of Sequences: " + count);
            System.out.println("Max length: " + maxLength);
            System.out.println("****************************************************************");
            System.out.println("\n\n\n");
        }

    }

}
