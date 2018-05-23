grammar RequirementsGrammar;

file: requirement+ ;

requirement: reqID? (scope ',')? specification '.' ;
scope: 'Globally' | 'Before' expr | 'After' expr | 'Between' expr 'and' expr | 'After' expr 'until' expr ;

reqID: '[' REQ_LABEL? rId ']';
rId: ID | GENERIC_ID | POSITIVE_INT;

specification: qualitative | realtime;

qualitative: absence | universality | existence | boundedExistence | precedence | precedenceChain12 | precedenceChain21 |
            response | responseChain12 | responseChain21 | constrainedChain12 | invariant;
absence: never expr 'holds' ;
universality: always expr 'holds' ;
existence: expr 'eventually' 'holds' ;
boundedExistence: 'transitions' 'to' 'states' 'in' 'which' expr 'holds' 'occur' 'at' 'most' positiveInt 'times' ;
precedence: always 'if' expr 'holds' ',' 'then' expr 'previously' 'held' ;
precedenceChain12: always 'if' expr 'holds' 'and' 'is' 'succeeded' 'by' expr ',' 'then' expr 'previously' 'held' ;
precedenceChain21: always 'if' expr 'holds' ',' 'then' expr 'previously' 'held' 'and' 'was' 'preceded' 'by' expr ;
response: always 'if' expr 'holds' ',' 'then' expr 'eventually' 'holds' ;
constrainedChain12: always 'if' expr 'holds' ',' 'then' expr 'eventually' 'holds' 'and' 'is' 'succeeded' 'by' expr ',' 'where' expr 'does' 'not' 'hold' 'between' expr 'and' expr ;
responseChain21: always 'if' expr 'holds' 'and' 'is' 'succeeded' 'by' expr ',' 'then' expr 'eventually' 'holds' 'after' expr ;
responseChain12:    always 'if' expr 'holds' ',' 'then' expr 'eventually' 'holds' 'and' 'is' 'succeeded' 'by' expr ;

realtime: minDuration | maxDuration | boundedRecurrence | boundedResponse | boundedInvariance ;
minDuration: always 'once' expr 'becomes' 'satisfied' ',' 'it' 'holds' 'for' 'at' 'least' time ;
maxDuration: always 'once' expr  'becomes' 'satisfied' ',' 'it' 'holds' 'for' 'at' 'most' time ;
boundedRecurrence: always expr 'holds' 'at' 'least' 'every' time ;
boundedResponse: always 'if' expr 'holds' ',' 'then' expr 'holds' 'after' 'at' 'most' time ;
boundedInvariance: always 'if' expr 'holds' ',' 'then' expr 'holds' 'for' 'at' 'least' time ;

invariant: always 'if' expr 'holds' ',' 'then' expr 'holds' 'as' 'well' ;

expr
    : '(' expr ')'                                                          #BracketExpression
    | (ID | number) ( '=' | '>' | '>=' | '<' | '<=' | '!=') (ID | number)   #CompareExpression
    | expr ('and' | 'or' | 'xor') expr                                      #BooleanExpression
    | 'not' expr                                                            #UnaryExpression
    | ID                                                                    #IDExpression
    ;

time : number TIME_UNIT;
always: ('it' | 'It') 'is' 'always' 'the' 'case' 'that' ;
never: ('it' | 'It') 'is' 'never' 'the' 'case' 'that' ;
number: POSITIVE_INT | FLOAT;
positiveInt: POSITIVE_INT;

POSITIVE_INT: DIGIT | NONZERODIGIT (DIGIT)*;
FLOAT: '-'? ('.' DIGIT+ | DIGIT+ ('.' DIGIT*)? );
TIME_UNIT: ('s' | 'm' | 'h');
ID: LETTER (LETTER | DIGIT | '_')*;
GENERIC_ID: (LETTER | DIGIT | '_')+;
REQ_LABEL: ('REQ' | [Rr] 'eq')? [Ii][Dd] '=';

WS: [ \t\r\n]+ -> skip ;
LINE_COMMENT : '#' .*? '\r'? '\n' -> skip ; // Match "#" stuff '\n'



fragment NONZERODIGIT: [1-9];
fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
