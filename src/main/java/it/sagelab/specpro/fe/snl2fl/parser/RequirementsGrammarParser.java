// Generated from RequirementsGrammar.g4 by ANTLR 4.7.1
package it.sagelab.specpro.fe.snl2fl.parser;
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
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, POSITIVE_INT=66, 
		FLOAT=67, TIME_UNIT=68, ID=69, GENERIC_ID=70, REQ_LABEL=71, WS=72, LINE_COMMENT=73;
	public static final int
		RULE_file = 0, RULE_varDeclaration = 1, RULE_varType = 2, RULE_requirement = 3, 
		RULE_scope = 4, RULE_reqID = 5, RULE_rId = 6, RULE_specification = 7, 
		RULE_qualitative = 8, RULE_absence = 9, RULE_universality = 10, RULE_existence = 11, 
		RULE_boundedExistence = 12, RULE_precedence = 13, RULE_precedenceChain12 = 14, 
		RULE_precedenceChain21 = 15, RULE_response = 16, RULE_constrainedChain12 = 17, 
		RULE_responseChain21 = 18, RULE_responseChain12 = 19, RULE_realtime = 20, 
		RULE_minDuration = 21, RULE_maxDuration = 22, RULE_boundedRecurrence = 23, 
		RULE_boundedResponse = 24, RULE_boundedInvariance = 25, RULE_invariant = 26, 
		RULE_expr = 27, RULE_time = 28, RULE_always = 29, RULE_never = 30, RULE_number = 31, 
		RULE_positiveInt = 32;
	public static final String[] ruleNames = {
		"file", "varDeclaration", "varType", "requirement", "scope", "reqID", 
		"rId", "specification", "qualitative", "absence", "universality", "existence", 
		"boundedExistence", "precedence", "precedenceChain12", "precedenceChain21", 
		"response", "constrainedChain12", "responseChain21", "responseChain12", 
		"realtime", "minDuration", "maxDuration", "boundedRecurrence", "boundedResponse", 
		"boundedInvariance", "invariant", "expr", "time", "always", "never", "number", 
		"positiveInt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'is'", "'an'", "'variable'", "'.'", "'input'", "'output'", "','", 
		"'Globally'", "'Before'", "'After'", "'Between'", "'and'", "'until'", 
		"'['", "']'", "'holds'", "'eventually'", "'transitions'", "'to'", "'states'", 
		"'in'", "'which'", "'occur'", "'at'", "'most'", "'times'", "'if'", "'then'", 
		"'previously'", "'held'", "'succeeded'", "'by'", "'was'", "'preceded'", 
		"'where'", "'does'", "'not'", "'hold'", "'between'", "'after'", "'once'", 
		"'becomes'", "'satisfied'", "'it'", "'for'", "'least'", "'every'", "'as'", 
		"'well'", "'('", "')'", "'='", "'>'", "'>='", "'<'", "'<='", "'!='", "'or'", 
		"'xor'", "'It'", "'always'", "'the'", "'case'", "'that'", "'never'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "POSITIVE_INT", "FLOAT", "TIME_UNIT", 
		"ID", "GENERIC_ID", "REQ_LABEL", "WS", "LINE_COMMENT"
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
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
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
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(66);
					varDeclaration();
					}
					break;
				case 2:
					{
					setState(67);
					requirement();
					}
					break;
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 8)) & ~0x3f) == 0 && ((1L << (_la - 8)) & ((1L << (T__7 - 8)) | (1L << (T__8 - 8)) | (1L << (T__9 - 8)) | (1L << (T__10 - 8)) | (1L << (T__13 - 8)) | (1L << (T__17 - 8)) | (1L << (T__36 - 8)) | (1L << (T__43 - 8)) | (1L << (T__49 - 8)) | (1L << (T__59 - 8)) | (1L << (POSITIVE_INT - 8)) | (1L << (FLOAT - 8)) | (1L << (ID - 8)))) != 0) );
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

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RequirementsGrammarParser.ID, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitVarDeclaration(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(ID);
			setState(73);
			match(T__0);
			setState(74);
			match(T__1);
			setState(75);
			varType();
			setState(76);
			match(T__2);
			setState(77);
			match(T__3);
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

	public static class VarTypeContext extends ParserRuleContext {
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitVarType(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
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
		enterRule(_localctx, 6, RULE_requirement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(81);
				reqID();
				}
			}

			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) {
				{
				setState(84);
				scope();
				setState(85);
				match(T__6);
				}
			}

			setState(89);
			specification();
			setState(90);
			match(T__3);
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
		enterRule(_localctx, 8, RULE_scope);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(T__7);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(T__8);
				setState(94);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				match(T__9);
				setState(96);
				expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				match(T__10);
				setState(98);
				expr(0);
				setState(99);
				match(T__11);
				setState(100);
				expr(0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(102);
				match(T__9);
				setState(103);
				expr(0);
				setState(104);
				match(T__12);
				setState(105);
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
		enterRule(_localctx, 10, RULE_reqID);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__13);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REQ_LABEL) {
				{
				setState(110);
				match(REQ_LABEL);
				}
			}

			setState(113);
			rId();
			setState(114);
			match(T__14);
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
		enterRule(_localctx, 12, RULE_rId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_la = _input.LA(1);
			if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (POSITIVE_INT - 66)) | (1L << (ID - 66)) | (1L << (GENERIC_ID - 66)))) != 0)) ) {
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
		enterRule(_localctx, 14, RULE_specification);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				qualitative();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
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
		enterRule(_localctx, 16, RULE_qualitative);
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				absence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				universality();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				existence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				boundedExistence();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				precedence();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(127);
				precedenceChain12();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(128);
				precedenceChain21();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(129);
				response();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(130);
				responseChain12();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(131);
				responseChain21();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(132);
				constrainedChain12();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(133);
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
		enterRule(_localctx, 18, RULE_absence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			never();
			setState(137);
			expr(0);
			setState(138);
			match(T__15);
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
		enterRule(_localctx, 20, RULE_universality);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			always();
			setState(141);
			expr(0);
			setState(142);
			match(T__15);
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
		enterRule(_localctx, 22, RULE_existence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			expr(0);
			setState(145);
			match(T__16);
			setState(146);
			match(T__15);
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
		enterRule(_localctx, 24, RULE_boundedExistence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__17);
			setState(149);
			match(T__18);
			setState(150);
			match(T__19);
			setState(151);
			match(T__20);
			setState(152);
			match(T__21);
			setState(153);
			expr(0);
			setState(154);
			match(T__15);
			setState(155);
			match(T__22);
			setState(156);
			match(T__23);
			setState(157);
			match(T__24);
			setState(158);
			positiveInt();
			setState(159);
			match(T__25);
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
		enterRule(_localctx, 26, RULE_precedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			always();
			setState(162);
			match(T__26);
			setState(163);
			expr(0);
			setState(164);
			match(T__15);
			setState(165);
			match(T__6);
			setState(166);
			match(T__27);
			setState(167);
			expr(0);
			setState(168);
			match(T__28);
			setState(169);
			match(T__29);
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
		enterRule(_localctx, 28, RULE_precedenceChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			always();
			setState(172);
			match(T__26);
			setState(173);
			expr(0);
			setState(174);
			match(T__15);
			setState(175);
			match(T__11);
			setState(176);
			match(T__0);
			setState(177);
			match(T__30);
			setState(178);
			match(T__31);
			setState(179);
			expr(0);
			setState(180);
			match(T__6);
			setState(181);
			match(T__27);
			setState(182);
			expr(0);
			setState(183);
			match(T__28);
			setState(184);
			match(T__29);
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
		enterRule(_localctx, 30, RULE_precedenceChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			always();
			setState(187);
			match(T__26);
			setState(188);
			expr(0);
			setState(189);
			match(T__15);
			setState(190);
			match(T__6);
			setState(191);
			match(T__27);
			setState(192);
			expr(0);
			setState(193);
			match(T__28);
			setState(194);
			match(T__29);
			setState(195);
			match(T__11);
			setState(196);
			match(T__32);
			setState(197);
			match(T__33);
			setState(198);
			match(T__31);
			setState(199);
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
		enterRule(_localctx, 32, RULE_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			always();
			setState(202);
			match(T__26);
			setState(203);
			expr(0);
			setState(204);
			match(T__15);
			setState(205);
			match(T__6);
			setState(206);
			match(T__27);
			setState(207);
			expr(0);
			setState(208);
			match(T__16);
			setState(209);
			match(T__15);
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
		enterRule(_localctx, 34, RULE_constrainedChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			always();
			setState(212);
			match(T__26);
			setState(213);
			expr(0);
			setState(214);
			match(T__15);
			setState(215);
			match(T__6);
			setState(216);
			match(T__27);
			setState(217);
			expr(0);
			setState(218);
			match(T__16);
			setState(219);
			match(T__15);
			setState(220);
			match(T__11);
			setState(221);
			match(T__0);
			setState(222);
			match(T__30);
			setState(223);
			match(T__31);
			setState(224);
			expr(0);
			setState(225);
			match(T__6);
			setState(226);
			match(T__34);
			setState(227);
			expr(0);
			setState(228);
			match(T__35);
			setState(229);
			match(T__36);
			setState(230);
			match(T__37);
			setState(231);
			match(T__38);
			setState(232);
			expr(0);
			setState(233);
			match(T__11);
			setState(234);
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
		enterRule(_localctx, 36, RULE_responseChain21);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			always();
			setState(237);
			match(T__26);
			setState(238);
			expr(0);
			setState(239);
			match(T__15);
			setState(240);
			match(T__11);
			setState(241);
			match(T__0);
			setState(242);
			match(T__30);
			setState(243);
			match(T__31);
			setState(244);
			expr(0);
			setState(245);
			match(T__6);
			setState(246);
			match(T__27);
			setState(247);
			expr(0);
			setState(248);
			match(T__16);
			setState(249);
			match(T__15);
			setState(250);
			match(T__39);
			setState(251);
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
		enterRule(_localctx, 38, RULE_responseChain12);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			always();
			setState(254);
			match(T__26);
			setState(255);
			expr(0);
			setState(256);
			match(T__15);
			setState(257);
			match(T__6);
			setState(258);
			match(T__27);
			setState(259);
			expr(0);
			setState(260);
			match(T__16);
			setState(261);
			match(T__15);
			setState(262);
			match(T__11);
			setState(263);
			match(T__0);
			setState(264);
			match(T__30);
			setState(265);
			match(T__31);
			setState(266);
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
		enterRule(_localctx, 40, RULE_realtime);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				minDuration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				maxDuration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				boundedRecurrence();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				boundedResponse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(272);
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
		enterRule(_localctx, 42, RULE_minDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			always();
			setState(276);
			match(T__40);
			setState(277);
			expr(0);
			setState(278);
			match(T__41);
			setState(279);
			match(T__42);
			setState(280);
			match(T__6);
			setState(281);
			match(T__43);
			setState(282);
			match(T__15);
			setState(283);
			match(T__44);
			setState(284);
			match(T__23);
			setState(285);
			match(T__45);
			setState(286);
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
		enterRule(_localctx, 44, RULE_maxDuration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			always();
			setState(289);
			match(T__40);
			setState(290);
			expr(0);
			setState(291);
			match(T__41);
			setState(292);
			match(T__42);
			setState(293);
			match(T__6);
			setState(294);
			match(T__43);
			setState(295);
			match(T__15);
			setState(296);
			match(T__44);
			setState(297);
			match(T__23);
			setState(298);
			match(T__24);
			setState(299);
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
		enterRule(_localctx, 46, RULE_boundedRecurrence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			always();
			setState(302);
			expr(0);
			setState(303);
			match(T__15);
			setState(304);
			match(T__23);
			setState(305);
			match(T__45);
			setState(306);
			match(T__46);
			setState(307);
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
		enterRule(_localctx, 48, RULE_boundedResponse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			always();
			setState(310);
			match(T__26);
			setState(311);
			expr(0);
			setState(312);
			match(T__15);
			setState(313);
			match(T__6);
			setState(314);
			match(T__27);
			setState(315);
			expr(0);
			setState(316);
			match(T__15);
			setState(317);
			match(T__39);
			setState(318);
			match(T__23);
			setState(319);
			match(T__24);
			setState(320);
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
		enterRule(_localctx, 50, RULE_boundedInvariance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			always();
			setState(323);
			match(T__26);
			setState(324);
			expr(0);
			setState(325);
			match(T__15);
			setState(326);
			match(T__6);
			setState(327);
			match(T__27);
			setState(328);
			expr(0);
			setState(329);
			match(T__15);
			setState(330);
			match(T__44);
			setState(331);
			match(T__23);
			setState(332);
			match(T__45);
			setState(333);
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
		enterRule(_localctx, 52, RULE_invariant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			always();
			setState(336);
			match(T__26);
			setState(337);
			expr(0);
			setState(338);
			match(T__15);
			setState(339);
			match(T__6);
			setState(340);
			match(T__27);
			setState(341);
			expr(0);
			setState(342);
			match(T__15);
			setState(343);
			match(T__47);
			setState(344);
			match(T__48);
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
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new BracketExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(347);
				match(T__49);
				setState(348);
				expr(0);
				setState(349);
				match(T__50);
				}
				break;
			case 2:
				{
				_localctx = new CompareExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(353);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(351);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(352);
					number();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(355);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(358);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(356);
					match(ID);
					}
					break;
				case POSITIVE_INT:
				case FLOAT:
					{
					setState(357);
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
				setState(360);
				match(T__36);
				setState(361);
				expr(2);
				}
				break;
			case 4:
				{
				_localctx = new IDExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(362);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(370);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BooleanExpressionContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(365);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(366);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__57) | (1L << T__58))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(367);
					expr(4);
					}
					} 
				}
				setState(372);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
		enterRule(_localctx, 56, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			number();
			setState(374);
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
		enterRule(_localctx, 58, RULE_always);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_la = _input.LA(1);
			if ( !(_la==T__43 || _la==T__59) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(377);
			match(T__0);
			setState(378);
			match(T__60);
			setState(379);
			match(T__61);
			setState(380);
			match(T__62);
			setState(381);
			match(T__63);
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
		enterRule(_localctx, 60, RULE_never);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			_la = _input.LA(1);
			if ( !(_la==T__43 || _la==T__59) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(384);
			match(T__0);
			setState(385);
			match(T__64);
			setState(386);
			match(T__61);
			setState(387);
			match(T__62);
			setState(388);
			match(T__63);
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
		enterRule(_localctx, 62, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
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
		enterRule(_localctx, 64, RULE_positiveInt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
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
		case 27:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3K\u018d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\6\2G\n\2\r\2\16\2H\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\5\5\5U\n\5\3\5\3\5\3\5\5\5Z\n\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6n\n\6\3\7\3\7\5\7r\n\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\5\t{\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\5\n\u0089\n\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\5\26\u0114\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0164"+
		"\n\35\3\35\3\35\3\35\5\35\u0169\n\35\3\35\3\35\3\35\5\35\u016e\n\35\3"+
		"\35\3\35\3\35\7\35\u0173\n\35\f\35\16\35\u0176\13\35\3\36\3\36\3\36\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3"+
		"\"\2\38#\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668"+
		":<>@B\2\b\3\2\7\b\4\2DDGH\3\2\66;\4\2\16\16<=\4\2..>>\3\2DE\2\u018a\2"+
		"F\3\2\2\2\4J\3\2\2\2\6Q\3\2\2\2\bT\3\2\2\2\nm\3\2\2\2\fo\3\2\2\2\16v\3"+
		"\2\2\2\20z\3\2\2\2\22\u0088\3\2\2\2\24\u008a\3\2\2\2\26\u008e\3\2\2\2"+
		"\30\u0092\3\2\2\2\32\u0096\3\2\2\2\34\u00a3\3\2\2\2\36\u00ad\3\2\2\2 "+
		"\u00bc\3\2\2\2\"\u00cb\3\2\2\2$\u00d5\3\2\2\2&\u00ee\3\2\2\2(\u00ff\3"+
		"\2\2\2*\u0113\3\2\2\2,\u0115\3\2\2\2.\u0122\3\2\2\2\60\u012f\3\2\2\2\62"+
		"\u0137\3\2\2\2\64\u0144\3\2\2\2\66\u0151\3\2\2\28\u016d\3\2\2\2:\u0177"+
		"\3\2\2\2<\u017a\3\2\2\2>\u0181\3\2\2\2@\u0188\3\2\2\2B\u018a\3\2\2\2D"+
		"G\5\4\3\2EG\5\b\5\2FD\3\2\2\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2"+
		"I\3\3\2\2\2JK\7G\2\2KL\7\3\2\2LM\7\4\2\2MN\5\6\4\2NO\7\5\2\2OP\7\6\2\2"+
		"P\5\3\2\2\2QR\t\2\2\2R\7\3\2\2\2SU\5\f\7\2TS\3\2\2\2TU\3\2\2\2UY\3\2\2"+
		"\2VW\5\n\6\2WX\7\t\2\2XZ\3\2\2\2YV\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\5\20"+
		"\t\2\\]\7\6\2\2]\t\3\2\2\2^n\7\n\2\2_`\7\13\2\2`n\58\35\2ab\7\f\2\2bn"+
		"\58\35\2cd\7\r\2\2de\58\35\2ef\7\16\2\2fg\58\35\2gn\3\2\2\2hi\7\f\2\2"+
		"ij\58\35\2jk\7\17\2\2kl\58\35\2ln\3\2\2\2m^\3\2\2\2m_\3\2\2\2ma\3\2\2"+
		"\2mc\3\2\2\2mh\3\2\2\2n\13\3\2\2\2oq\7\20\2\2pr\7I\2\2qp\3\2\2\2qr\3\2"+
		"\2\2rs\3\2\2\2st\5\16\b\2tu\7\21\2\2u\r\3\2\2\2vw\t\3\2\2w\17\3\2\2\2"+
		"x{\5\22\n\2y{\5*\26\2zx\3\2\2\2zy\3\2\2\2{\21\3\2\2\2|\u0089\5\24\13\2"+
		"}\u0089\5\26\f\2~\u0089\5\30\r\2\177\u0089\5\32\16\2\u0080\u0089\5\34"+
		"\17\2\u0081\u0089\5\36\20\2\u0082\u0089\5 \21\2\u0083\u0089\5\"\22\2\u0084"+
		"\u0089\5(\25\2\u0085\u0089\5&\24\2\u0086\u0089\5$\23\2\u0087\u0089\5\66"+
		"\34\2\u0088|\3\2\2\2\u0088}\3\2\2\2\u0088~\3\2\2\2\u0088\177\3\2\2\2\u0088"+
		"\u0080\3\2\2\2\u0088\u0081\3\2\2\2\u0088\u0082\3\2\2\2\u0088\u0083\3\2"+
		"\2\2\u0088\u0084\3\2\2\2\u0088\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0087\3\2\2\2\u0089\23\3\2\2\2\u008a\u008b\5> \2\u008b\u008c\58\35\2"+
		"\u008c\u008d\7\22\2\2\u008d\25\3\2\2\2\u008e\u008f\5<\37\2\u008f\u0090"+
		"\58\35\2\u0090\u0091\7\22\2\2\u0091\27\3\2\2\2\u0092\u0093\58\35\2\u0093"+
		"\u0094\7\23\2\2\u0094\u0095\7\22\2\2\u0095\31\3\2\2\2\u0096\u0097\7\24"+
		"\2\2\u0097\u0098\7\25\2\2\u0098\u0099\7\26\2\2\u0099\u009a\7\27\2\2\u009a"+
		"\u009b\7\30\2\2\u009b\u009c\58\35\2\u009c\u009d\7\22\2\2\u009d\u009e\7"+
		"\31\2\2\u009e\u009f\7\32\2\2\u009f\u00a0\7\33\2\2\u00a0\u00a1\5B\"\2\u00a1"+
		"\u00a2\7\34\2\2\u00a2\33\3\2\2\2\u00a3\u00a4\5<\37\2\u00a4\u00a5\7\35"+
		"\2\2\u00a5\u00a6\58\35\2\u00a6\u00a7\7\22\2\2\u00a7\u00a8\7\t\2\2\u00a8"+
		"\u00a9\7\36\2\2\u00a9\u00aa\58\35\2\u00aa\u00ab\7\37\2\2\u00ab\u00ac\7"+
		" \2\2\u00ac\35\3\2\2\2\u00ad\u00ae\5<\37\2\u00ae\u00af\7\35\2\2\u00af"+
		"\u00b0\58\35\2\u00b0\u00b1\7\22\2\2\u00b1\u00b2\7\16\2\2\u00b2\u00b3\7"+
		"\3\2\2\u00b3\u00b4\7!\2\2\u00b4\u00b5\7\"\2\2\u00b5\u00b6\58\35\2\u00b6"+
		"\u00b7\7\t\2\2\u00b7\u00b8\7\36\2\2\u00b8\u00b9\58\35\2\u00b9\u00ba\7"+
		"\37\2\2\u00ba\u00bb\7 \2\2\u00bb\37\3\2\2\2\u00bc\u00bd\5<\37\2\u00bd"+
		"\u00be\7\35\2\2\u00be\u00bf\58\35\2\u00bf\u00c0\7\22\2\2\u00c0\u00c1\7"+
		"\t\2\2\u00c1\u00c2\7\36\2\2\u00c2\u00c3\58\35\2\u00c3\u00c4\7\37\2\2\u00c4"+
		"\u00c5\7 \2\2\u00c5\u00c6\7\16\2\2\u00c6\u00c7\7#\2\2\u00c7\u00c8\7$\2"+
		"\2\u00c8\u00c9\7\"\2\2\u00c9\u00ca\58\35\2\u00ca!\3\2\2\2\u00cb\u00cc"+
		"\5<\37\2\u00cc\u00cd\7\35\2\2\u00cd\u00ce\58\35\2\u00ce\u00cf\7\22\2\2"+
		"\u00cf\u00d0\7\t\2\2\u00d0\u00d1\7\36\2\2\u00d1\u00d2\58\35\2\u00d2\u00d3"+
		"\7\23\2\2\u00d3\u00d4\7\22\2\2\u00d4#\3\2\2\2\u00d5\u00d6\5<\37\2\u00d6"+
		"\u00d7\7\35\2\2\u00d7\u00d8\58\35\2\u00d8\u00d9\7\22\2\2\u00d9\u00da\7"+
		"\t\2\2\u00da\u00db\7\36\2\2\u00db\u00dc\58\35\2\u00dc\u00dd\7\23\2\2\u00dd"+
		"\u00de\7\22\2\2\u00de\u00df\7\16\2\2\u00df\u00e0\7\3\2\2\u00e0\u00e1\7"+
		"!\2\2\u00e1\u00e2\7\"\2\2\u00e2\u00e3\58\35\2\u00e3\u00e4\7\t\2\2\u00e4"+
		"\u00e5\7%\2\2\u00e5\u00e6\58\35\2\u00e6\u00e7\7&\2\2\u00e7\u00e8\7\'\2"+
		"\2\u00e8\u00e9\7(\2\2\u00e9\u00ea\7)\2\2\u00ea\u00eb\58\35\2\u00eb\u00ec"+
		"\7\16\2\2\u00ec\u00ed\58\35\2\u00ed%\3\2\2\2\u00ee\u00ef\5<\37\2\u00ef"+
		"\u00f0\7\35\2\2\u00f0\u00f1\58\35\2\u00f1\u00f2\7\22\2\2\u00f2\u00f3\7"+
		"\16\2\2\u00f3\u00f4\7\3\2\2\u00f4\u00f5\7!\2\2\u00f5\u00f6\7\"\2\2\u00f6"+
		"\u00f7\58\35\2\u00f7\u00f8\7\t\2\2\u00f8\u00f9\7\36\2\2\u00f9\u00fa\5"+
		"8\35\2\u00fa\u00fb\7\23\2\2\u00fb\u00fc\7\22\2\2\u00fc\u00fd\7*\2\2\u00fd"+
		"\u00fe\58\35\2\u00fe\'\3\2\2\2\u00ff\u0100\5<\37\2\u0100\u0101\7\35\2"+
		"\2\u0101\u0102\58\35\2\u0102\u0103\7\22\2\2\u0103\u0104\7\t\2\2\u0104"+
		"\u0105\7\36\2\2\u0105\u0106\58\35\2\u0106\u0107\7\23\2\2\u0107\u0108\7"+
		"\22\2\2\u0108\u0109\7\16\2\2\u0109\u010a\7\3\2\2\u010a\u010b\7!\2\2\u010b"+
		"\u010c\7\"\2\2\u010c\u010d\58\35\2\u010d)\3\2\2\2\u010e\u0114\5,\27\2"+
		"\u010f\u0114\5.\30\2\u0110\u0114\5\60\31\2\u0111\u0114\5\62\32\2\u0112"+
		"\u0114\5\64\33\2\u0113\u010e\3\2\2\2\u0113\u010f\3\2\2\2\u0113\u0110\3"+
		"\2\2\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2\u0114+\3\2\2\2\u0115\u0116"+
		"\5<\37\2\u0116\u0117\7+\2\2\u0117\u0118\58\35\2\u0118\u0119\7,\2\2\u0119"+
		"\u011a\7-\2\2\u011a\u011b\7\t\2\2\u011b\u011c\7.\2\2\u011c\u011d\7\22"+
		"\2\2\u011d\u011e\7/\2\2\u011e\u011f\7\32\2\2\u011f\u0120\7\60\2\2\u0120"+
		"\u0121\5:\36\2\u0121-\3\2\2\2\u0122\u0123\5<\37\2\u0123\u0124\7+\2\2\u0124"+
		"\u0125\58\35\2\u0125\u0126\7,\2\2\u0126\u0127\7-\2\2\u0127\u0128\7\t\2"+
		"\2\u0128\u0129\7.\2\2\u0129\u012a\7\22\2\2\u012a\u012b\7/\2\2\u012b\u012c"+
		"\7\32\2\2\u012c\u012d\7\33\2\2\u012d\u012e\5:\36\2\u012e/\3\2\2\2\u012f"+
		"\u0130\5<\37\2\u0130\u0131\58\35\2\u0131\u0132\7\22\2\2\u0132\u0133\7"+
		"\32\2\2\u0133\u0134\7\60\2\2\u0134\u0135\7\61\2\2\u0135\u0136\5:\36\2"+
		"\u0136\61\3\2\2\2\u0137\u0138\5<\37\2\u0138\u0139\7\35\2\2\u0139\u013a"+
		"\58\35\2\u013a\u013b\7\22\2\2\u013b\u013c\7\t\2\2\u013c\u013d\7\36\2\2"+
		"\u013d\u013e\58\35\2\u013e\u013f\7\22\2\2\u013f\u0140\7*\2\2\u0140\u0141"+
		"\7\32\2\2\u0141\u0142\7\33\2\2\u0142\u0143\5:\36\2\u0143\63\3\2\2\2\u0144"+
		"\u0145\5<\37\2\u0145\u0146\7\35\2\2\u0146\u0147\58\35\2\u0147\u0148\7"+
		"\22\2\2\u0148\u0149\7\t\2\2\u0149\u014a\7\36\2\2\u014a\u014b\58\35\2\u014b"+
		"\u014c\7\22\2\2\u014c\u014d\7/\2\2\u014d\u014e\7\32\2\2\u014e\u014f\7"+
		"\60\2\2\u014f\u0150\5:\36\2\u0150\65\3\2\2\2\u0151\u0152\5<\37\2\u0152"+
		"\u0153\7\35\2\2\u0153\u0154\58\35\2\u0154\u0155\7\22\2\2\u0155\u0156\7"+
		"\t\2\2\u0156\u0157\7\36\2\2\u0157\u0158\58\35\2\u0158\u0159\7\22\2\2\u0159"+
		"\u015a\7\62\2\2\u015a\u015b\7\63\2\2\u015b\67\3\2\2\2\u015c\u015d\b\35"+
		"\1\2\u015d\u015e\7\64\2\2\u015e\u015f\58\35\2\u015f\u0160\7\65\2\2\u0160"+
		"\u016e\3\2\2\2\u0161\u0164\7G\2\2\u0162\u0164\5@!\2\u0163\u0161\3\2\2"+
		"\2\u0163\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0168\t\4\2\2\u0166\u0169"+
		"\7G\2\2\u0167\u0169\5@!\2\u0168\u0166\3\2\2\2\u0168\u0167\3\2\2\2\u0169"+
		"\u016e\3\2\2\2\u016a\u016b\7\'\2\2\u016b\u016e\58\35\4\u016c\u016e\7G"+
		"\2\2\u016d\u015c\3\2\2\2\u016d\u0163\3\2\2\2\u016d\u016a\3\2\2\2\u016d"+
		"\u016c\3\2\2\2\u016e\u0174\3\2\2\2\u016f\u0170\f\5\2\2\u0170\u0171\t\5"+
		"\2\2\u0171\u0173\58\35\6\u0172\u016f\3\2\2\2\u0173\u0176\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u01759\3\2\2\2\u0176\u0174\3\2\2\2"+
		"\u0177\u0178\5@!\2\u0178\u0179\7F\2\2\u0179;\3\2\2\2\u017a\u017b\t\6\2"+
		"\2\u017b\u017c\7\3\2\2\u017c\u017d\7?\2\2\u017d\u017e\7@\2\2\u017e\u017f"+
		"\7A\2\2\u017f\u0180\7B\2\2\u0180=\3\2\2\2\u0181\u0182\t\6\2\2\u0182\u0183"+
		"\7\3\2\2\u0183\u0184\7C\2\2\u0184\u0185\7@\2\2\u0185\u0186\7A\2\2\u0186"+
		"\u0187\7B\2\2\u0187?\3\2\2\2\u0188\u0189\t\7\2\2\u0189A\3\2\2\2\u018a"+
		"\u018b\7D\2\2\u018bC\3\2\2\2\17FHTYmqz\u0088\u0113\u0163\u0168\u016d\u0174";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}