// Generated from RequirementsGrammar.g4 by ANTLR 4.7.1
package snl2fl.req.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RequirementsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, POSITIVE_INT=59, 
		FLOAT=60, TIME_UNIT=61, ID=62, WS=63, LINE_COMMENT=64;
	public static final int
		RULE_file = 0, RULE_requirement = 1, RULE_scope = 2, RULE_specification = 3, 
		RULE_qualitative = 4, RULE_absence = 5, RULE_universality = 6, RULE_existence = 7, 
		RULE_boundedExistence = 8, RULE_precedence = 9, RULE_precedenceChain12 = 10, 
		RULE_precedenceChain21 = 11, RULE_response = 12, RULE_constrainedChain12 = 13, 
		RULE_responseChain21 = 14, RULE_responseChain12 = 15, RULE_realtime = 16, 
		RULE_minDuration = 17, RULE_maxDuration = 18, RULE_boundedRecurrence = 19, 
		RULE_boundedResponse = 20, RULE_boundedInvariance = 21, RULE_invariant = 22, 
		RULE_expr = 23, RULE_time = 24, RULE_always = 25, RULE_never = 26, RULE_number = 27, 
		RULE_positiveInt = 28;
	public static final String[] ruleNames = {
		"file", "requirement", "scope", "specification", "qualitative", "absence", 
		"universality", "existence", "boundedExistence", "precedence", "precedenceChain12", 
		"precedenceChain21", "response", "constrainedChain12", "responseChain21", 
		"responseChain12", "realtime", "minDuration", "maxDuration", "boundedRecurrence", 
		"boundedResponse", "boundedInvariance", "invariant", "expr", "time", "always", 
		"never", "number", "positiveInt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'.'", "'Globally'", "'Before'", "'After'", "'Between'", 
		"'and'", "'until'", "'holds'", "'eventually'", "'transitions'", "'to'", 
		"'states'", "'in'", "'which'", "'occur'", "'at'", "'most'", "'times'", 
		"'if'", "'then'", "'previously'", "'held'", "'is'", "'succeeded'", "'by'", 
		"'was'", "'preceded'", "'where'", "'does'", "'not'", "'hold'", "'between'", 
		"'after'", "'once'", "'becomes'", "'satisfied'", "'it'", "'for'", "'least'", 
		"'every'", "'as'", "'well'", "'('", "')'", "'='", "'>'", "'>='", "'<'", 
		"'<='", "'!='", "'or'", "'xor'", "'always'", "'the'", "'case'", "'that'", 
		"'never'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "POSITIVE_INT", 
		"FLOAT", "TIME_UNIT", "ID", "WS", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RequirementsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RequirementsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public List<RequirementContext> requirement() {
			return getRuleContexts(RequirementContext.class);
		}
		public RequirementContext requirement(int i) {
			return getRuleContext(RequirementContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				requirement();
				}
				}
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RequirementContext extends ParserRuleContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public SpecificationContext specification() {
			return getRuleContext(SpecificationContext.class,0);
		}
		public RequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRequirement(this);
		}
	}

	public final RequirementContext requirement() throws RecognitionException {
		RequirementContext _localctx = new RequirementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_requirement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			scope();
			setState(64);
			match(T__0);
			setState(65);
			specification();
			setState(66);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitScope(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_scope);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__3);
				setState(70);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				match(T__4);
				setState(72);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				match(T__5);
				setState(74);
				expr(0);
				setState(75);
				match(T__6);
				setState(76);
				expr(0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				match(T__4);
				setState(79);
				expr(0);
				setState(80);
				match(T__7);
				setState(81);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpecificationContext extends ParserRuleContext {
		public QualitativeContext qualitative() {
			return getRuleContext(QualitativeContext.class,0);
		}
		public RealtimeContext realtime() {
			return getRuleContext(RealtimeContext.class,0);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitSpecification(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_specification);
		try {
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				qualitative();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				realtime();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualitativeContext extends ParserRuleContext {
		public AbsenceContext absence() {
			return getRuleContext(AbsenceContext.class,0);
		}
		public UniversalityContext universality() {
			return getRuleContext(UniversalityContext.class,0);
		}
		public ExistenceContext existence() {
			return getRuleContext(ExistenceContext.class,0);
		}
		public BoundedExistenceContext boundedExistence() {
			return getRuleContext(BoundedExistenceContext.class,0);
		}
		public PrecedenceContext precedence() {
			return getRuleContext(PrecedenceContext.class,0);
		}
		public PrecedenceChain12Context precedenceChain12() {
			return getRuleContext(PrecedenceChain12Context.class,0);
		}
		public PrecedenceChain21Context precedenceChain21() {
			return getRuleContext(PrecedenceChain21Context.class,0);
		}
		public ResponseContext response() {
			return getRuleContext(ResponseContext.class,0);
		}
		public ResponseChain12Context responseChain12() {
			return getRuleContext(ResponseChain12Context.class,0);
		}
		public ResponseChain21Context responseChain21() {
			return getRuleContext(ResponseChain21Context.class,0);
		}
		public ConstrainedChain12Context constrainedChain12() {
			return getRuleContext(ConstrainedChain12Context.class,0);
		}
		public InvariantContext invariant() {
			return getRuleContext(InvariantContext.class,0);
		}
		public QualitativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualitative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterQualitative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitQualitative(this);
		}
	}

	public final QualitativeContext qualitative() throws RecognitionException {
		QualitativeContext _localctx = new QualitativeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_qualitative);
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				absence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				universality();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				existence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(92);
				boundedExistence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(93);
				precedence();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(94);
				precedenceChain12();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(95);
				precedenceChain21();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(96);
				response();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(97);
				responseChain12();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(98);
				responseChain21();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(99);
				constrainedChain12();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(100);
				invariant();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsenceContext extends ParserRuleContext {
		public NeverContext never() {
			return getRuleContext(NeverContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AbsenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterAbsence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitAbsence(this);
		}
	}

	public final AbsenceContext absence() throws RecognitionException {
		AbsenceContext _localctx = new AbsenceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_absence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			never();
			setState(104);
			expr(0);
			setState(105);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniversalityContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UniversalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_universality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterUniversality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitUniversality(this);
		}
	}

	public final UniversalityContext universality() throws RecognitionException {
		UniversalityContext _localctx = new UniversalityContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_universality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			always();
			setState(108);
			expr(0);
			setState(109);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistenceContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExistenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterExistence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitExistence(this);
		}
	}

	public final ExistenceContext existence() throws RecognitionException {
		ExistenceContext _localctx = new ExistenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_existence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			expr(0);
			setState(112);
			match(T__9);
			setState(113);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundedExistenceContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PositiveIntContext positiveInt() {
			return getRuleContext(PositiveIntContext.class,0);
		}
		public BoundedExistenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boundedExistence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBoundedExistence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBoundedExistence(this);
		}
	}

	public final BoundedExistenceContext boundedExistence() throws RecognitionException {
		BoundedExistenceContext _localctx = new BoundedExistenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_boundedExistence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__10);
			setState(116);
			match(T__11);
			setState(117);
			match(T__12);
			setState(118);
			match(T__13);
			setState(119);
			match(T__14);
			setState(120);
			expr(0);
			setState(121);
			match(T__8);
			setState(122);
			match(T__15);
			setState(123);
			match(T__16);
			setState(124);
			match(T__17);
			setState(125);
			positiveInt();
			setState(126);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrecedenceContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitPrecedence(this);
		}
	}

	public final PrecedenceContext precedence() throws RecognitionException {
		PrecedenceContext _localctx = new PrecedenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			always();
			setState(129);
			match(T__19);
			setState(130);
			expr(0);
			setState(131);
			match(T__8);
			setState(132);
			match(T__0);
			setState(133);
			match(T__20);
			setState(134);
			expr(0);
			setState(135);
			match(T__21);
			setState(136);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrecedenceChain12Context extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PrecedenceChain12Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedenceChain12; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterPrecedenceChain12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitPrecedenceChain12(this);
		}
	}

	public final PrecedenceChain12Context precedenceChain12() throws RecognitionException {
		PrecedenceChain12Context _localctx = new PrecedenceChain12Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_precedenceChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			always();
			setState(139);
			match(T__19);
			setState(140);
			expr(0);
			setState(141);
			match(T__8);
			setState(142);
			match(T__6);
			setState(143);
			match(T__23);
			setState(144);
			match(T__24);
			setState(145);
			match(T__25);
			setState(146);
			expr(0);
			setState(147);
			match(T__0);
			setState(148);
			match(T__20);
			setState(149);
			expr(0);
			setState(150);
			match(T__21);
			setState(151);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrecedenceChain21Context extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PrecedenceChain21Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedenceChain21; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterPrecedenceChain21(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitPrecedenceChain21(this);
		}
	}

	public final PrecedenceChain21Context precedenceChain21() throws RecognitionException {
		PrecedenceChain21Context _localctx = new PrecedenceChain21Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_precedenceChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			always();
			setState(154);
			match(T__19);
			setState(155);
			expr(0);
			setState(156);
			match(T__8);
			setState(157);
			match(T__0);
			setState(158);
			match(T__20);
			setState(159);
			expr(0);
			setState(160);
			match(T__21);
			setState(161);
			match(T__22);
			setState(162);
			match(T__6);
			setState(163);
			match(T__26);
			setState(164);
			match(T__27);
			setState(165);
			match(T__25);
			setState(166);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResponseContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ResponseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterResponse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitResponse(this);
		}
	}

	public final ResponseContext response() throws RecognitionException {
		ResponseContext _localctx = new ResponseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			always();
			setState(169);
			match(T__19);
			setState(170);
			expr(0);
			setState(171);
			match(T__8);
			setState(172);
			match(T__0);
			setState(173);
			match(T__20);
			setState(174);
			expr(0);
			setState(175);
			match(T__9);
			setState(176);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstrainedChain12Context extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ConstrainedChain12Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constrainedChain12; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterConstrainedChain12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitConstrainedChain12(this);
		}
	}

	public final ConstrainedChain12Context constrainedChain12() throws RecognitionException {
		ConstrainedChain12Context _localctx = new ConstrainedChain12Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_constrainedChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			always();
			setState(179);
			match(T__19);
			setState(180);
			expr(0);
			setState(181);
			match(T__8);
			setState(182);
			match(T__0);
			setState(183);
			match(T__20);
			setState(184);
			expr(0);
			setState(185);
			match(T__9);
			setState(186);
			match(T__8);
			setState(187);
			match(T__6);
			setState(188);
			match(T__23);
			setState(189);
			match(T__24);
			setState(190);
			match(T__25);
			setState(191);
			expr(0);
			setState(192);
			match(T__0);
			setState(193);
			match(T__28);
			setState(194);
			expr(0);
			setState(195);
			match(T__29);
			setState(196);
			match(T__30);
			setState(197);
			match(T__31);
			setState(198);
			match(T__32);
			setState(199);
			expr(0);
			setState(200);
			match(T__6);
			setState(201);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResponseChain21Context extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ResponseChain21Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_responseChain21; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterResponseChain21(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitResponseChain21(this);
		}
	}

	public final ResponseChain21Context responseChain21() throws RecognitionException {
		ResponseChain21Context _localctx = new ResponseChain21Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_responseChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			always();
			setState(204);
			match(T__19);
			setState(205);
			expr(0);
			setState(206);
			match(T__8);
			setState(207);
			match(T__6);
			setState(208);
			match(T__23);
			setState(209);
			match(T__24);
			setState(210);
			match(T__25);
			setState(211);
			expr(0);
			setState(212);
			match(T__0);
			setState(213);
			match(T__20);
			setState(214);
			expr(0);
			setState(215);
			match(T__9);
			setState(216);
			match(T__8);
			setState(217);
			match(T__33);
			setState(218);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResponseChain12Context extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ResponseChain12Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_responseChain12; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterResponseChain12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitResponseChain12(this);
		}
	}

	public final ResponseChain12Context responseChain12() throws RecognitionException {
		ResponseChain12Context _localctx = new ResponseChain12Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_responseChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			always();
			setState(221);
			match(T__19);
			setState(222);
			expr(0);
			setState(223);
			match(T__8);
			setState(224);
			match(T__0);
			setState(225);
			match(T__20);
			setState(226);
			expr(0);
			setState(227);
			match(T__9);
			setState(228);
			match(T__8);
			setState(229);
			match(T__6);
			setState(230);
			match(T__23);
			setState(231);
			match(T__24);
			setState(232);
			match(T__25);
			setState(233);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealtimeContext extends ParserRuleContext {
		public MinDurationContext minDuration() {
			return getRuleContext(MinDurationContext.class,0);
		}
		public MaxDurationContext maxDuration() {
			return getRuleContext(MaxDurationContext.class,0);
		}
		public BoundedRecurrenceContext boundedRecurrence() {
			return getRuleContext(BoundedRecurrenceContext.class,0);
		}
		public BoundedResponseContext boundedResponse() {
			return getRuleContext(BoundedResponseContext.class,0);
		}
		public BoundedInvarianceContext boundedInvariance() {
			return getRuleContext(BoundedInvarianceContext.class,0);
		}
		public RealtimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realtime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRealtime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRealtime(this);
		}
	}

	public final RealtimeContext realtime() throws RecognitionException {
		RealtimeContext _localctx = new RealtimeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_realtime);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				minDuration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				maxDuration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				boundedRecurrence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				boundedResponse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(239);
				boundedInvariance();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MinDurationContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public MinDurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minDuration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterMinDuration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitMinDuration(this);
		}
	}

	public final MinDurationContext minDuration() throws RecognitionException {
		MinDurationContext _localctx = new MinDurationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_minDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			always();
			setState(243);
			match(T__34);
			setState(244);
			expr(0);
			setState(245);
			match(T__35);
			setState(246);
			match(T__36);
			setState(247);
			match(T__0);
			setState(248);
			match(T__37);
			setState(249);
			match(T__8);
			setState(250);
			match(T__38);
			setState(251);
			match(T__16);
			setState(252);
			match(T__39);
			setState(253);
			time();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MaxDurationContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public MaxDurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxDuration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterMaxDuration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitMaxDuration(this);
		}
	}

	public final MaxDurationContext maxDuration() throws RecognitionException {
		MaxDurationContext _localctx = new MaxDurationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_maxDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			always();
			setState(256);
			match(T__34);
			setState(257);
			expr(0);
			setState(258);
			match(T__35);
			setState(259);
			match(T__36);
			setState(260);
			match(T__0);
			setState(261);
			match(T__37);
			setState(262);
			match(T__8);
			setState(263);
			match(T__38);
			setState(264);
			match(T__16);
			setState(265);
			match(T__17);
			setState(266);
			time();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundedRecurrenceContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public BoundedRecurrenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boundedRecurrence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBoundedRecurrence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBoundedRecurrence(this);
		}
	}

	public final BoundedRecurrenceContext boundedRecurrence() throws RecognitionException {
		BoundedRecurrenceContext _localctx = new BoundedRecurrenceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_boundedRecurrence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			always();
			setState(269);
			expr(0);
			setState(270);
			match(T__8);
			setState(271);
			match(T__16);
			setState(272);
			match(T__39);
			setState(273);
			match(T__40);
			setState(274);
			time();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundedResponseContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public BoundedResponseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boundedResponse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBoundedResponse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBoundedResponse(this);
		}
	}

	public final BoundedResponseContext boundedResponse() throws RecognitionException {
		BoundedResponseContext _localctx = new BoundedResponseContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_boundedResponse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			always();
			setState(277);
			match(T__19);
			setState(278);
			expr(0);
			setState(279);
			match(T__8);
			setState(280);
			match(T__0);
			setState(281);
			match(T__20);
			setState(282);
			expr(0);
			setState(283);
			match(T__8);
			setState(284);
			match(T__33);
			setState(285);
			match(T__16);
			setState(286);
			match(T__17);
			setState(287);
			time();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundedInvarianceContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public BoundedInvarianceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boundedInvariance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBoundedInvariance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBoundedInvariance(this);
		}
	}

	public final BoundedInvarianceContext boundedInvariance() throws RecognitionException {
		BoundedInvarianceContext _localctx = new BoundedInvarianceContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_boundedInvariance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			always();
			setState(290);
			match(T__19);
			setState(291);
			expr(0);
			setState(292);
			match(T__8);
			setState(293);
			match(T__0);
			setState(294);
			match(T__20);
			setState(295);
			expr(0);
			setState(296);
			match(T__8);
			setState(297);
			match(T__38);
			setState(298);
			match(T__16);
			setState(299);
			match(T__39);
			setState(300);
			time();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InvariantContext extends ParserRuleContext {
		public AlwaysContext always() {
			return getRuleContext(AlwaysContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public InvariantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invariant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterInvariant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitInvariant(this);
		}
	}

	public final InvariantContext invariant() throws RecognitionException {
		InvariantContext _localctx = new InvariantContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_invariant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			always();
			setState(303);
			match(T__19);
			setState(304);
			expr(0);
			setState(305);
			match(T__8);
			setState(306);
			match(T__0);
			setState(307);
			match(T__20);
			setState(308);
			expr(0);
			setState(309);
			match(T__8);
			setState(310);
			match(T__41);
			setState(311);
			match(T__42);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BracketExpressionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BracketExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBracketExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBracketExpression(this);
		}
	}
	public static class BooleanExpressionContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BooleanExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitBooleanExpression(this);
		}
	}
	public static class IDExpressionContext extends ExprContext {
		public TerminalNode ID() { return getToken(RequirementsGrammarParser.ID, 0); }
		public IDExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterIDExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitIDExpression(this);
		}
	}
	public static class UnaryExpressionContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitUnaryExpression(this);
		}
	}
	public static class CompareExpressionContext extends ExprContext {
		public List<TerminalNode> ID() { return getTokens(RequirementsGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RequirementsGrammarParser.ID, i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public CompareExpressionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterCompareExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitCompareExpression(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new BracketExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(314);
				match(T__43);
				setState(315);
				expr(0);
				setState(316);
				match(T__44);
				}
				break;
			case 2:
				{
				_localctx = new CompareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(320);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(318);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(319);
					number();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(322);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(325);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(323);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(324);
					number();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(327);
				match(T__30);
				setState(328);
				expr(2);
				}
				break;
			case 4:
				{
				_localctx = new IDExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(329);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BooleanExpressionContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(332);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(333);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__51) | (1L << T__52))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(334);
					expr(4);
					}
					} 
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TimeContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode TIME_UNIT() { return getToken(RequirementsGrammarParser.TIME_UNIT, 0); }
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitTime(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			number();
			setState(341);
			match(TIME_UNIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlwaysContext extends ParserRuleContext {
		public AlwaysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_always; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterAlways(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitAlways(this);
		}
	}

	public final AlwaysContext always() throws RecognitionException {
		AlwaysContext _localctx = new AlwaysContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_always);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(T__37);
			setState(344);
			match(T__23);
			setState(345);
			match(T__53);
			setState(346);
			match(T__54);
			setState(347);
			match(T__55);
			setState(348);
			match(T__56);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NeverContext extends ParserRuleContext {
		public NeverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_never; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterNever(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitNever(this);
		}
	}

	public final NeverContext never() throws RecognitionException {
		NeverContext _localctx = new NeverContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_never);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__37);
			setState(351);
			match(T__23);
			setState(352);
			match(T__57);
			setState(353);
			match(T__54);
			setState(354);
			match(T__55);
			setState(355);
			match(T__56);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(RequirementsGrammarParser.POSITIVE_INT, 0); }
		public TerminalNode FLOAT() { return getToken(RequirementsGrammarParser.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			_la = _input.LA(1);
			if ( !(_la==POSITIVE_INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PositiveIntContext extends ParserRuleContext {
		public TerminalNode POSITIVE_INT() { return getToken(RequirementsGrammarParser.POSITIVE_INT, 0); }
		public PositiveIntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positiveInt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterPositiveInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitPositiveInt(this);
		}
	}

	public final PositiveIntContext positiveInt() throws RecognitionException {
		PositiveIntContext _localctx = new PositiveIntContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_positiveInt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(POSITIVE_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 23:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3B\u016c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\6\2>\n\2\r\2\16"+
		"\2?\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4V\n\4\3\5\3\5\5\5Z\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6h\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\5\22\u00f3\n\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\5\31\u0143\n\31\3\31\3\31\3\31\5\31\u0148\n\31\3\31\3"+
		"\31\3\31\5\31\u014d\n\31\3\31\3\31\3\31\7\31\u0152\n\31\f\31\16\31\u0155"+
		"\13\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\2\3\60\37\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\5\3\2\60\65\4\2\t\t"+
		"\66\67\3\2=>\2\u0169\2=\3\2\2\2\4A\3\2\2\2\6U\3\2\2\2\bY\3\2\2\2\ng\3"+
		"\2\2\2\fi\3\2\2\2\16m\3\2\2\2\20q\3\2\2\2\22u\3\2\2\2\24\u0082\3\2\2\2"+
		"\26\u008c\3\2\2\2\30\u009b\3\2\2\2\32\u00aa\3\2\2\2\34\u00b4\3\2\2\2\36"+
		"\u00cd\3\2\2\2 \u00de\3\2\2\2\"\u00f2\3\2\2\2$\u00f4\3\2\2\2&\u0101\3"+
		"\2\2\2(\u010e\3\2\2\2*\u0116\3\2\2\2,\u0123\3\2\2\2.\u0130\3\2\2\2\60"+
		"\u014c\3\2\2\2\62\u0156\3\2\2\2\64\u0159\3\2\2\2\66\u0160\3\2\2\28\u0167"+
		"\3\2\2\2:\u0169\3\2\2\2<>\5\4\3\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2"+
		"\2\2@\3\3\2\2\2AB\5\6\4\2BC\7\3\2\2CD\5\b\5\2DE\7\4\2\2E\5\3\2\2\2FV\7"+
		"\5\2\2GH\7\6\2\2HV\5\60\31\2IJ\7\7\2\2JV\5\60\31\2KL\7\b\2\2LM\5\60\31"+
		"\2MN\7\t\2\2NO\5\60\31\2OV\3\2\2\2PQ\7\7\2\2QR\5\60\31\2RS\7\n\2\2ST\5"+
		"\60\31\2TV\3\2\2\2UF\3\2\2\2UG\3\2\2\2UI\3\2\2\2UK\3\2\2\2UP\3\2\2\2V"+
		"\7\3\2\2\2WZ\5\n\6\2XZ\5\"\22\2YW\3\2\2\2YX\3\2\2\2Z\t\3\2\2\2[h\5\f\7"+
		"\2\\h\5\16\b\2]h\5\20\t\2^h\5\22\n\2_h\5\24\13\2`h\5\26\f\2ah\5\30\r\2"+
		"bh\5\32\16\2ch\5 \21\2dh\5\36\20\2eh\5\34\17\2fh\5.\30\2g[\3\2\2\2g\\"+
		"\3\2\2\2g]\3\2\2\2g^\3\2\2\2g_\3\2\2\2g`\3\2\2\2ga\3\2\2\2gb\3\2\2\2g"+
		"c\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2h\13\3\2\2\2ij\5\66\34\2jk\5\60"+
		"\31\2kl\7\13\2\2l\r\3\2\2\2mn\5\64\33\2no\5\60\31\2op\7\13\2\2p\17\3\2"+
		"\2\2qr\5\60\31\2rs\7\f\2\2st\7\13\2\2t\21\3\2\2\2uv\7\r\2\2vw\7\16\2\2"+
		"wx\7\17\2\2xy\7\20\2\2yz\7\21\2\2z{\5\60\31\2{|\7\13\2\2|}\7\22\2\2}~"+
		"\7\23\2\2~\177\7\24\2\2\177\u0080\5:\36\2\u0080\u0081\7\25\2\2\u0081\23"+
		"\3\2\2\2\u0082\u0083\5\64\33\2\u0083\u0084\7\26\2\2\u0084\u0085\5\60\31"+
		"\2\u0085\u0086\7\13\2\2\u0086\u0087\7\3\2\2\u0087\u0088\7\27\2\2\u0088"+
		"\u0089\5\60\31\2\u0089\u008a\7\30\2\2\u008a\u008b\7\31\2\2\u008b\25\3"+
		"\2\2\2\u008c\u008d\5\64\33\2\u008d\u008e\7\26\2\2\u008e\u008f\5\60\31"+
		"\2\u008f\u0090\7\13\2\2\u0090\u0091\7\t\2\2\u0091\u0092\7\32\2\2\u0092"+
		"\u0093\7\33\2\2\u0093\u0094\7\34\2\2\u0094\u0095\5\60\31\2\u0095\u0096"+
		"\7\3\2\2\u0096\u0097\7\27\2\2\u0097\u0098\5\60\31\2\u0098\u0099\7\30\2"+
		"\2\u0099\u009a\7\31\2\2\u009a\27\3\2\2\2\u009b\u009c\5\64\33\2\u009c\u009d"+
		"\7\26\2\2\u009d\u009e\5\60\31\2\u009e\u009f\7\13\2\2\u009f\u00a0\7\3\2"+
		"\2\u00a0\u00a1\7\27\2\2\u00a1\u00a2\5\60\31\2\u00a2\u00a3\7\30\2\2\u00a3"+
		"\u00a4\7\31\2\2\u00a4\u00a5\7\t\2\2\u00a5\u00a6\7\35\2\2\u00a6\u00a7\7"+
		"\36\2\2\u00a7\u00a8\7\34\2\2\u00a8\u00a9\5\60\31\2\u00a9\31\3\2\2\2\u00aa"+
		"\u00ab\5\64\33\2\u00ab\u00ac\7\26\2\2\u00ac\u00ad\5\60\31\2\u00ad\u00ae"+
		"\7\13\2\2\u00ae\u00af\7\3\2\2\u00af\u00b0\7\27\2\2\u00b0\u00b1\5\60\31"+
		"\2\u00b1\u00b2\7\f\2\2\u00b2\u00b3\7\13\2\2\u00b3\33\3\2\2\2\u00b4\u00b5"+
		"\5\64\33\2\u00b5\u00b6\7\26\2\2\u00b6\u00b7\5\60\31\2\u00b7\u00b8\7\13"+
		"\2\2\u00b8\u00b9\7\3\2\2\u00b9\u00ba\7\27\2\2\u00ba\u00bb\5\60\31\2\u00bb"+
		"\u00bc\7\f\2\2\u00bc\u00bd\7\13\2\2\u00bd\u00be\7\t\2\2\u00be\u00bf\7"+
		"\32\2\2\u00bf\u00c0\7\33\2\2\u00c0\u00c1\7\34\2\2\u00c1\u00c2\5\60\31"+
		"\2\u00c2\u00c3\7\3\2\2\u00c3\u00c4\7\37\2\2\u00c4\u00c5\5\60\31\2\u00c5"+
		"\u00c6\7 \2\2\u00c6\u00c7\7!\2\2\u00c7\u00c8\7\"\2\2\u00c8\u00c9\7#\2"+
		"\2\u00c9\u00ca\5\60\31\2\u00ca\u00cb\7\t\2\2\u00cb\u00cc\5\60\31\2\u00cc"+
		"\35\3\2\2\2\u00cd\u00ce\5\64\33\2\u00ce\u00cf\7\26\2\2\u00cf\u00d0\5\60"+
		"\31\2\u00d0\u00d1\7\13\2\2\u00d1\u00d2\7\t\2\2\u00d2\u00d3\7\32\2\2\u00d3"+
		"\u00d4\7\33\2\2\u00d4\u00d5\7\34\2\2\u00d5\u00d6\5\60\31\2\u00d6\u00d7"+
		"\7\3\2\2\u00d7\u00d8\7\27\2\2\u00d8\u00d9\5\60\31\2\u00d9\u00da\7\f\2"+
		"\2\u00da\u00db\7\13\2\2\u00db\u00dc\7$\2\2\u00dc\u00dd\5\60\31\2\u00dd"+
		"\37\3\2\2\2\u00de\u00df\5\64\33\2\u00df\u00e0\7\26\2\2\u00e0\u00e1\5\60"+
		"\31\2\u00e1\u00e2\7\13\2\2\u00e2\u00e3\7\3\2\2\u00e3\u00e4\7\27\2\2\u00e4"+
		"\u00e5\5\60\31\2\u00e5\u00e6\7\f\2\2\u00e6\u00e7\7\13\2\2\u00e7\u00e8"+
		"\7\t\2\2\u00e8\u00e9\7\32\2\2\u00e9\u00ea\7\33\2\2\u00ea\u00eb\7\34\2"+
		"\2\u00eb\u00ec\5\60\31\2\u00ec!\3\2\2\2\u00ed\u00f3\5$\23\2\u00ee\u00f3"+
		"\5&\24\2\u00ef\u00f3\5(\25\2\u00f0\u00f3\5*\26\2\u00f1\u00f3\5,\27\2\u00f2"+
		"\u00ed\3\2\2\2\u00f2\u00ee\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f1\3\2\2\2\u00f3#\3\2\2\2\u00f4\u00f5\5\64\33\2\u00f5\u00f6"+
		"\7%\2\2\u00f6\u00f7\5\60\31\2\u00f7\u00f8\7&\2\2\u00f8\u00f9\7\'\2\2\u00f9"+
		"\u00fa\7\3\2\2\u00fa\u00fb\7(\2\2\u00fb\u00fc\7\13\2\2\u00fc\u00fd\7)"+
		"\2\2\u00fd\u00fe\7\23\2\2\u00fe\u00ff\7*\2\2\u00ff\u0100\5\62\32\2\u0100"+
		"%\3\2\2\2\u0101\u0102\5\64\33\2\u0102\u0103\7%\2\2\u0103\u0104\5\60\31"+
		"\2\u0104\u0105\7&\2\2\u0105\u0106\7\'\2\2\u0106\u0107\7\3\2\2\u0107\u0108"+
		"\7(\2\2\u0108\u0109\7\13\2\2\u0109\u010a\7)\2\2\u010a\u010b\7\23\2\2\u010b"+
		"\u010c\7\24\2\2\u010c\u010d\5\62\32\2\u010d\'\3\2\2\2\u010e\u010f\5\64"+
		"\33\2\u010f\u0110\5\60\31\2\u0110\u0111\7\13\2\2\u0111\u0112\7\23\2\2"+
		"\u0112\u0113\7*\2\2\u0113\u0114\7+\2\2\u0114\u0115\5\62\32\2\u0115)\3"+
		"\2\2\2\u0116\u0117\5\64\33\2\u0117\u0118\7\26\2\2\u0118\u0119\5\60\31"+
		"\2\u0119\u011a\7\13\2\2\u011a\u011b\7\3\2\2\u011b\u011c\7\27\2\2\u011c"+
		"\u011d\5\60\31\2\u011d\u011e\7\13\2\2\u011e\u011f\7$\2\2\u011f\u0120\7"+
		"\23\2\2\u0120\u0121\7\24\2\2\u0121\u0122\5\62\32\2\u0122+\3\2\2\2\u0123"+
		"\u0124\5\64\33\2\u0124\u0125\7\26\2\2\u0125\u0126\5\60\31\2\u0126\u0127"+
		"\7\13\2\2\u0127\u0128\7\3\2\2\u0128\u0129\7\27\2\2\u0129\u012a\5\60\31"+
		"\2\u012a\u012b\7\13\2\2\u012b\u012c\7)\2\2\u012c\u012d\7\23\2\2\u012d"+
		"\u012e\7*\2\2\u012e\u012f\5\62\32\2\u012f-\3\2\2\2\u0130\u0131\5\64\33"+
		"\2\u0131\u0132\7\26\2\2\u0132\u0133\5\60\31\2\u0133\u0134\7\13\2\2\u0134"+
		"\u0135\7\3\2\2\u0135\u0136\7\27\2\2\u0136\u0137\5\60\31\2\u0137\u0138"+
		"\7\13\2\2\u0138\u0139\7,\2\2\u0139\u013a\7-\2\2\u013a/\3\2\2\2\u013b\u013c"+
		"\b\31\1\2\u013c\u013d\7.\2\2\u013d\u013e\5\60\31\2\u013e\u013f\7/\2\2"+
		"\u013f\u014d\3\2\2\2\u0140\u0143\7@\2\2\u0141\u0143\58\35\2\u0142\u0140"+
		"\3\2\2\2\u0142\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0147\t\2\2\2\u0145"+
		"\u0148\7@\2\2\u0146\u0148\58\35\2\u0147\u0145\3\2\2\2\u0147\u0146\3\2"+
		"\2\2\u0148\u014d\3\2\2\2\u0149\u014a\7!\2\2\u014a\u014d\5\60\31\4\u014b"+
		"\u014d\7@\2\2\u014c\u013b\3\2\2\2\u014c\u0142\3\2\2\2\u014c\u0149\3\2"+
		"\2\2\u014c\u014b\3\2\2\2\u014d\u0153\3\2\2\2\u014e\u014f\f\5\2\2\u014f"+
		"\u0150\t\3\2\2\u0150\u0152\5\60\31\6\u0151\u014e\3\2\2\2\u0152\u0155\3"+
		"\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\61\3\2\2\2\u0155"+
		"\u0153\3\2\2\2\u0156\u0157\58\35\2\u0157\u0158\7?\2\2\u0158\63\3\2\2\2"+
		"\u0159\u015a\7(\2\2\u015a\u015b\7\32\2\2\u015b\u015c\78\2\2\u015c\u015d"+
		"\79\2\2\u015d\u015e\7:\2\2\u015e\u015f\7;\2\2\u015f\65\3\2\2\2\u0160\u0161"+
		"\7(\2\2\u0161\u0162\7\32\2\2\u0162\u0163\7<\2\2\u0163\u0164\79\2\2\u0164"+
		"\u0165\7:\2\2\u0165\u0166\7;\2\2\u0166\67\3\2\2\2\u0167\u0168\t\4\2\2"+
		"\u01689\3\2\2\2\u0169\u016a\7=\2\2\u016a;\3\2\2\2\13?UYg\u00f2\u0142\u0147"+
		"\u014c\u0153";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}