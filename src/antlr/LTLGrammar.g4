grammar LTLGrammar;

@header {
package it.sagelab.specpro.fe.ltl.parser;
}

file: (formula ';'?)+;

formula
        : '(' formula ')'                                               #BracketFormula
        | ('[]' | 'G' | '<>' | 'F' | '!' | '~' | 'o' | 'X') formula     #UnaryOp
        | formula ('&' | '&&' | '^' | 'xor' | '|' | '||' )  formula     #BinaryOp
        | formula ('->' | '<->' | 'U' | 'W') formula                    #BinaryOp
        | (TRUE | FALSE)                                                #Const
        | ATOM                                                          #Atom
        ;

WS: [ \t\r\n]+ -> skip ;
TRUE: 'true';
FALSE: 'false';
ATOM: [a-zA-Z0-9_]+;
LINE_COMMENT : ('#' | '//') .*? '\r'? '\n' -> skip ; // Match single line comments
