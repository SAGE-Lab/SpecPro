// Generated from FLGrammar.g4 by ANTLR 4.5.3
package snl2fl.fl.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FLGrammarParser}.
 */
public interface FLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FLGrammarParser#fl}.
	 * @param ctx the parse tree
	 */
	void enterFl(FLGrammarParser.FlContext ctx);
	/**
	 * Exit a parse tree produced by {@link FLGrammarParser#fl}.
	 * @param ctx the parse tree
	 */
	void exitFl(FLGrammarParser.FlContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(FLGrammarParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(FLGrammarParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketFormula}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterBracketFormula(FLGrammarParser.BracketFormulaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketFormula}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitBracketFormula(FLGrammarParser.BracketFormulaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryLogicOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryLogicOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLogicOp(FLGrammarParser.BinaryLogicOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterAtom(FLGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atom}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitAtom(FLGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(FLGrammarParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryOp}
	 * labeled alternative in {@link FLGrammarParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(FLGrammarParser.BinaryOpContext ctx);
}