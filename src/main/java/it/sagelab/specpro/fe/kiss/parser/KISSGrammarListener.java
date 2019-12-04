// Generated from KISSGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.fe.kiss.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KISSGrammarParser}.
 */
public interface KISSGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KISSGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(KISSGrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link KISSGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(KISSGrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterInputsDeclaration(KISSGrammarParser.InputsDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitInputsDeclaration(KISSGrammarParser.InputsDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OutputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterOutputsDeclaration(KISSGrammarParser.OutputsDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OutputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitOutputsDeclaration(KISSGrammarParser.OutputsDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NInputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterNInputsDeclaration(KISSGrammarParser.NInputsDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NInputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitNInputsDeclaration(KISSGrammarParser.NInputsDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NOutputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterNOutputsDeclaration(KISSGrammarParser.NOutputsDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NOutputsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitNOutputsDeclaration(KISSGrammarParser.NOutputsDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NTransitionsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterNTransitionsDeclaration(KISSGrammarParser.NTransitionsDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NTransitionsDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitNTransitionsDeclaration(KISSGrammarParser.NTransitionsDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NStatesDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterNStatesDeclaration(KISSGrammarParser.NStatesDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NStatesDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitNStatesDeclaration(KISSGrammarParser.NStatesDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ResetStateDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterResetStateDeclaration(KISSGrammarParser.ResetStateDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ResetStateDeclaration}
	 * labeled alternative in {@link KISSGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitResetStateDeclaration(KISSGrammarParser.ResetStateDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KISSGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(KISSGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link KISSGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(KISSGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link KISSGrammarParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(KISSGrammarParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KISSGrammarParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(KISSGrammarParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code values}
	 * labeled alternative in {@link KISSGrammarParser#declarationdeclarationdeclarationdeclarationdeclarationdeclarationdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterValues(KISSGrammarParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code values}
	 * labeled alternative in {@link KISSGrammarParser#declarationdeclarationdeclarationdeclarationdeclarationdeclarationdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitValues(KISSGrammarParser.ValuesContext ctx);
}