grammar NuSMVOutputGrammar;

@header {
package it.sagelab.specpro.reasoners.parser;
}

output: spec*;

spec: '-- specification' formula  'is' ('true' | 'false') counterexample?;

counterexample: '-- as demonstrated by the following execution sequence'
                'Trace Description: LTL Counterexample'
                'Trace Type: Counterexample' stateAssignment+;

stateAssignment: startLoop? '->' 'State:' INT '.' INT '<-' assignment*;

startLoop: '-- Loop starts here';

assignment: booleanAssignment | enumAssignment;

booleanAssignment: ID ' = ' (TRUE | FALSE);
enumAssignment: ID ' = ' ID;

formula: ('!' | '&' | '|' | '(' | ')' | '->' | '<->' | 'G' | 'F' | 'X' | ID)*;

WS: [ \t\r\n]+ -> skip ;
TRUE: 'TRUE';
FALSE: 'FALSE';
INT: [0-9]+;
ID: [a-zA-Z0-9_]+;

PREAMBLE : '***' .*? '\r'? '\n' -> skip ; // Match single line comments
