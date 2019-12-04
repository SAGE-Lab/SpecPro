package it.sagelab.specpro.fe.kiss;

import it.sagelab.specpro.fe.ParseException;
import it.sagelab.specpro.fe.kiss.parser.KISSGrammarBaseListener;
import it.sagelab.specpro.fe.kiss.parser.KISSGrammarLexer;
import it.sagelab.specpro.fe.kiss.parser.KISSGrammarParser;
import it.sagelab.specpro.models.fsm.Edge;
import it.sagelab.specpro.models.fsm.MealyMachine;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MealyMachineBuilder extends KISSGrammarBaseListener {

    private MealyMachine mealyMachine;
    private ArrayList<Atom> inputVariables, outputVariables;

    public static MealyMachine parseKISSFile(String fileName) throws IOException {
        KISSGrammarLexer lexer = new KISSGrammarLexer(CharStreams.fromFileName(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KISSGrammarParser parser = new KISSGrammarParser(tokens);

        ParseTreeWalker walker = new ParseTreeWalker();
        MealyMachineBuilder builder = new MealyMachineBuilder();

        walker.walk(builder, parser.file());

        return builder.mealyMachine;
    }

    public MealyMachine getMealyMachine() {
        return mealyMachine;
    }

    @Override
    public void enterFile(KISSGrammarParser.FileContext ctx) {
        super.enterFile(ctx);
        mealyMachine = new MealyMachine();
    }

    @Override
    public void enterInputsDeclaration(KISSGrammarParser.InputsDeclarationContext ctx) {
        super.enterInputsDeclaration(ctx);
        inputVariables = new ArrayList<>();
        for(TerminalNode id: ctx.ID()) {
            inputVariables.add(new Atom(id.toString()));
        }
        mealyMachine.setInputs(inputVariables);
    }

    @Override
    public void enterOutputsDeclaration(KISSGrammarParser.OutputsDeclarationContext ctx) {
        super.enterOutputsDeclaration(ctx);
        outputVariables = new ArrayList<>();
        for(TerminalNode id: ctx.ID()) {
            outputVariables.add(new Atom(id.toString()));
        }

        mealyMachine.setOutputs(outputVariables);
    }

    @Override
    public void enterResetStateDeclaration(KISSGrammarParser.ResetStateDeclarationContext ctx) {
        super.enterResetStateDeclaration(ctx);
        String resetState = ctx.ID().getText();
        mealyMachine.setResetState(resetState);
    }

    @Override
    public void enterTransition(KISSGrammarParser.TransitionContext ctx) {
        super.enterTransition(ctx);
        String s1 = ctx.ID(0).getText();
        String s2 = ctx.ID(1).getText();

        Assignment inputs = buildAssignment(ctx.values(0).getText(), inputVariables);
        Assignment outputs = buildAssignment(ctx.values(1).getText(), outputVariables);

        for(Atom o: outputVariables) {
            outputs.getAssignments().putIfAbsent(o, false);
        }

        Edge e = new Edge(s1, s2, inputs, outputs);
        mealyMachine.addVertex(s1);
        mealyMachine.addVertex(s2);
        mealyMachine.addEdge(s1, s2, e);
    }

    private Assignment buildAssignment(String values, ArrayList<Atom> vars) {
        Assignment a = new Assignment();

        if(values.length() != vars.size()) {
            throw new ParseException("Wrong number of values in KISS file");
        }

        for(int i = 0; i < values.length(); ++i) {
            switch (values.charAt(i)) {
                case '1':
                    a.add(vars.get(i), true);
                    break;
                case '0':
                    a.add(vars.get(i), false);
                    break;
                default:
                    break;
            }
        }

        return a;
    }

}
