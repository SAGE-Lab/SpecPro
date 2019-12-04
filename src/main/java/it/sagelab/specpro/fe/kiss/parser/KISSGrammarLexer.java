// Generated from KISSGrammar.g4 by ANTLR 4.7.2

package it.sagelab.specpro.fe.kiss.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KISSGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, WS=21, ID=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "WS", "ID", "DIGIT", "LETTER"
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


	public KISSGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "KISSGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u0084\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\6\26o\n\26\r\26\16"+
		"\26p\3\26\3\26\3\27\3\27\5\27w\n\27\3\27\3\27\3\27\7\27|\n\27\f\27\16"+
		"\27\177\13\27\3\30\3\30\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\2\61\2\3\2\5\5\2\13\f\17\17\"\"\3\2\62;\4\2C\\c|\2\u0086\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3\63\3\2\2\2\5\65"+
		"\3\2\2\2\7=\3\2\2\2\tF\3\2\2\2\13I\3\2\2\2\rL\3\2\2\2\17O\3\2\2\2\21R"+
		"\3\2\2\2\23U\3\2\2\2\25W\3\2\2\2\27Y\3\2\2\2\31[\3\2\2\2\33]\3\2\2\2\35"+
		"_\3\2\2\2\37a\3\2\2\2!c\3\2\2\2#e\3\2\2\2%g\3\2\2\2\'i\3\2\2\2)k\3\2\2"+
		"\2+n\3\2\2\2-v\3\2\2\2/\u0080\3\2\2\2\61\u0082\3\2\2\2\63\64\7\f\2\2\64"+
		"\4\3\2\2\2\65\66\7\60\2\2\66\67\7k\2\2\678\7p\2\289\7r\2\29:\7w\2\2:;"+
		"\7v\2\2;<\7u\2\2<\6\3\2\2\2=>\7\60\2\2>?\7q\2\2?@\7w\2\2@A\7v\2\2AB\7"+
		"r\2\2BC\7w\2\2CD\7v\2\2DE\7u\2\2E\b\3\2\2\2FG\7\60\2\2GH\7k\2\2H\n\3\2"+
		"\2\2IJ\7\60\2\2JK\7q\2\2K\f\3\2\2\2LM\7\60\2\2MN\7r\2\2N\16\3\2\2\2OP"+
		"\7\60\2\2PQ\7u\2\2Q\20\3\2\2\2RS\7\60\2\2ST\7t\2\2T\22\3\2\2\2UV\7\62"+
		"\2\2V\24\3\2\2\2WX\7\63\2\2X\26\3\2\2\2YZ\7\64\2\2Z\30\3\2\2\2[\\\7\65"+
		"\2\2\\\32\3\2\2\2]^\7\66\2\2^\34\3\2\2\2_`\7\67\2\2`\36\3\2\2\2ab\78\2"+
		"\2b \3\2\2\2cd\79\2\2d\"\3\2\2\2ef\7:\2\2f$\3\2\2\2gh\7;\2\2h&\3\2\2\2"+
		"ij\7-\2\2j(\3\2\2\2kl\7/\2\2l*\3\2\2\2mo\t\2\2\2nm\3\2\2\2op\3\2\2\2p"+
		"n\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\26\2\2s,\3\2\2\2tw\5\61\31\2uw\7a\2"+
		"\2vt\3\2\2\2vu\3\2\2\2w}\3\2\2\2x|\5\61\31\2y|\5/\30\2z|\7a\2\2{x\3\2"+
		"\2\2{y\3\2\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~.\3\2\2\2\177"+
		"}\3\2\2\2\u0080\u0081\t\3\2\2\u0081\60\3\2\2\2\u0082\u0083\t\4\2\2\u0083"+
		"\62\3\2\2\2\7\2pv{}\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}