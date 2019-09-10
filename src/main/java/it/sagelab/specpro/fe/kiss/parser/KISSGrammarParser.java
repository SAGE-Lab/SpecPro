// Generated from KISSGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.fe.kiss.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KISSGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, WS=21, ID=22;
	public static final int
		RULE_file = 0, RULE_declaration = 1, RULE_number = 2, RULE_transition = 3, 
		RULE_values = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "declaration", "number", "transition", "values"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\n'", "'.inputs'", "'.outputs'", "'.i'", "'.o'", "'.p'", "'.s'", 
			"'.r'", "'0'", "'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", 
			"'9'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "WS", "ID"
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
	public String getGrammarFileName() { return "KISSGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KISSGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TransitionContext> transition() {
			return getRuleContexts(TransitionContext.class);
		}
		public TransitionContext transition(int i) {
			return getRuleContext(TransitionContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__19))) != 0)) {
				{
				{
				setState(12);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
				case T__2:
				case T__3:
				case T__4:
				case T__5:
				case T__6:
				case T__7:
					{
					setState(10);
					declaration();
					}
					break;
				case T__8:
				case T__9:
				case T__19:
					{
					setState(11);
					transition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(14);
				match(T__0);
				}
				}
				setState(20);
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

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OutputsDeclarationContext extends DeclarationContext {
		public List<TerminalNode> ID() { return getTokens(KISSGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(KISSGrammarParser.ID, i);
		}
		public OutputsDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterOutputsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitOutputsDeclaration(this);
		}
	}
	public static class NOutputsDeclarationContext extends DeclarationContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NOutputsDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterNOutputsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitNOutputsDeclaration(this);
		}
	}
	public static class InputsDeclarationContext extends DeclarationContext {
		public List<TerminalNode> ID() { return getTokens(KISSGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(KISSGrammarParser.ID, i);
		}
		public InputsDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterInputsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitInputsDeclaration(this);
		}
	}
	public static class ResetStateDeclarationContext extends DeclarationContext {
		public TerminalNode ID() { return getToken(KISSGrammarParser.ID, 0); }
		public ResetStateDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterResetStateDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitResetStateDeclaration(this);
		}
	}
	public static class NInputsDeclarationContext extends DeclarationContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NInputsDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterNInputsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitNInputsDeclaration(this);
		}
	}
	public static class NStatesDeclarationContext extends DeclarationContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NStatesDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterNStatesDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitNStatesDeclaration(this);
		}
	}
	public static class NTransitionsDeclarationContext extends DeclarationContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NTransitionsDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterNTransitionsDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitNTransitionsDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				_localctx = new InputsDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				match(T__1);
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(22);
					match(ID);
					}
					}
					setState(25); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
				break;
			case T__2:
				_localctx = new OutputsDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(T__2);
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(28);
					match(ID);
					}
					}
					setState(31); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
				break;
			case T__3:
				_localctx = new NInputsDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				match(T__3);
				setState(34);
				number();
				}
				break;
			case T__4:
				_localctx = new NOutputsDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(35);
				match(T__4);
				setState(36);
				number();
				}
				break;
			case T__5:
				_localctx = new NTransitionsDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(37);
				match(T__5);
				setState(38);
				number();
				}
				break;
			case T__6:
				_localctx = new NStatesDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(39);
				match(T__6);
				setState(40);
				number();
				}
				break;
			case T__7:
				_localctx = new ResetStateDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(41);
				match(T__7);
				setState(42);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17))) != 0) );
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

	public static class TransitionContext extends ParserRuleContext {
		public List<ValuesContext> values() {
			return getRuleContexts(ValuesContext.class);
		}
		public ValuesContext values(int i) {
			return getRuleContext(ValuesContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(KISSGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(KISSGrammarParser.ID, i);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitTransition(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_transition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			values();
			setState(51);
			match(ID);
			setState(52);
			match(ID);
			setState(53);
			values();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(54);
				match(T__18);
				setState(55);
				values();
				}
				}
				setState(60);
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

	public static class ValuesContext extends ParserRuleContext {
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).enterValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KISSGrammarListener ) ((KISSGrammarListener)listener).exitValues(this);
		}
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__19))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__19))) != 0) );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30E\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\5\2\17\n\2\3\2\3\2\7\2\23\n\2\f\2\16"+
		"\2\26\13\2\3\3\3\3\6\3\32\n\3\r\3\16\3\33\3\3\3\3\6\3 \n\3\r\3\16\3!\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\4\6\4\61\n\4\r\4\16"+
		"\4\62\3\5\3\5\3\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\3\6\6\6A\n\6\r\6"+
		"\16\6B\3\6\2\2\7\2\4\6\b\n\2\4\3\2\13\24\4\2\13\f\26\26\2L\2\24\3\2\2"+
		"\2\4-\3\2\2\2\6\60\3\2\2\2\b\64\3\2\2\2\n@\3\2\2\2\f\17\5\4\3\2\r\17\5"+
		"\b\5\2\16\f\3\2\2\2\16\r\3\2\2\2\17\20\3\2\2\2\20\21\7\3\2\2\21\23\3\2"+
		"\2\2\22\16\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\3\3\2"+
		"\2\2\26\24\3\2\2\2\27\31\7\4\2\2\30\32\7\30\2\2\31\30\3\2\2\2\32\33\3"+
		"\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34.\3\2\2\2\35\37\7\5\2\2\36 \7\30"+
		"\2\2\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\".\3\2\2\2#$\7\6\2"+
		"\2$.\5\6\4\2%&\7\7\2\2&.\5\6\4\2\'(\7\b\2\2(.\5\6\4\2)*\7\t\2\2*.\5\6"+
		"\4\2+,\7\n\2\2,.\7\30\2\2-\27\3\2\2\2-\35\3\2\2\2-#\3\2\2\2-%\3\2\2\2"+
		"-\'\3\2\2\2-)\3\2\2\2-+\3\2\2\2.\5\3\2\2\2/\61\t\2\2\2\60/\3\2\2\2\61"+
		"\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2\2\64\65\5\n\6\2\65"+
		"\66\7\30\2\2\66\67\7\30\2\2\67<\5\n\6\289\7\25\2\29;\5\n\6\2:8\3\2\2\2"+
		";>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\t\3\2\2\2><\3\2\2\2?A\t\3\2\2@?\3\2\2"+
		"\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13\3\2\2\2\n\16\24\33!-\62<B";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}