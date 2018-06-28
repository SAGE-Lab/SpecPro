// Generated from RequirementsGrammar.g4 by ANTLR 4.7.1
package it.sagelab.fe.snl2fl.parser;
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
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, POSITIVE_INT=62, FLOAT=63, TIME_UNIT=64, ID=65, GENERIC_ID=66, 
		REQ_LABEL=67, WS=68, LINE_COMMENT=69;
	public static final int
		RULE_file = 0, RULE_requirement = 1, RULE_scope = 2, RULE_reqID = 3, RULE_rId = 4, 
		RULE_specification = 5, RULE_qualitative = 6, RULE_absence = 7, RULE_universality = 8, 
		RULE_existence = 9, RULE_boundedExistence = 10, RULE_precedence = 11, 
		RULE_precedenceChain12 = 12, RULE_precedenceChain21 = 13, RULE_response = 14, 
		RULE_constrainedChain12 = 15, RULE_responseChain21 = 16, RULE_responseChain12 = 17, 
		RULE_realtime = 18, RULE_minDuration = 19, RULE_maxDuration = 20, RULE_boundedRecurrence = 21, 
		RULE_boundedResponse = 22, RULE_boundedInvariance = 23, RULE_invariant = 24, 
		RULE_expr = 25, RULE_time = 26, RULE_always = 27, RULE_never = 28, RULE_number = 29, 
		RULE_positiveInt = 30;
	public static final String[] ruleNames = {
		"file", "requirement", "scope", "reqID", "rId", "specification", "qualitative", 
		"absence", "universality", "existence", "boundedExistence", "precedence", 
		"precedenceChain12", "precedenceChain21", "response", "constrainedChain12", 
		"responseChain21", "responseChain12", "realtime", "minDuration", "maxDuration", 
		"boundedRecurrence", "boundedResponse", "boundedInvariance", "invariant", 
		"expr", "time", "always", "never", "number", "positiveInt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'.'", "'Globally'", "'Before'", "'After'", "'Between'", 
		"'and'", "'until'", "'['", "']'", "'holds'", "'eventually'", "'transitions'", 
		"'to'", "'states'", "'in'", "'which'", "'occur'", "'at'", "'most'", "'times'", 
		"'if'", "'then'", "'previously'", "'held'", "'is'", "'succeeded'", "'by'", 
		"'was'", "'preceded'", "'where'", "'does'", "'not'", "'hold'", "'between'", 
		"'after'", "'once'", "'becomes'", "'satisfied'", "'it'", "'for'", "'least'", 
		"'every'", "'as'", "'well'", "'('", "')'", "'='", "'>'", "'>='", "'<'", 
		"'<='", "'!='", "'or'", "'xor'", "'It'", "'always'", "'the'", "'case'", 
		"'that'", "'never'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "POSITIVE_INT", "FLOAT", "TIME_UNIT", "ID", "GENERIC_ID", 
		"REQ_LABEL", "WS", "LINE_COMMENT"
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
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				requirement();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 3)) & ~0x3f) == 0 && ((1L << (_la - 3)) & ((1L << (T__2 - 3)) | (1L << (T__3 - 3)) | (1L << (T__4 - 3)) | (1L << (T__5 - 3)) | (1L << (T__8 - 3)) | (1L << (T__12 - 3)) | (1L << (T__32 - 3)) | (1L << (T__39 - 3)) | (1L << (T__45 - 3)) | (1L << (T__55 - 3)) | (1L << (POSITIVE_INT - 3)) | (1L << (FLOAT - 3)) | (1L << (ID - 3)))) != 0) );
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
		public SpecificationContext specification() {
			return getRuleContext(SpecificationContext.class,0);
		}
		public ReqIDContext reqID() {
			return getRuleContext(ReqIDContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(67);
				reqID();
				}
			}

			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				setState(70);
				scope();
				setState(71);
				match(T__0);
				}
			}

			setState(75);
			specification();
			setState(76);
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
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(T__3);
				setState(80);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(T__4);
				setState(82);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				match(T__5);
				setState(84);
				expr(0);
				setState(85);
				match(T__6);
				setState(86);
				expr(0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(88);
				match(T__4);
				setState(89);
				expr(0);
				setState(90);
				match(T__7);
				setState(91);
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

	public static class ReqIDContext extends ParserRuleContext {
		public RIdContext rId() {
			return getRuleContext(RIdContext.class,0);
		}
		public TerminalNode REQ_LABEL() { return getToken(RequirementsGrammarParser.REQ_LABEL, 0); }
		public ReqIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reqID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterReqID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitReqID(this);
		}
	}

	public final ReqIDContext reqID() throws RecognitionException {
		ReqIDContext _localctx = new ReqIDContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reqID);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__8);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REQ_LABEL) {
				{
				setState(96);
				match(REQ_LABEL);
				}
			}

			setState(99);
			rId();
			setState(100);
			match(T__9);
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

	public static class RIdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RequirementsGrammarParser.ID, 0); }
		public TerminalNode GENERIC_ID() { return getToken(RequirementsGrammarParser.GENERIC_ID, 0); }
		public TerminalNode POSITIVE_INT() { return getToken(RequirementsGrammarParser.POSITIVE_INT, 0); }
		public RIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRId(this);
		}
	}

	public final RIdContext rId() throws RecognitionException {
		RIdContext _localctx = new RIdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (POSITIVE_INT - 62)) | (1L << (ID - 62)) | (1L << (GENERIC_ID - 62)))) != 0)) ) {
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
		enterRule(_localctx, 10, RULE_specification);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				qualitative();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
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
		enterRule(_localctx, 12, RULE_qualitative);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				absence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				universality();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				existence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				boundedExistence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(112);
				precedence();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(113);
				precedenceChain12();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(114);
				precedenceChain21();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(115);
				response();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(116);
				responseChain12();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(117);
				responseChain21();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(118);
				constrainedChain12();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(119);
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
		enterRule(_localctx, 14, RULE_absence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			never();
			setState(123);
			expr(0);
			setState(124);
			match(T__10);
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
		enterRule(_localctx, 16, RULE_universality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			always();
			setState(127);
			expr(0);
			setState(128);
			match(T__10);
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
		enterRule(_localctx, 18, RULE_existence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			expr(0);
			setState(131);
			match(T__11);
			setState(132);
			match(T__10);
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
		enterRule(_localctx, 20, RULE_boundedExistence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__12);
			setState(135);
			match(T__13);
			setState(136);
			match(T__14);
			setState(137);
			match(T__15);
			setState(138);
			match(T__16);
			setState(139);
			expr(0);
			setState(140);
			match(T__10);
			setState(141);
			match(T__17);
			setState(142);
			match(T__18);
			setState(143);
			match(T__19);
			setState(144);
			positiveInt();
			setState(145);
			match(T__20);
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
		enterRule(_localctx, 22, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			always();
			setState(148);
			match(T__21);
			setState(149);
			expr(0);
			setState(150);
			match(T__10);
			setState(151);
			match(T__0);
			setState(152);
			match(T__22);
			setState(153);
			expr(0);
			setState(154);
			match(T__23);
			setState(155);
			match(T__24);
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
		enterRule(_localctx, 24, RULE_precedenceChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			always();
			setState(158);
			match(T__21);
			setState(159);
			expr(0);
			setState(160);
			match(T__10);
			setState(161);
			match(T__6);
			setState(162);
			match(T__25);
			setState(163);
			match(T__26);
			setState(164);
			match(T__27);
			setState(165);
			expr(0);
			setState(166);
			match(T__0);
			setState(167);
			match(T__22);
			setState(168);
			expr(0);
			setState(169);
			match(T__23);
			setState(170);
			match(T__24);
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
		enterRule(_localctx, 26, RULE_precedenceChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			always();
			setState(173);
			match(T__21);
			setState(174);
			expr(0);
			setState(175);
			match(T__10);
			setState(176);
			match(T__0);
			setState(177);
			match(T__22);
			setState(178);
			expr(0);
			setState(179);
			match(T__23);
			setState(180);
			match(T__24);
			setState(181);
			match(T__6);
			setState(182);
			match(T__28);
			setState(183);
			match(T__29);
			setState(184);
			match(T__27);
			setState(185);
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
		enterRule(_localctx, 28, RULE_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			always();
			setState(188);
			match(T__21);
			setState(189);
			expr(0);
			setState(190);
			match(T__10);
			setState(191);
			match(T__0);
			setState(192);
			match(T__22);
			setState(193);
			expr(0);
			setState(194);
			match(T__11);
			setState(195);
			match(T__10);
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
		enterRule(_localctx, 30, RULE_constrainedChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			always();
			setState(198);
			match(T__21);
			setState(199);
			expr(0);
			setState(200);
			match(T__10);
			setState(201);
			match(T__0);
			setState(202);
			match(T__22);
			setState(203);
			expr(0);
			setState(204);
			match(T__11);
			setState(205);
			match(T__10);
			setState(206);
			match(T__6);
			setState(207);
			match(T__25);
			setState(208);
			match(T__26);
			setState(209);
			match(T__27);
			setState(210);
			expr(0);
			setState(211);
			match(T__0);
			setState(212);
			match(T__30);
			setState(213);
			expr(0);
			setState(214);
			match(T__31);
			setState(215);
			match(T__32);
			setState(216);
			match(T__33);
			setState(217);
			match(T__34);
			setState(218);
			expr(0);
			setState(219);
			match(T__6);
			setState(220);
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
		enterRule(_localctx, 32, RULE_responseChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			always();
			setState(223);
			match(T__21);
			setState(224);
			expr(0);
			setState(225);
			match(T__10);
			setState(226);
			match(T__6);
			setState(227);
			match(T__25);
			setState(228);
			match(T__26);
			setState(229);
			match(T__27);
			setState(230);
			expr(0);
			setState(231);
			match(T__0);
			setState(232);
			match(T__22);
			setState(233);
			expr(0);
			setState(234);
			match(T__11);
			setState(235);
			match(T__10);
			setState(236);
			match(T__35);
			setState(237);
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
		enterRule(_localctx, 34, RULE_responseChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			always();
			setState(240);
			match(T__21);
			setState(241);
			expr(0);
			setState(242);
			match(T__10);
			setState(243);
			match(T__0);
			setState(244);
			match(T__22);
			setState(245);
			expr(0);
			setState(246);
			match(T__11);
			setState(247);
			match(T__10);
			setState(248);
			match(T__6);
			setState(249);
			match(T__25);
			setState(250);
			match(T__26);
			setState(251);
			match(T__27);
			setState(252);
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
		enterRule(_localctx, 36, RULE_realtime);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				minDuration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				maxDuration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				boundedRecurrence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				boundedResponse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
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
		enterRule(_localctx, 38, RULE_minDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			always();
			setState(262);
			match(T__36);
			setState(263);
			expr(0);
			setState(264);
			match(T__37);
			setState(265);
			match(T__38);
			setState(266);
			match(T__0);
			setState(267);
			match(T__39);
			setState(268);
			match(T__10);
			setState(269);
			match(T__40);
			setState(270);
			match(T__18);
			setState(271);
			match(T__41);
			setState(272);
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
		enterRule(_localctx, 40, RULE_maxDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			always();
			setState(275);
			match(T__36);
			setState(276);
			expr(0);
			setState(277);
			match(T__37);
			setState(278);
			match(T__38);
			setState(279);
			match(T__0);
			setState(280);
			match(T__39);
			setState(281);
			match(T__10);
			setState(282);
			match(T__40);
			setState(283);
			match(T__18);
			setState(284);
			match(T__19);
			setState(285);
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
		enterRule(_localctx, 42, RULE_boundedRecurrence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			always();
			setState(288);
			expr(0);
			setState(289);
			match(T__10);
			setState(290);
			match(T__18);
			setState(291);
			match(T__41);
			setState(292);
			match(T__42);
			setState(293);
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
		enterRule(_localctx, 44, RULE_boundedResponse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			always();
			setState(296);
			match(T__21);
			setState(297);
			expr(0);
			setState(298);
			match(T__10);
			setState(299);
			match(T__0);
			setState(300);
			match(T__22);
			setState(301);
			expr(0);
			setState(302);
			match(T__10);
			setState(303);
			match(T__35);
			setState(304);
			match(T__18);
			setState(305);
			match(T__19);
			setState(306);
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
		enterRule(_localctx, 46, RULE_boundedInvariance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			always();
			setState(309);
			match(T__21);
			setState(310);
			expr(0);
			setState(311);
			match(T__10);
			setState(312);
			match(T__0);
			setState(313);
			match(T__22);
			setState(314);
			expr(0);
			setState(315);
			match(T__10);
			setState(316);
			match(T__40);
			setState(317);
			match(T__18);
			setState(318);
			match(T__41);
			setState(319);
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
		enterRule(_localctx, 48, RULE_invariant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			always();
			setState(322);
			match(T__21);
			setState(323);
			expr(0);
			setState(324);
			match(T__10);
			setState(325);
			match(T__0);
			setState(326);
			match(T__22);
			setState(327);
			expr(0);
			setState(328);
			match(T__10);
			setState(329);
			match(T__43);
			setState(330);
			match(T__44);
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
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new BracketExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(333);
				match(T__45);
				setState(334);
				expr(0);
				setState(335);
				match(T__46);
				}
				break;
			case 2:
				{
				_localctx = new CompareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(339);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(337);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(338);
					number();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(341);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(344);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(342);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(343);
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
				setState(346);
				match(T__32);
				setState(347);
				expr(2);
				}
				break;
			case 4:
				{
				_localctx = new IDExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(348);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(356);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BooleanExpressionContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(351);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(352);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__53) | (1L << T__54))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(353);
					expr(4);
					}
					} 
				}
				setState(358);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		enterRule(_localctx, 52, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			number();
			setState(360);
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
		enterRule(_localctx, 54, RULE_always);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			_la = _input.LA(1);
			if ( !(_la==T__39 || _la==T__55) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(363);
			match(T__25);
			setState(364);
			match(T__56);
			setState(365);
			match(T__57);
			setState(366);
			match(T__58);
			setState(367);
			match(T__59);
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
		enterRule(_localctx, 56, RULE_never);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			_la = _input.LA(1);
			if ( !(_la==T__39 || _la==T__55) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(370);
			match(T__25);
			setState(371);
			match(T__60);
			setState(372);
			match(T__57);
			setState(373);
			match(T__58);
			setState(374);
			match(T__59);
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
		enterRule(_localctx, 58, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
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
		enterRule(_localctx, 60, RULE_positiveInt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
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
		case 25:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3G\u017f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\6\2B\n\2\r\2\16\2C\3\3\5\3G\n\3\3\3\3\3\3\3\5\3L\n\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4`\n\4\3\5"+
		"\3\5\5\5d\n\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\5\7m\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b{\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u0106"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0156\n\33\3\33\3\33"+
		"\3\33\5\33\u015b\n\33\3\33\3\33\3\33\5\33\u0160\n\33\3\33\3\33\3\33\7"+
		"\33\u0165\n\33\f\33\16\33\u0168\13\33\3\34\3\34\3\34\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 "+
		"\3 \2\3\64!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>\2\7\4\2@@CD\3\2\62\67\4\2\t\t89\4\2**::\3\2@A\2\u017d\2A\3\2\2\2"+
		"\4F\3\2\2\2\6_\3\2\2\2\ba\3\2\2\2\nh\3\2\2\2\fl\3\2\2\2\16z\3\2\2\2\20"+
		"|\3\2\2\2\22\u0080\3\2\2\2\24\u0084\3\2\2\2\26\u0088\3\2\2\2\30\u0095"+
		"\3\2\2\2\32\u009f\3\2\2\2\34\u00ae\3\2\2\2\36\u00bd\3\2\2\2 \u00c7\3\2"+
		"\2\2\"\u00e0\3\2\2\2$\u00f1\3\2\2\2&\u0105\3\2\2\2(\u0107\3\2\2\2*\u0114"+
		"\3\2\2\2,\u0121\3\2\2\2.\u0129\3\2\2\2\60\u0136\3\2\2\2\62\u0143\3\2\2"+
		"\2\64\u015f\3\2\2\2\66\u0169\3\2\2\28\u016c\3\2\2\2:\u0173\3\2\2\2<\u017a"+
		"\3\2\2\2>\u017c\3\2\2\2@B\5\4\3\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2"+
		"\2\2D\3\3\2\2\2EG\5\b\5\2FE\3\2\2\2FG\3\2\2\2GK\3\2\2\2HI\5\6\4\2IJ\7"+
		"\3\2\2JL\3\2\2\2KH\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\5\f\7\2NO\7\4\2\2O\5"+
		"\3\2\2\2P`\7\5\2\2QR\7\6\2\2R`\5\64\33\2ST\7\7\2\2T`\5\64\33\2UV\7\b\2"+
		"\2VW\5\64\33\2WX\7\t\2\2XY\5\64\33\2Y`\3\2\2\2Z[\7\7\2\2[\\\5\64\33\2"+
		"\\]\7\n\2\2]^\5\64\33\2^`\3\2\2\2_P\3\2\2\2_Q\3\2\2\2_S\3\2\2\2_U\3\2"+
		"\2\2_Z\3\2\2\2`\7\3\2\2\2ac\7\13\2\2bd\7E\2\2cb\3\2\2\2cd\3\2\2\2de\3"+
		"\2\2\2ef\5\n\6\2fg\7\f\2\2g\t\3\2\2\2hi\t\2\2\2i\13\3\2\2\2jm\5\16\b\2"+
		"km\5&\24\2lj\3\2\2\2lk\3\2\2\2m\r\3\2\2\2n{\5\20\t\2o{\5\22\n\2p{\5\24"+
		"\13\2q{\5\26\f\2r{\5\30\r\2s{\5\32\16\2t{\5\34\17\2u{\5\36\20\2v{\5$\23"+
		"\2w{\5\"\22\2x{\5 \21\2y{\5\62\32\2zn\3\2\2\2zo\3\2\2\2zp\3\2\2\2zq\3"+
		"\2\2\2zr\3\2\2\2zs\3\2\2\2zt\3\2\2\2zu\3\2\2\2zv\3\2\2\2zw\3\2\2\2zx\3"+
		"\2\2\2zy\3\2\2\2{\17\3\2\2\2|}\5:\36\2}~\5\64\33\2~\177\7\r\2\2\177\21"+
		"\3\2\2\2\u0080\u0081\58\35\2\u0081\u0082\5\64\33\2\u0082\u0083\7\r\2\2"+
		"\u0083\23\3\2\2\2\u0084\u0085\5\64\33\2\u0085\u0086\7\16\2\2\u0086\u0087"+
		"\7\r\2\2\u0087\25\3\2\2\2\u0088\u0089\7\17\2\2\u0089\u008a\7\20\2\2\u008a"+
		"\u008b\7\21\2\2\u008b\u008c\7\22\2\2\u008c\u008d\7\23\2\2\u008d\u008e"+
		"\5\64\33\2\u008e\u008f\7\r\2\2\u008f\u0090\7\24\2\2\u0090\u0091\7\25\2"+
		"\2\u0091\u0092\7\26\2\2\u0092\u0093\5> \2\u0093\u0094\7\27\2\2\u0094\27"+
		"\3\2\2\2\u0095\u0096\58\35\2\u0096\u0097\7\30\2\2\u0097\u0098\5\64\33"+
		"\2\u0098\u0099\7\r\2\2\u0099\u009a\7\3\2\2\u009a\u009b\7\31\2\2\u009b"+
		"\u009c\5\64\33\2\u009c\u009d\7\32\2\2\u009d\u009e\7\33\2\2\u009e\31\3"+
		"\2\2\2\u009f\u00a0\58\35\2\u00a0\u00a1\7\30\2\2\u00a1\u00a2\5\64\33\2"+
		"\u00a2\u00a3\7\r\2\2\u00a3\u00a4\7\t\2\2\u00a4\u00a5\7\34\2\2\u00a5\u00a6"+
		"\7\35\2\2\u00a6\u00a7\7\36\2\2\u00a7\u00a8\5\64\33\2\u00a8\u00a9\7\3\2"+
		"\2\u00a9\u00aa\7\31\2\2\u00aa\u00ab\5\64\33\2\u00ab\u00ac\7\32\2\2\u00ac"+
		"\u00ad\7\33\2\2\u00ad\33\3\2\2\2\u00ae\u00af\58\35\2\u00af\u00b0\7\30"+
		"\2\2\u00b0\u00b1\5\64\33\2\u00b1\u00b2\7\r\2\2\u00b2\u00b3\7\3\2\2\u00b3"+
		"\u00b4\7\31\2\2\u00b4\u00b5\5\64\33\2\u00b5\u00b6\7\32\2\2\u00b6\u00b7"+
		"\7\33\2\2\u00b7\u00b8\7\t\2\2\u00b8\u00b9\7\37\2\2\u00b9\u00ba\7 \2\2"+
		"\u00ba\u00bb\7\36\2\2\u00bb\u00bc\5\64\33\2\u00bc\35\3\2\2\2\u00bd\u00be"+
		"\58\35\2\u00be\u00bf\7\30\2\2\u00bf\u00c0\5\64\33\2\u00c0\u00c1\7\r\2"+
		"\2\u00c1\u00c2\7\3\2\2\u00c2\u00c3\7\31\2\2\u00c3\u00c4\5\64\33\2\u00c4"+
		"\u00c5\7\16\2\2\u00c5\u00c6\7\r\2\2\u00c6\37\3\2\2\2\u00c7\u00c8\58\35"+
		"\2\u00c8\u00c9\7\30\2\2\u00c9\u00ca\5\64\33\2\u00ca\u00cb\7\r\2\2\u00cb"+
		"\u00cc\7\3\2\2\u00cc\u00cd\7\31\2\2\u00cd\u00ce\5\64\33\2\u00ce\u00cf"+
		"\7\16\2\2\u00cf\u00d0\7\r\2\2\u00d0\u00d1\7\t\2\2\u00d1\u00d2\7\34\2\2"+
		"\u00d2\u00d3\7\35\2\2\u00d3\u00d4\7\36\2\2\u00d4\u00d5\5\64\33\2\u00d5"+
		"\u00d6\7\3\2\2\u00d6\u00d7\7!\2\2\u00d7\u00d8\5\64\33\2\u00d8\u00d9\7"+
		"\"\2\2\u00d9\u00da\7#\2\2\u00da\u00db\7$\2\2\u00db\u00dc\7%\2\2\u00dc"+
		"\u00dd\5\64\33\2\u00dd\u00de\7\t\2\2\u00de\u00df\5\64\33\2\u00df!\3\2"+
		"\2\2\u00e0\u00e1\58\35\2\u00e1\u00e2\7\30\2\2\u00e2\u00e3\5\64\33\2\u00e3"+
		"\u00e4\7\r\2\2\u00e4\u00e5\7\t\2\2\u00e5\u00e6\7\34\2\2\u00e6\u00e7\7"+
		"\35\2\2\u00e7\u00e8\7\36\2\2\u00e8\u00e9\5\64\33\2\u00e9\u00ea\7\3\2\2"+
		"\u00ea\u00eb\7\31\2\2\u00eb\u00ec\5\64\33\2\u00ec\u00ed\7\16\2\2\u00ed"+
		"\u00ee\7\r\2\2\u00ee\u00ef\7&\2\2\u00ef\u00f0\5\64\33\2\u00f0#\3\2\2\2"+
		"\u00f1\u00f2\58\35\2\u00f2\u00f3\7\30\2\2\u00f3\u00f4\5\64\33\2\u00f4"+
		"\u00f5\7\r\2\2\u00f5\u00f6\7\3\2\2\u00f6\u00f7\7\31\2\2\u00f7\u00f8\5"+
		"\64\33\2\u00f8\u00f9\7\16\2\2\u00f9\u00fa\7\r\2\2\u00fa\u00fb\7\t\2\2"+
		"\u00fb\u00fc\7\34\2\2\u00fc\u00fd\7\35\2\2\u00fd\u00fe\7\36\2\2\u00fe"+
		"\u00ff\5\64\33\2\u00ff%\3\2\2\2\u0100\u0106\5(\25\2\u0101\u0106\5*\26"+
		"\2\u0102\u0106\5,\27\2\u0103\u0106\5.\30\2\u0104\u0106\5\60\31\2\u0105"+
		"\u0100\3\2\2\2\u0105\u0101\3\2\2\2\u0105\u0102\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0105\u0104\3\2\2\2\u0106\'\3\2\2\2\u0107\u0108\58\35\2\u0108\u0109"+
		"\7\'\2\2\u0109\u010a\5\64\33\2\u010a\u010b\7(\2\2\u010b\u010c\7)\2\2\u010c"+
		"\u010d\7\3\2\2\u010d\u010e\7*\2\2\u010e\u010f\7\r\2\2\u010f\u0110\7+\2"+
		"\2\u0110\u0111\7\25\2\2\u0111\u0112\7,\2\2\u0112\u0113\5\66\34\2\u0113"+
		")\3\2\2\2\u0114\u0115\58\35\2\u0115\u0116\7\'\2\2\u0116\u0117\5\64\33"+
		"\2\u0117\u0118\7(\2\2\u0118\u0119\7)\2\2\u0119\u011a\7\3\2\2\u011a\u011b"+
		"\7*\2\2\u011b\u011c\7\r\2\2\u011c\u011d\7+\2\2\u011d\u011e\7\25\2\2\u011e"+
		"\u011f\7\26\2\2\u011f\u0120\5\66\34\2\u0120+\3\2\2\2\u0121\u0122\58\35"+
		"\2\u0122\u0123\5\64\33\2\u0123\u0124\7\r\2\2\u0124\u0125\7\25\2\2\u0125"+
		"\u0126\7,\2\2\u0126\u0127\7-\2\2\u0127\u0128\5\66\34\2\u0128-\3\2\2\2"+
		"\u0129\u012a\58\35\2\u012a\u012b\7\30\2\2\u012b\u012c\5\64\33\2\u012c"+
		"\u012d\7\r\2\2\u012d\u012e\7\3\2\2\u012e\u012f\7\31\2\2\u012f\u0130\5"+
		"\64\33\2\u0130\u0131\7\r\2\2\u0131\u0132\7&\2\2\u0132\u0133\7\25\2\2\u0133"+
		"\u0134\7\26\2\2\u0134\u0135\5\66\34\2\u0135/\3\2\2\2\u0136\u0137\58\35"+
		"\2\u0137\u0138\7\30\2\2\u0138\u0139\5\64\33\2\u0139\u013a\7\r\2\2\u013a"+
		"\u013b\7\3\2\2\u013b\u013c\7\31\2\2\u013c\u013d\5\64\33\2\u013d\u013e"+
		"\7\r\2\2\u013e\u013f\7+\2\2\u013f\u0140\7\25\2\2\u0140\u0141\7,\2\2\u0141"+
		"\u0142\5\66\34\2\u0142\61\3\2\2\2\u0143\u0144\58\35\2\u0144\u0145\7\30"+
		"\2\2\u0145\u0146\5\64\33\2\u0146\u0147\7\r\2\2\u0147\u0148\7\3\2\2\u0148"+
		"\u0149\7\31\2\2\u0149\u014a\5\64\33\2\u014a\u014b\7\r\2\2\u014b\u014c"+
		"\7.\2\2\u014c\u014d\7/\2\2\u014d\63\3\2\2\2\u014e\u014f\b\33\1\2\u014f"+
		"\u0150\7\60\2\2\u0150\u0151\5\64\33\2\u0151\u0152\7\61\2\2\u0152\u0160"+
		"\3\2\2\2\u0153\u0156\7C\2\2\u0154\u0156\5<\37\2\u0155\u0153\3\2\2\2\u0155"+
		"\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u015a\t\3\2\2\u0158\u015b\7C"+
		"\2\2\u0159\u015b\5<\37\2\u015a\u0158\3\2\2\2\u015a\u0159\3\2\2\2\u015b"+
		"\u0160\3\2\2\2\u015c\u015d\7#\2\2\u015d\u0160\5\64\33\4\u015e\u0160\7"+
		"C\2\2\u015f\u014e\3\2\2\2\u015f\u0155\3\2\2\2\u015f\u015c\3\2\2\2\u015f"+
		"\u015e\3\2\2\2\u0160\u0166\3\2\2\2\u0161\u0162\f\5\2\2\u0162\u0163\t\4"+
		"\2\2\u0163\u0165\5\64\33\6\u0164\u0161\3\2\2\2\u0165\u0168\3\2\2\2\u0166"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\65\3\2\2\2\u0168\u0166\3\2\2"+
		"\2\u0169\u016a\5<\37\2\u016a\u016b\7B\2\2\u016b\67\3\2\2\2\u016c\u016d"+
		"\t\5\2\2\u016d\u016e\7\34\2\2\u016e\u016f\7;\2\2\u016f\u0170\7<\2\2\u0170"+
		"\u0171\7=\2\2\u0171\u0172\7>\2\2\u01729\3\2\2\2\u0173\u0174\t\5\2\2\u0174"+
		"\u0175\7\34\2\2\u0175\u0176\7?\2\2\u0176\u0177\7<\2\2\u0177\u0178\7=\2"+
		"\2\u0178\u0179\7>\2\2\u0179;\3\2\2\2\u017a\u017b\t\6\2\2\u017b=\3\2\2"+
		"\2\u017c\u017d\7@\2\2\u017d?\3\2\2\2\16CFK_clz\u0105\u0155\u015a\u015f"+
		"\u0166";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}