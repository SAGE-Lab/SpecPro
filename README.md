
# snl2fl

## Introduction
   This project started the 01/09/2015. snl2fl stands for Structured
   Natural Language To Formal Language, and it is a transalator from a
   structured language (Patterns [1][2][3]) to man formal languages, such
   as LTL (Linear Temporal Logic).

## How to compile
   # COMMAND LINE
   javac -classpath .:src/:lib/*:resources/ src/snl2fl/Main.java -d bin/

   # ECLIPSE
     
## How to run
  There are many way to run snl2fl and they differ for the type of otuput provided.
  There are 4 output provided: 

  # SMV with INVAR, generate a NuSMV problem with inequalities managed by INVAR
    java -classpath .:bin/:lib/*:resources/ snl2fl.Main <inputfile> <outputfile>

  # SMV without INVAR generate a NuSMV problem with inequalities written into LTLSPEC 
    java -classpath .:bin/:lib/*:resources/ snl2fl.Main <inputfile> <outputfile> -noinvar

  # PANDA output, it writes the formula as a LTL, used by PANDA tool  
    java -classpath .:bin/:lib/*:resources/ snl2fl.Main <inputfile> <outputfile> -panda

  # AALTA output, it writes the formula as a LTL, used by AALTA tool  
    java -classpath .:bin/:lib/*:resources/ snl2fl.Main <inputfile> <outputfile> -aalta

### Build the grammars

There are two grammars used in **snl2fl**: 

* [FLGrammar.g4](FLGrammar.g4): defines the formal formulae syntax
* [RequirementsGrammar.g4](RequirementsGrammar.g4): defines the grammar for patterns

The grammars are built using ANTLR4 [4], you need to download and install
it in order to rebuild them. The installation guide is available
[here](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md).
 
Once you have installed ANTLR4, all you have to do is:

1. Open a terminal and move to the snl2fl root directory

2. Build FLGrammar:

        antlr4 -o src/snl2fl/fl/parser -package snl2fl.fl.parser FLGrammar.g4

3. Build RequirementsGrammar:
        
        antlr4 -o src/snl2fl/req/parser -package snl2fl.req.parser RequirementsGrammar.g4

## How to run


## References

   [1] Dwyer, M.B., Avrunin, G.S., Corbett, J.C.: Patterns in property
   specifications for finite-state verification. In: Software
   Engineering, 1999. Proceedings of the 1999 International Conference
   on, pp. 411–420. IEEE (1999)

   [2] http://patterns.projects.cs.ksu.edu/

   [3] Konrad, S., Cheng, B.H.: Real-time specification patterns. In:
   Software engineering, 2005. icse 2005.  proceedings. 27th
   international conference on, pp. 372–381. IEEE (2005)
   
   [4] http://www.antlr.org/