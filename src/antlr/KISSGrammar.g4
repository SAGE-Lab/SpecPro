grammar KISSGrammar;

@header {
package it.sagelab.specpro.fe.kiss.parser;
}

file: ((declaration | transition) '\n')*;

declaration
    : '.inputs'  ID+        #InputsDeclaration
    | '.outputs' ID+        #OutputsDeclaration
    | '.i'       number     #NInputsDeclaration
    | '.o'       number     #NOutputsDeclaration
    | '.p'       number     #NTransitionsDeclaration
    | '.s'       number     #NStatesDeclaration
    | '.r'       ID         #ResetStateDeclaration
    ;

number: ('0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9')+;
transition: values ID ID values ('+' values)*;
values: ('0' | '1' | '-')+;


WS: [ \t\r\n]+ -> skip;
ID: (LETTER | '_') (LETTER | DIGIT | '_')*;

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
