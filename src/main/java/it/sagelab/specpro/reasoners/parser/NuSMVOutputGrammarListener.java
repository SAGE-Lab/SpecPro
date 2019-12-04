// Generated from NuSMVOutputGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.reasoners.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NuSMVOutputGrammarParser}.
 */
public interface NuSMVOutputGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#output}.
	 * @param ctx the parse tree
	 */
	void enterOutput(NuSMVOutputGrammarParser.OutputContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#output}.
	 * @param ctx the parse tree
	 */
	void exitOutput(NuSMVOutputGrammarParser.OutputContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#spec}.
	 * @param ctx the parse tree
	 */
	void enterSpec(NuSMVOutputGrammarParser.SpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#spec}.
	 * @param ctx the parse tree
	 */
	void exitSpec(NuSMVOutputGrammarParser.SpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#counterexample}.
	 * @param ctx the parse tree
	 */
	void enterCounterexample(NuSMVOutputGrammarParser.CounterexampleContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#counterexample}.
	 * @param ctx the parse tree
	 */
	void exitCounterexample(NuSMVOutputGrammarParser.CounterexampleContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#stateAssignment}.
	 * @param ctx the parse tree
	 */
	void enterStateAssignment(NuSMVOutputGrammarParser.StateAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#stateAssignment}.
	 * @param ctx the parse tree
	 */
	void exitStateAssignment(NuSMVOutputGrammarParser.StateAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#startLoop}.
	 * @param ctx the parse tree
	 */
	void enterStartLoop(NuSMVOutputGrammarParser.StartLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#startLoop}.
	 * @param ctx the parse tree
	 */
	void exitStartLoop(NuSMVOutputGrammarParser.StartLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(NuSMVOutputGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(NuSMVOutputGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#booleanAssignment}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAssignment(NuSMVOutputGrammarParser.BooleanAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#booleanAssignment}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAssignment(NuSMVOutputGrammarParser.BooleanAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#enumAssignment}.
	 * @param ctx the parse tree
	 */
	void enterEnumAssignment(NuSMVOutputGrammarParser.EnumAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#enumAssignment}.
	 * @param ctx the parse tree
	 */
	void exitEnumAssignment(NuSMVOutputGrammarParser.EnumAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NuSMVOutputGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(NuSMVOutputGrammarParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link NuSMVOutputGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(NuSMVOutputGrammarParser.FormulaContext ctx);
}