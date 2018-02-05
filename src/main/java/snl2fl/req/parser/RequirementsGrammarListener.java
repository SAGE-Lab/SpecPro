// Generated from RequirementsGrammar.g4 by ANTLR 4.5.3
package snl2fl.req.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequirementsGrammarParser}.
 */
public interface RequirementsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(RequirementsGrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(RequirementsGrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void enterRequirement(RequirementsGrammarParser.RequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void exitRequirement(RequirementsGrammarParser.RequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(RequirementsGrammarParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(RequirementsGrammarParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#specification}.
	 * @param ctx the parse tree
	 */
	void enterSpecification(RequirementsGrammarParser.SpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#specification}.
	 * @param ctx the parse tree
	 */
	void exitSpecification(RequirementsGrammarParser.SpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#qualitative}.
	 * @param ctx the parse tree
	 */
	void enterQualitative(RequirementsGrammarParser.QualitativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#qualitative}.
	 * @param ctx the parse tree
	 */
	void exitQualitative(RequirementsGrammarParser.QualitativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#absence}.
	 * @param ctx the parse tree
	 */
	void enterAbsence(RequirementsGrammarParser.AbsenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#absence}.
	 * @param ctx the parse tree
	 */
	void exitAbsence(RequirementsGrammarParser.AbsenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#universality}.
	 * @param ctx the parse tree
	 */
	void enterUniversality(RequirementsGrammarParser.UniversalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#universality}.
	 * @param ctx the parse tree
	 */
	void exitUniversality(RequirementsGrammarParser.UniversalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#existence}.
	 * @param ctx the parse tree
	 */
	void enterExistence(RequirementsGrammarParser.ExistenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#existence}.
	 * @param ctx the parse tree
	 */
	void exitExistence(RequirementsGrammarParser.ExistenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#boundedExistence}.
	 * @param ctx the parse tree
	 */
	void enterBoundedExistence(RequirementsGrammarParser.BoundedExistenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#boundedExistence}.
	 * @param ctx the parse tree
	 */
	void exitBoundedExistence(RequirementsGrammarParser.BoundedExistenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#precedence}.
	 * @param ctx the parse tree
	 */
	void enterPrecedence(RequirementsGrammarParser.PrecedenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#precedence}.
	 * @param ctx the parse tree
	 */
	void exitPrecedence(RequirementsGrammarParser.PrecedenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#precedenceChain12}.
	 * @param ctx the parse tree
	 */
	void enterPrecedenceChain12(RequirementsGrammarParser.PrecedenceChain12Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#precedenceChain12}.
	 * @param ctx the parse tree
	 */
	void exitPrecedenceChain12(RequirementsGrammarParser.PrecedenceChain12Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#precedenceChain21}.
	 * @param ctx the parse tree
	 */
	void enterPrecedenceChain21(RequirementsGrammarParser.PrecedenceChain21Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#precedenceChain21}.
	 * @param ctx the parse tree
	 */
	void exitPrecedenceChain21(RequirementsGrammarParser.PrecedenceChain21Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#response}.
	 * @param ctx the parse tree
	 */
	void enterResponse(RequirementsGrammarParser.ResponseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#response}.
	 * @param ctx the parse tree
	 */
	void exitResponse(RequirementsGrammarParser.ResponseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#constrainedChain12}.
	 * @param ctx the parse tree
	 */
	void enterConstrainedChain12(RequirementsGrammarParser.ConstrainedChain12Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#constrainedChain12}.
	 * @param ctx the parse tree
	 */
	void exitConstrainedChain12(RequirementsGrammarParser.ConstrainedChain12Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#responseChain21}.
	 * @param ctx the parse tree
	 */
	void enterResponseChain21(RequirementsGrammarParser.ResponseChain21Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#responseChain21}.
	 * @param ctx the parse tree
	 */
	void exitResponseChain21(RequirementsGrammarParser.ResponseChain21Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#responseChain12}.
	 * @param ctx the parse tree
	 */
	void enterResponseChain12(RequirementsGrammarParser.ResponseChain12Context ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#responseChain12}.
	 * @param ctx the parse tree
	 */
	void exitResponseChain12(RequirementsGrammarParser.ResponseChain12Context ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#realtime}.
	 * @param ctx the parse tree
	 */
	void enterRealtime(RequirementsGrammarParser.RealtimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#realtime}.
	 * @param ctx the parse tree
	 */
	void exitRealtime(RequirementsGrammarParser.RealtimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#minDuration}.
	 * @param ctx the parse tree
	 */
	void enterMinDuration(RequirementsGrammarParser.MinDurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#minDuration}.
	 * @param ctx the parse tree
	 */
	void exitMinDuration(RequirementsGrammarParser.MinDurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#maxDuration}.
	 * @param ctx the parse tree
	 */
	void enterMaxDuration(RequirementsGrammarParser.MaxDurationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#maxDuration}.
	 * @param ctx the parse tree
	 */
	void exitMaxDuration(RequirementsGrammarParser.MaxDurationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#boundedRecurrence}.
	 * @param ctx the parse tree
	 */
	void enterBoundedRecurrence(RequirementsGrammarParser.BoundedRecurrenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#boundedRecurrence}.
	 * @param ctx the parse tree
	 */
	void exitBoundedRecurrence(RequirementsGrammarParser.BoundedRecurrenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#boundedResponse}.
	 * @param ctx the parse tree
	 */
	void enterBoundedResponse(RequirementsGrammarParser.BoundedResponseContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#boundedResponse}.
	 * @param ctx the parse tree
	 */
	void exitBoundedResponse(RequirementsGrammarParser.BoundedResponseContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#boundedInvariance}.
	 * @param ctx the parse tree
	 */
	void enterBoundedInvariance(RequirementsGrammarParser.BoundedInvarianceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#boundedInvariance}.
	 * @param ctx the parse tree
	 */
	void exitBoundedInvariance(RequirementsGrammarParser.BoundedInvarianceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#invariant}.
	 * @param ctx the parse tree
	 */
	void enterInvariant(RequirementsGrammarParser.InvariantContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#invariant}.
	 * @param ctx the parse tree
	 */
	void exitInvariant(RequirementsGrammarParser.InvariantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BracketExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBracketExpression(RequirementsGrammarParser.BracketExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BracketExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBracketExpression(RequirementsGrammarParser.BracketExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(RequirementsGrammarParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(RequirementsGrammarParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIDExpression(RequirementsGrammarParser.IDExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIDExpression(RequirementsGrammarParser.IDExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(RequirementsGrammarParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(RequirementsGrammarParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpression(RequirementsGrammarParser.CompareExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareExpression}
	 * labeled alternative in {@link RequirementsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpression(RequirementsGrammarParser.CompareExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(RequirementsGrammarParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(RequirementsGrammarParser.TimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#always}.
	 * @param ctx the parse tree
	 */
	void enterAlways(RequirementsGrammarParser.AlwaysContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#always}.
	 * @param ctx the parse tree
	 */
	void exitAlways(RequirementsGrammarParser.AlwaysContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#never}.
	 * @param ctx the parse tree
	 */
	void enterNever(RequirementsGrammarParser.NeverContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#never}.
	 * @param ctx the parse tree
	 */
	void exitNever(RequirementsGrammarParser.NeverContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RequirementsGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RequirementsGrammarParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#positiveInt}.
	 * @param ctx the parse tree
	 */
	void enterPositiveInt(RequirementsGrammarParser.PositiveIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#positiveInt}.
	 * @param ctx the parse tree
	 */
	void exitPositiveInt(RequirementsGrammarParser.PositiveIntContext ctx);
}