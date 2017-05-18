grammar FLGrammar;

fl: formula;

formula
        : '(' formula ')'                               #BracketFormula
        | ('[]' | '<>' | '!' | 'o') formula             #UnaryOp
        | formula ('&' | '^' | '|') formula             #BinaryLogicOp
        | formula ('->' | '<->' | 'U' | 'W') formula    #BinaryOp
        | ATOM                                          #Atom
        ;

WS: [ \t\r\n]+ -> skip ;
ATOM: [a-zA-Z0-9]+;
