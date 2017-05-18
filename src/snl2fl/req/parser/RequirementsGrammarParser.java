package it.unige.pat2fl.req.parser;


// Generated from RequirementsGrammar.g by ANTLR 4.5.1
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

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
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, NUMBER=59, 
		POSITIVE_INT=60, TIME_UNIT=61, ID=62, WS=63, LINE_COMMENT=64;
	public static final int
		RULE_file = 0, RULE_requirement = 1, RULE_scope = 2, RULE_specification = 3, 
		RULE_qualitative = 4, RULE_absence = 5, RULE_universality = 6, RULE_existence = 7, 
		RULE_boundedExistence = 8, RULE_precedence = 9, RULE_precedenceChain12 = 10, 
		RULE_precedenceChain21 = 11, RULE_response = 12, RULE_constrainedChain12 = 13, 
		RULE_responseChain21 = 14, RULE_responseChain12 = 15, RULE_realtime = 16, 
		RULE_minDuration = 17, RULE_maxDuration = 18, RULE_boundedRecurrence = 19, 
		RULE_boundedResponse = 20, RULE_boundedInvariance = 21, RULE_invariant = 22, 
		RULE_expr = 23, RULE_time = 24, RULE_always = 25, RULE_never = 26;
	public static final String[] ruleNames = {
		"file", "requirement", "scope", "specification", "qualitative", "absence", 
		"universality", "existence", "boundedExistence", "precedence", "precedenceChain12", 
		"precedenceChain21", "response", "constrainedChain12", "responseChain21", 
		"responseChain12", "realtime", "minDuration", "maxDuration", "boundedRecurrence", 
		"boundedResponse", "boundedInvariance", "invariant", "expr", "time", "always", 
		"never"
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
		null, null, null, null, null, null, null, null, null, null, null, "NUMBER", 
		"POSITIVE_INT", "TIME_UNIT", "ID", "WS", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "RequirementsGrammar.g"; }

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
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				requirement();
				}
				}
				setState(57); 
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
			setState(59);
			scope();
			setState(60);
			match(T__0);
			setState(61);
			specification();
			setState(62);
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
			setState(79);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				match(T__3);
				setState(66);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				match(T__4);
				setState(68);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				match(T__5);
				setState(70);
				expr(0);
				setState(71);
				match(T__6);
				setState(72);
				expr(0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				match(T__4);
				setState(75);
				expr(0);
				setState(76);
				match(T__7);
				setState(77);
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
			setState(83);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				qualitative();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
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
			setState(97);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				absence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				universality();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(87);
				existence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				boundedExistence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(89);
				precedence();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(90);
				precedenceChain12();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(91);
				precedenceChain21();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(92);
				response();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(93);
				responseChain12();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(94);
				responseChain21();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(95);
				constrainedChain12();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(96);
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
			setState(99);
			never();
			setState(100);
			expr(0);
			setState(101);
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
			setState(103);
			always();
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
			setState(107);
			expr(0);
			setState(108);
			match(T__9);
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

	public static class BoundedExistenceContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode POSITIVE_INT() { return getToken(RequirementsGrammarParser.POSITIVE_INT, 0); }
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
			setState(111);
			match(T__10);
			setState(112);
			match(T__11);
			setState(113);
			match(T__12);
			setState(114);
			match(T__13);
			setState(115);
			match(T__14);
			setState(116);
			expr(0);
			setState(117);
			match(T__8);
			setState(118);
			match(T__15);
			setState(119);
			match(T__16);
			setState(120);
			match(T__17);
			setState(121);
			match(POSITIVE_INT);
			setState(122);
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
			setState(124);
			always();
			setState(125);
			match(T__19);
			setState(126);
			expr(0);
			setState(127);
			match(T__8);
			setState(128);
			match(T__0);
			setState(129);
			match(T__20);
			setState(130);
			expr(0);
			setState(131);
			match(T__21);
			setState(132);
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
			setState(134);
			always();
			setState(135);
			match(T__19);
			setState(136);
			expr(0);
			setState(137);
			match(T__8);
			setState(138);
			match(T__6);
			setState(139);
			match(T__23);
			setState(140);
			match(T__24);
			setState(141);
			match(T__25);
			setState(142);
			expr(0);
			setState(143);
			match(T__0);
			setState(144);
			match(T__20);
			setState(145);
			expr(0);
			setState(146);
			match(T__21);
			setState(147);
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
			setState(149);
			always();
			setState(150);
			match(T__19);
			setState(151);
			expr(0);
			setState(152);
			match(T__8);
			setState(153);
			match(T__0);
			setState(154);
			match(T__20);
			setState(155);
			expr(0);
			setState(156);
			match(T__21);
			setState(157);
			match(T__22);
			setState(158);
			match(T__6);
			setState(159);
			match(T__26);
			setState(160);
			match(T__27);
			setState(161);
			match(T__25);
			setState(162);
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
			setState(164);
			always();
			setState(165);
			match(T__19);
			setState(166);
			expr(0);
			setState(167);
			match(T__8);
			setState(168);
			match(T__0);
			setState(169);
			match(T__20);
			setState(170);
			expr(0);
			setState(171);
			match(T__9);
			setState(172);
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
			setState(174);
			always();
			setState(175);
			match(T__19);
			setState(176);
			expr(0);
			setState(177);
			match(T__8);
			setState(178);
			match(T__0);
			setState(179);
			match(T__20);
			setState(180);
			expr(0);
			setState(181);
			match(T__9);
			setState(182);
			match(T__8);
			setState(183);
			match(T__6);
			setState(184);
			match(T__23);
			setState(185);
			match(T__24);
			setState(186);
			match(T__25);
			setState(187);
			expr(0);
			setState(188);
			match(T__0);
			setState(189);
			match(T__28);
			setState(190);
			expr(0);
			setState(191);
			match(T__29);
			setState(192);
			match(T__30);
			setState(193);
			match(T__31);
			setState(194);
			match(T__32);
			setState(195);
			expr(0);
			setState(196);
			match(T__6);
			setState(197);
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
			setState(199);
			always();
			setState(200);
			match(T__19);
			setState(201);
			expr(0);
			setState(202);
			match(T__8);
			setState(203);
			match(T__6);
			setState(204);
			match(T__23);
			setState(205);
			match(T__24);
			setState(206);
			match(T__25);
			setState(207);
			expr(0);
			setState(208);
			match(T__0);
			setState(209);
			match(T__20);
			setState(210);
			expr(0);
			setState(211);
			match(T__9);
			setState(212);
			match(T__8);
			setState(213);
			match(T__33);
			setState(214);
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
			setState(216);
			always();
			setState(217);
			match(T__19);
			setState(218);
			expr(0);
			setState(219);
			match(T__8);
			setState(220);
			match(T__0);
			setState(221);
			match(T__20);
			setState(222);
			expr(0);
			setState(223);
			match(T__9);
			setState(224);
			match(T__8);
			setState(225);
			match(T__6);
			setState(226);
			match(T__23);
			setState(227);
			match(T__24);
			setState(228);
			match(T__25);
			setState(229);
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
			setState(236);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				minDuration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				maxDuration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(233);
				boundedRecurrence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(234);
				boundedResponse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(235);
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
			setState(238);
			always();
			setState(239);
			match(T__34);
			setState(240);
			expr(0);
			setState(241);
			match(T__35);
			setState(242);
			match(T__36);
			setState(243);
			match(T__0);
			setState(244);
			match(T__37);
			setState(245);
			match(T__8);
			setState(246);
			match(T__38);
			setState(247);
			match(T__16);
			setState(248);
			match(T__39);
			setState(249);
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
			setState(251);
			always();
			setState(252);
			match(T__34);
			setState(253);
			expr(0);
			setState(254);
			match(T__35);
			setState(255);
			match(T__36);
			setState(256);
			match(T__0);
			setState(257);
			match(T__37);
			setState(258);
			match(T__8);
			setState(259);
			match(T__38);
			setState(260);
			match(T__16);
			setState(261);
			match(T__17);
			setState(262);
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
			setState(264);
			always();
			setState(265);
			expr(0);
			setState(266);
			match(T__8);
			setState(267);
			match(T__16);
			setState(268);
			match(T__39);
			setState(269);
			match(T__40);
			setState(270);
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
			setState(272);
			always();
			setState(273);
			match(T__19);
			setState(274);
			expr(0);
			setState(275);
			match(T__8);
			setState(276);
			match(T__0);
			setState(277);
			match(T__20);
			setState(278);
			expr(0);
			setState(279);
			match(T__8);
			setState(280);
			match(T__33);
			setState(281);
			match(T__16);
			setState(282);
			match(T__17);
			setState(283);
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
			setState(285);
			always();
			setState(286);
			match(T__19);
			setState(287);
			expr(0);
			setState(288);
			match(T__8);
			setState(289);
			match(T__0);
			setState(290);
			match(T__20);
			setState(291);
			expr(0);
			setState(292);
			match(T__8);
			setState(293);
			match(T__38);
			setState(294);
			match(T__16);
			setState(295);
			match(T__39);
			setState(296);
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
			setState(298);
			always();
			setState(299);
			match(T__19);
			setState(300);
			expr(0);
			setState(301);
			match(T__8);
			setState(302);
			match(T__0);
			setState(303);
			match(T__20);
			setState(304);
			expr(0);
			setState(305);
			match(T__8);
			setState(306);
			match(T__41);
			setState(307);
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
	public static class CompareExpressionContext extends ExprContext {
		public List<TerminalNode> ID() { return getTokens(RequirementsGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RequirementsGrammarParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(RequirementsGrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(RequirementsGrammarParser.NUMBER, i);
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
			setState(318);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new BracketExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(310);
				match(T__43);
				setState(311);
				expr(0);
				setState(312);
				match(T__44);
				}
				break;
			case 2:
				{
				_localctx = new CompareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(314);
				_la = _input.LA(1);
				if ( !(_la==NUMBER || _la==ID) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(315);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(316);
				_la = _input.LA(1);
				if ( !(_la==NUMBER || _la==ID) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 3:
				{
				_localctx = new IDExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(317);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(325);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BooleanExpressionContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(320);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(321);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__51) | (1L << T__52))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(322);
					expr(3);
					}
					} 
				}
				setState(327);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		public TerminalNode NUMBER() { return getToken(RequirementsGrammarParser.NUMBER, 0); }
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
			setState(328);
			match(NUMBER);
			setState(329);
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
			setState(331);
			match(T__37);
			setState(332);
			match(T__23);
			setState(333);
			match(T__53);
			setState(334);
			match(T__54);
			setState(335);
			match(T__55);
			setState(336);
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
			setState(338);
			match(T__37);
			setState(339);
			match(T__23);
			setState(340);
			match(T__57);
			setState(341);
			match(T__54);
			setState(342);
			match(T__55);
			setState(343);
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3B\u015c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4R"+
		"\n\4\3\5\3\5\5\5V\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6d\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u00ef\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u0141\n\31\3\31\3\31\3\31\7\31\u0146\n\31\f\31\16\31\u0149"+
		"\13\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\2\3\60\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\66\2\5\4\2==@@\3\2\60\65\4\2\t\t\66\67\u0158\29"+
		"\3\2\2\2\4=\3\2\2\2\6Q\3\2\2\2\bU\3\2\2\2\nc\3\2\2\2\fe\3\2\2\2\16i\3"+
		"\2\2\2\20m\3\2\2\2\22q\3\2\2\2\24~\3\2\2\2\26\u0088\3\2\2\2\30\u0097\3"+
		"\2\2\2\32\u00a6\3\2\2\2\34\u00b0\3\2\2\2\36\u00c9\3\2\2\2 \u00da\3\2\2"+
		"\2\"\u00ee\3\2\2\2$\u00f0\3\2\2\2&\u00fd\3\2\2\2(\u010a\3\2\2\2*\u0112"+
		"\3\2\2\2,\u011f\3\2\2\2.\u012c\3\2\2\2\60\u0140\3\2\2\2\62\u014a\3\2\2"+
		"\2\64\u014d\3\2\2\2\66\u0154\3\2\2\28:\5\4\3\298\3\2\2\2:;\3\2\2\2;9\3"+
		"\2\2\2;<\3\2\2\2<\3\3\2\2\2=>\5\6\4\2>?\7\3\2\2?@\5\b\5\2@A\7\4\2\2A\5"+
		"\3\2\2\2BR\7\5\2\2CD\7\6\2\2DR\5\60\31\2EF\7\7\2\2FR\5\60\31\2GH\7\b\2"+
		"\2HI\5\60\31\2IJ\7\t\2\2JK\5\60\31\2KR\3\2\2\2LM\7\7\2\2MN\5\60\31\2N"+
		"O\7\n\2\2OP\5\60\31\2PR\3\2\2\2QB\3\2\2\2QC\3\2\2\2QE\3\2\2\2QG\3\2\2"+
		"\2QL\3\2\2\2R\7\3\2\2\2SV\5\n\6\2TV\5\"\22\2US\3\2\2\2UT\3\2\2\2V\t\3"+
		"\2\2\2Wd\5\f\7\2Xd\5\16\b\2Yd\5\20\t\2Zd\5\22\n\2[d\5\24\13\2\\d\5\26"+
		"\f\2]d\5\30\r\2^d\5\32\16\2_d\5 \21\2`d\5\36\20\2ad\5\34\17\2bd\5.\30"+
		"\2cW\3\2\2\2cX\3\2\2\2cY\3\2\2\2cZ\3\2\2\2c[\3\2\2\2c\\\3\2\2\2c]\3\2"+
		"\2\2c^\3\2\2\2c_\3\2\2\2c`\3\2\2\2ca\3\2\2\2cb\3\2\2\2d\13\3\2\2\2ef\5"+
		"\66\34\2fg\5\60\31\2gh\7\13\2\2h\r\3\2\2\2ij\5\64\33\2jk\5\60\31\2kl\7"+
		"\13\2\2l\17\3\2\2\2mn\5\60\31\2no\7\f\2\2op\7\13\2\2p\21\3\2\2\2qr\7\r"+
		"\2\2rs\7\16\2\2st\7\17\2\2tu\7\20\2\2uv\7\21\2\2vw\5\60\31\2wx\7\13\2"+
		"\2xy\7\22\2\2yz\7\23\2\2z{\7\24\2\2{|\7>\2\2|}\7\25\2\2}\23\3\2\2\2~\177"+
		"\5\64\33\2\177\u0080\7\26\2\2\u0080\u0081\5\60\31\2\u0081\u0082\7\13\2"+
		"\2\u0082\u0083\7\3\2\2\u0083\u0084\7\27\2\2\u0084\u0085\5\60\31\2\u0085"+
		"\u0086\7\30\2\2\u0086\u0087\7\31\2\2\u0087\25\3\2\2\2\u0088\u0089\5\64"+
		"\33\2\u0089\u008a\7\26\2\2\u008a\u008b\5\60\31\2\u008b\u008c\7\13\2\2"+
		"\u008c\u008d\7\t\2\2\u008d\u008e\7\32\2\2\u008e\u008f\7\33\2\2\u008f\u0090"+
		"\7\34\2\2\u0090\u0091\5\60\31\2\u0091\u0092\7\3\2\2\u0092\u0093\7\27\2"+
		"\2\u0093\u0094\5\60\31\2\u0094\u0095\7\30\2\2\u0095\u0096\7\31\2\2\u0096"+
		"\27\3\2\2\2\u0097\u0098\5\64\33\2\u0098\u0099\7\26\2\2\u0099\u009a\5\60"+
		"\31\2\u009a\u009b\7\13\2\2\u009b\u009c\7\3\2\2\u009c\u009d\7\27\2\2\u009d"+
		"\u009e\5\60\31\2\u009e\u009f\7\30\2\2\u009f\u00a0\7\31\2\2\u00a0\u00a1"+
		"\7\t\2\2\u00a1\u00a2\7\35\2\2\u00a2\u00a3\7\36\2\2\u00a3\u00a4\7\34\2"+
		"\2\u00a4\u00a5\5\60\31\2\u00a5\31\3\2\2\2\u00a6\u00a7\5\64\33\2\u00a7"+
		"\u00a8\7\26\2\2\u00a8\u00a9\5\60\31\2\u00a9\u00aa\7\13\2\2\u00aa\u00ab"+
		"\7\3\2\2\u00ab\u00ac\7\27\2\2\u00ac\u00ad\5\60\31\2\u00ad\u00ae\7\f\2"+
		"\2\u00ae\u00af\7\13\2\2\u00af\33\3\2\2\2\u00b0\u00b1\5\64\33\2\u00b1\u00b2"+
		"\7\26\2\2\u00b2\u00b3\5\60\31\2\u00b3\u00b4\7\13\2\2\u00b4\u00b5\7\3\2"+
		"\2\u00b5\u00b6\7\27\2\2\u00b6\u00b7\5\60\31\2\u00b7\u00b8\7\f\2\2\u00b8"+
		"\u00b9\7\13\2\2\u00b9\u00ba\7\t\2\2\u00ba\u00bb\7\32\2\2\u00bb\u00bc\7"+
		"\33\2\2\u00bc\u00bd\7\34\2\2\u00bd\u00be\5\60\31\2\u00be\u00bf\7\3\2\2"+
		"\u00bf\u00c0\7\37\2\2\u00c0\u00c1\5\60\31\2\u00c1\u00c2\7 \2\2\u00c2\u00c3"+
		"\7!\2\2\u00c3\u00c4\7\"\2\2\u00c4\u00c5\7#\2\2\u00c5\u00c6\5\60\31\2\u00c6"+
		"\u00c7\7\t\2\2\u00c7\u00c8\5\60\31\2\u00c8\35\3\2\2\2\u00c9\u00ca\5\64"+
		"\33\2\u00ca\u00cb\7\26\2\2\u00cb\u00cc\5\60\31\2\u00cc\u00cd\7\13\2\2"+
		"\u00cd\u00ce\7\t\2\2\u00ce\u00cf\7\32\2\2\u00cf\u00d0\7\33\2\2\u00d0\u00d1"+
		"\7\34\2\2\u00d1\u00d2\5\60\31\2\u00d2\u00d3\7\3\2\2\u00d3\u00d4\7\27\2"+
		"\2\u00d4\u00d5\5\60\31\2\u00d5\u00d6\7\f\2\2\u00d6\u00d7\7\13\2\2\u00d7"+
		"\u00d8\7$\2\2\u00d8\u00d9\5\60\31\2\u00d9\37\3\2\2\2\u00da\u00db\5\64"+
		"\33\2\u00db\u00dc\7\26\2\2\u00dc\u00dd\5\60\31\2\u00dd\u00de\7\13\2\2"+
		"\u00de\u00df\7\3\2\2\u00df\u00e0\7\27\2\2\u00e0\u00e1\5\60\31\2\u00e1"+
		"\u00e2\7\f\2\2\u00e2\u00e3\7\13\2\2\u00e3\u00e4\7\t\2\2\u00e4\u00e5\7"+
		"\32\2\2\u00e5\u00e6\7\33\2\2\u00e6\u00e7\7\34\2\2\u00e7\u00e8\5\60\31"+
		"\2\u00e8!\3\2\2\2\u00e9\u00ef\5$\23\2\u00ea\u00ef\5&\24\2\u00eb\u00ef"+
		"\5(\25\2\u00ec\u00ef\5*\26\2\u00ed\u00ef\5,\27\2\u00ee\u00e9\3\2\2\2\u00ee"+
		"\u00ea\3\2\2\2\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2"+
		"\2\2\u00ef#\3\2\2\2\u00f0\u00f1\5\64\33\2\u00f1\u00f2\7%\2\2\u00f2\u00f3"+
		"\5\60\31\2\u00f3\u00f4\7&\2\2\u00f4\u00f5\7\'\2\2\u00f5\u00f6\7\3\2\2"+
		"\u00f6\u00f7\7(\2\2\u00f7\u00f8\7\13\2\2\u00f8\u00f9\7)\2\2\u00f9\u00fa"+
		"\7\23\2\2\u00fa\u00fb\7*\2\2\u00fb\u00fc\5\62\32\2\u00fc%\3\2\2\2\u00fd"+
		"\u00fe\5\64\33\2\u00fe\u00ff\7%\2\2\u00ff\u0100\5\60\31\2\u0100\u0101"+
		"\7&\2\2\u0101\u0102\7\'\2\2\u0102\u0103\7\3\2\2\u0103\u0104\7(\2\2\u0104"+
		"\u0105\7\13\2\2\u0105\u0106\7)\2\2\u0106\u0107\7\23\2\2\u0107\u0108\7"+
		"\24\2\2\u0108\u0109\5\62\32\2\u0109\'\3\2\2\2\u010a\u010b\5\64\33\2\u010b"+
		"\u010c\5\60\31\2\u010c\u010d\7\13\2\2\u010d\u010e\7\23\2\2\u010e\u010f"+
		"\7*\2\2\u010f\u0110\7+\2\2\u0110\u0111\5\62\32\2\u0111)\3\2\2\2\u0112"+
		"\u0113\5\64\33\2\u0113\u0114\7\26\2\2\u0114\u0115\5\60\31\2\u0115\u0116"+
		"\7\13\2\2\u0116\u0117\7\3\2\2\u0117\u0118\7\27\2\2\u0118\u0119\5\60\31"+
		"\2\u0119\u011a\7\13\2\2\u011a\u011b\7$\2\2\u011b\u011c\7\23\2\2\u011c"+
		"\u011d\7\24\2\2\u011d\u011e\5\62\32\2\u011e+\3\2\2\2\u011f\u0120\5\64"+
		"\33\2\u0120\u0121\7\26\2\2\u0121\u0122\5\60\31\2\u0122\u0123\7\13\2\2"+
		"\u0123\u0124\7\3\2\2\u0124\u0125\7\27\2\2\u0125\u0126\5\60\31\2\u0126"+
		"\u0127\7\13\2\2\u0127\u0128\7)\2\2\u0128\u0129\7\23\2\2\u0129\u012a\7"+
		"*\2\2\u012a\u012b\5\62\32\2\u012b-\3\2\2\2\u012c\u012d\5\64\33\2\u012d"+
		"\u012e\7\26\2\2\u012e\u012f\5\60\31\2\u012f\u0130\7\13\2\2\u0130\u0131"+
		"\7\3\2\2\u0131\u0132\7\27\2\2\u0132\u0133\5\60\31\2\u0133\u0134\7\13\2"+
		"\2\u0134\u0135\7,\2\2\u0135\u0136\7-\2\2\u0136/\3\2\2\2\u0137\u0138\b"+
		"\31\1\2\u0138\u0139\7.\2\2\u0139\u013a\5\60\31\2\u013a\u013b\7/\2\2\u013b"+
		"\u0141\3\2\2\2\u013c\u013d\t\2\2\2\u013d\u013e\t\3\2\2\u013e\u0141\t\2"+
		"\2\2\u013f\u0141\7@\2\2\u0140\u0137\3\2\2\2\u0140\u013c\3\2\2\2\u0140"+
		"\u013f\3\2\2\2\u0141\u0147\3\2\2\2\u0142\u0143\f\4\2\2\u0143\u0144\t\4"+
		"\2\2\u0144\u0146\5\60\31\5\u0145\u0142\3\2\2\2\u0146\u0149\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\61\3\2\2\2\u0149\u0147\3\2\2"+
		"\2\u014a\u014b\7=\2\2\u014b\u014c\7?\2\2\u014c\63\3\2\2\2\u014d\u014e"+
		"\7(\2\2\u014e\u014f\7\32\2\2\u014f\u0150\78\2\2\u0150\u0151\79\2\2\u0151"+
		"\u0152\7:\2\2\u0152\u0153\7;\2\2\u0153\65\3\2\2\2\u0154\u0155\7(\2\2\u0155"+
		"\u0156\7\32\2\2\u0156\u0157\7<\2\2\u0157\u0158\79\2\2\u0158\u0159\7:\2"+
		"\2\u0159\u015a\7;\2\2\u015a\67\3\2\2\2\t;QUc\u00ee\u0140\u0147";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}