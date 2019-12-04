package it.sagelab.specpro.reasoners.parser;

import it.sagelab.specpro.fe.ParseException;
import it.sagelab.specpro.fe.psp.parser.ThrowingErrorListener;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import it.sagelab.specpro.models.ltl.assign.Trace;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class NuSMVOutputParser extends NuSMVOutputGrammarBaseListener {

    private ArrayList<Trace> counterexampleList;
    private Trace counterexample;
    private Assignment currentAssignment;

    public ArrayList<Trace> getCounterexampleList() {
        return counterexampleList;
    }

    public static ArrayList<Trace> parseFile(String fileName) throws IOException {
        return parse(CharStreams.fromFileName(fileName));
    }

    public static ArrayList<Trace> parseString(String str) throws IOException {
        return parse(CharStreams.fromString(str));
    }

    public static ArrayList<Trace> parse(CharStream stream) throws IOException {
        try {
            NuSMVOutputGrammarLexer lexer = new NuSMVOutputGrammarLexer(stream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            NuSMVOutputGrammarParser parser = new NuSMVOutputGrammarParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);

            NuSMVOutputParser nuSMVOutputParser = new NuSMVOutputParser();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(nuSMVOutputParser, parser.output());

            return nuSMVOutputParser.getCounterexampleList();
        } catch (ParseCancellationException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public void enterOutput(NuSMVOutputGrammarParser.OutputContext ctx) {
        counterexampleList = new ArrayList<>();
    }

    @Override
    public void exitOutput(NuSMVOutputGrammarParser.OutputContext ctx) {
        for(Trace trace: counterexampleList) {
            for(int i = 1; i < trace.size(); ++i) {
                Assignment previous = trace.get(i - 1);
                Assignment current = trace.get(i);

                for(Map.Entry<Atom, Boolean> entry: previous.getAssignments().entrySet()) {
                    if(!current.contains(entry.getKey())) {
                        current.add(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    }

    @Override
    public void enterSpec(NuSMVOutputGrammarParser.SpecContext ctx) {
        counterexample = new Trace();
    }

    @Override
    public void exitSpec(NuSMVOutputGrammarParser.SpecContext ctx) {
        counterexampleList.add(counterexample);
    }

    @Override
    public void enterStateAssignment(NuSMVOutputGrammarParser.StateAssignmentContext ctx) {
        currentAssignment = new Assignment();
    }

    @Override
    public void exitStateAssignment(NuSMVOutputGrammarParser.StateAssignmentContext ctx) {
        counterexample.add(currentAssignment);
        if(ctx.startLoop() != null) {
            counterexample.setStartCycle(counterexample.size() - 1);
        }
    }

    @Override
    public void exitBooleanAssignment(NuSMVOutputGrammarParser.BooleanAssignmentContext ctx) {
        currentAssignment.add(new Atom(ctx.ID().getText()), ctx.TRUE() != null);
    }
}
