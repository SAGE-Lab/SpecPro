// Generated from LTLGrammar.g4 by ANTLR 4.7.2
package it.sagelab.specpro.fe.ltl.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LTLGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, WS=22, ATOM=23, LINE_COMMENT=24;
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
			"T__17", "T__18", "T__19", "T__20", "WS", "ATOM", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "'[]'", "'G'", "'<>'", "'F'", "'!'", "'~'", 
			"'o'", "'X'", "'&'", "'&&'", "'^'", "'xor'", "'|'", "'||'", "'->'", "'<->'", 
			"'U'", "'W'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "WS", "ATOM", 
			"LINE_COMMENT"
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


	public LTLGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LTLGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u0080\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\27\6\27h\n\27\r\27\16\27i\3\27\3\27\3\30"+
		"\6\30o\n\30\r\30\16\30p\3\31\3\31\7\31u\n\31\f\31\16\31x\13\31\3\31\5"+
		"\31{\n\31\3\31\3\31\3\31\3\31\3v\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\3\2\4\5\2\13\f\17\17\"\"\5\2\62;C\\c|\2\u0083\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13<\3\2\2\2\r>\3\2"+
		"\2\2\17A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25G\3\2\2\2\27I\3\2\2\2\31K\3"+
		"\2\2\2\33M\3\2\2\2\35P\3\2\2\2\37R\3\2\2\2!V\3\2\2\2#X\3\2\2\2%[\3\2\2"+
		"\2\'^\3\2\2\2)b\3\2\2\2+d\3\2\2\2-g\3\2\2\2/n\3\2\2\2\61r\3\2\2\2\63\64"+
		"\7=\2\2\64\4\3\2\2\2\65\66\7*\2\2\66\6\3\2\2\2\678\7+\2\28\b\3\2\2\29"+
		":\7]\2\2:;\7_\2\2;\n\3\2\2\2<=\7I\2\2=\f\3\2\2\2>?\7>\2\2?@\7@\2\2@\16"+
		"\3\2\2\2AB\7H\2\2B\20\3\2\2\2CD\7#\2\2D\22\3\2\2\2EF\7\u0080\2\2F\24\3"+
		"\2\2\2GH\7q\2\2H\26\3\2\2\2IJ\7Z\2\2J\30\3\2\2\2KL\7(\2\2L\32\3\2\2\2"+
		"MN\7(\2\2NO\7(\2\2O\34\3\2\2\2PQ\7`\2\2Q\36\3\2\2\2RS\7z\2\2ST\7q\2\2"+
		"TU\7t\2\2U \3\2\2\2VW\7~\2\2W\"\3\2\2\2XY\7~\2\2YZ\7~\2\2Z$\3\2\2\2[\\"+
		"\7/\2\2\\]\7@\2\2]&\3\2\2\2^_\7>\2\2_`\7/\2\2`a\7@\2\2a(\3\2\2\2bc\7W"+
		"\2\2c*\3\2\2\2de\7Y\2\2e,\3\2\2\2fh\t\2\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2"+
		"\2\2ij\3\2\2\2jk\3\2\2\2kl\b\27\2\2l.\3\2\2\2mo\t\3\2\2nm\3\2\2\2op\3"+
		"\2\2\2pn\3\2\2\2pq\3\2\2\2q\60\3\2\2\2rv\7%\2\2su\13\2\2\2ts\3\2\2\2u"+
		"x\3\2\2\2vw\3\2\2\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2y{\7\17\2\2zy\3\2\2\2"+
		"z{\3\2\2\2{|\3\2\2\2|}\7\f\2\2}~\3\2\2\2~\177\b\31\2\2\177\62\3\2\2\2"+
		"\7\2ipvz\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}