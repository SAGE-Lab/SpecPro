// Generated from NuSMVOutputGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.reasoners.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NuSMVOutputGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, WS=23, TRUE=24, FALSE=25, 
		INT=26, ID=27, PREAMBLE=28;
	public static final int
		RULE_output = 0, RULE_spec = 1, RULE_counterexample = 2, RULE_stateAssignment = 3, 
		RULE_startLoop = 4, RULE_assignment = 5, RULE_booleanAssignment = 6, RULE_enumAssignment = 7, 
		RULE_formula = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"output", "spec", "counterexample", "stateAssignment", "startLoop", "assignment", 
			"booleanAssignment", "enumAssignment", "formula"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-- specification'", "'is'", "'true'", "'false'", "'-- as demonstrated by the following execution sequence'", 
			"'Trace Description: LTL Counterexample'", "'Trace Type: Counterexample'", 
			"'->'", "'State:'", "'.'", "'<-'", "'-- Loop starts here'", "' = '", 
			"'!'", "'&'", "'|'", "'('", "')'", "'<->'", "'G'", "'F'", "'X'", null, 
			"'TRUE'", "'FALSE'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "WS", 
			"TRUE", "FALSE", "INT", "ID", "PREAMBLE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "NuSMVOutputGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NuSMVOutputGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class OutputContext extends ParserRuleContext {
		public List<SpecContext> spec() {
			return getRuleContexts(SpecContext.class);
		}
		public SpecContext spec(int i) {
			return getRuleContext(SpecContext.class,i);
		}
		public OutputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_output; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterOutput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitOutput(this);
		}
	}

	public final OutputContext output() throws RecognitionException {
		OutputContext _localctx = new OutputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_output);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(18);
				spec();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SpecContext extends ParserRuleContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public CounterexampleContext counterexample() {
			return getRuleContext(CounterexampleContext.class,0);
		}
		public SpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitSpec(this);
		}
	}

	public final SpecContext spec() throws RecognitionException {
		SpecContext _localctx = new SpecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_spec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__0);
			setState(25);
			formula();
			setState(26);
			match(T__1);
			setState(27);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(28);
				counterexample();
				}
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

	public static class CounterexampleContext extends ParserRuleContext {
		public List<StateAssignmentContext> stateAssignment() {
			return getRuleContexts(StateAssignmentContext.class);
		}
		public StateAssignmentContext stateAssignment(int i) {
			return getRuleContext(StateAssignmentContext.class,i);
		}
		public CounterexampleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_counterexample; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterCounterexample(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitCounterexample(this);
		}
	}

	public final CounterexampleContext counterexample() throws RecognitionException {
		CounterexampleContext _localctx = new CounterexampleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_counterexample);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(T__4);
			setState(32);
			match(T__5);
			setState(33);
			match(T__6);
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				stateAssignment();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__7 || _la==T__11 );
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

	public static class StateAssignmentContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(NuSMVOutputGrammarParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(NuSMVOutputGrammarParser.INT, i);
		}
		public StartLoopContext startLoop() {
			return getRuleContext(StartLoopContext.class,0);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public StateAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterStateAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitStateAssignment(this);
		}
	}

	public final StateAssignmentContext stateAssignment() throws RecognitionException {
		StateAssignmentContext _localctx = new StateAssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stateAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(39);
				startLoop();
				}
			}

			setState(42);
			match(T__7);
			setState(43);
			match(T__8);
			setState(44);
			match(INT);
			setState(45);
			match(T__9);
			setState(46);
			match(INT);
			setState(47);
			match(T__10);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(48);
				assignment();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class StartLoopContext extends ParserRuleContext {
		public StartLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterStartLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitStartLoop(this);
		}
	}

	public final StartLoopContext startLoop() throws RecognitionException {
		StartLoopContext _localctx = new StartLoopContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_startLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__11);
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

	public static class AssignmentContext extends ParserRuleContext {
		public BooleanAssignmentContext booleanAssignment() {
			return getRuleContext(BooleanAssignmentContext.class,0);
		}
		public EnumAssignmentContext enumAssignment() {
			return getRuleContext(EnumAssignmentContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignment);
		try {
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				booleanAssignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				enumAssignment();
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

	public static class BooleanAssignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NuSMVOutputGrammarParser.ID, 0); }
		public TerminalNode TRUE() { return getToken(NuSMVOutputGrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(NuSMVOutputGrammarParser.FALSE, 0); }
		public BooleanAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterBooleanAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitBooleanAssignment(this);
		}
	}

	public final BooleanAssignmentContext booleanAssignment() throws RecognitionException {
		BooleanAssignmentContext _localctx = new BooleanAssignmentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_booleanAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ID);
			setState(61);
			match(T__12);
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
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

	public static class EnumAssignmentContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NuSMVOutputGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NuSMVOutputGrammarParser.ID, i);
		}
		public EnumAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterEnumAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitEnumAssignment(this);
		}
	}

	public final EnumAssignmentContext enumAssignment() throws RecognitionException {
		EnumAssignmentContext _localctx = new EnumAssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_enumAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(ID);
			setState(65);
			match(T__12);
			setState(66);
			match(ID);
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

	public static class FormulaContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(NuSMVOutputGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(NuSMVOutputGrammarParser.ID, i);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NuSMVOutputGrammarListener ) ((NuSMVOutputGrammarListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_formula);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) {
				{
				{
				setState(68);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36M\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\3\3\3\3\3\3\3\3\3\5\3 \n\3\3\4\3\4\3\4\3\4\6\4"+
		"&\n\4\r\4\16\4\'\3\5\5\5+\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\64\n\5\f"+
		"\5\16\5\67\13\5\3\6\3\6\3\7\3\7\5\7=\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\n\7\nH\n\n\f\n\16\nK\13\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\5\3\2"+
		"\5\6\3\2\32\33\5\2\n\n\20\30\35\35\2J\2\27\3\2\2\2\4\32\3\2\2\2\6!\3\2"+
		"\2\2\b*\3\2\2\2\n8\3\2\2\2\f<\3\2\2\2\16>\3\2\2\2\20B\3\2\2\2\22I\3\2"+
		"\2\2\24\26\5\4\3\2\25\24\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2"+
		"\2\2\30\3\3\2\2\2\31\27\3\2\2\2\32\33\7\3\2\2\33\34\5\22\n\2\34\35\7\4"+
		"\2\2\35\37\t\2\2\2\36 \5\6\4\2\37\36\3\2\2\2\37 \3\2\2\2 \5\3\2\2\2!\""+
		"\7\7\2\2\"#\7\b\2\2#%\7\t\2\2$&\5\b\5\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2"+
		"\2\'(\3\2\2\2(\7\3\2\2\2)+\5\n\6\2*)\3\2\2\2*+\3\2\2\2+,\3\2\2\2,-\7\n"+
		"\2\2-.\7\13\2\2./\7\34\2\2/\60\7\f\2\2\60\61\7\34\2\2\61\65\7\r\2\2\62"+
		"\64\5\f\7\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66"+
		"\t\3\2\2\2\67\65\3\2\2\289\7\16\2\29\13\3\2\2\2:=\5\16\b\2;=\5\20\t\2"+
		"<:\3\2\2\2<;\3\2\2\2=\r\3\2\2\2>?\7\35\2\2?@\7\17\2\2@A\t\3\2\2A\17\3"+
		"\2\2\2BC\7\35\2\2CD\7\17\2\2DE\7\35\2\2E\21\3\2\2\2FH\t\4\2\2GF\3\2\2"+
		"\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\23\3\2\2\2KI\3\2\2\2\t\27\37\'*\65<"+
		"I";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}