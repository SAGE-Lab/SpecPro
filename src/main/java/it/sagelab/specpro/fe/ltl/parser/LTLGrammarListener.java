// Generated from LTLGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.fe.ltl.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LTLGrammarParser}.
 */
public interface LTLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LTLGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LTLGrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LTLGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LTLGrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(LTLGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(LTLGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketFormula}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterBracketFormula(LTLGrammarParser.BracketFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketFormula}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitBracketFormula(LTLGrammarParser.BracketFormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LTLGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LTLGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryOp}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(LTLGrammarParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryOp}
	 * labeled alternative in {@link LTLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(LTLGrammarParser.BinaryOpContext ctx);
}