
# snl2fl

## Introduction
   **snl2fl** stands for Structured Natural Language To Formal Language.
   It is a transalator from a structured language (Patterns [1][2][3]) to many formal languages, 
   such as LTL (Linear Temporal Logic).
   
   It is used to formally verify the consistency of a set of requirements written in a human readable format.

## Reference

    @article{narizzano2017consistency,
      title={Consistency of Property Specification Patterns with Boolean and Constrained Numerical Signals},
      author={Narizzano, Massimo and Pulina, Luca and Tacchella, Armando and Vuotto, Simone},
      journal={arXiv preprint arXiv:1712.04162},
      year={2017}
    }

## Build and Run
    
   You can build a new distribution of the software simply running in the project dir the following command:
   
      ./gradlew build
      
   It will automatically build a .zip and a .tar files in the `build/distribution` directory.
   To run snl2fl simply decompress one of the two files and execute the command
   
      ./bin/snl2fl
      
   It will prompt the help message showing the list of available options.
      
   There are many way to run snl2fl and they differ for the kind of otuput generated.
   There are 4 output provided: 
    
   * SMV with INVAR, generate a NuSMV problem with inequalities managed by INVAR
    
         ./bin/snl2fl --nusmv <inputfile> <outputfile>
    
   * SMV without INVAR generate a NuSMV problem with inequalities written into LTLSPEC 
    
         ./bin/snl2fl --nusmv --noinvar <inputfile> <outputfile> 
    
   * PANDA output, it writes the formula as a LTL, used by PANDA tool  
    
         ./bin/snl2fl --panda <inputfile> <outputfile> 
    
   * AALTA output, it writes the formula as a LTL, used by AALTA tool (add --negated option if you want the negated formula)
    
         ./bin/snl2fl --aalta <inputfile> <outputfile>

   
   Alternatively, you can run the application directly with gradle, substituting `argN` as needed.
   
      ./gradlew run -PappArgs="['arg1', 'args2', 'args3']" 
   
     

## Build the grammars

There are two grammars used in **snl2fl**: 

* [FLGrammar.g4](FLGrammar.g4): defines the formal formulae syntax
* [RequirementsGrammar.g4](RequirementsGrammar.g4): defines the grammar for patterns

The grammars are built using ANTLR4 [4], you need to download and install
it in order to rebuild them. The installation guide is available
[here](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md).
 
Once you have installed ANTLR4, all you have to do is:

1. Open a terminal and move to the snl2fl root directory

2. Build FLGrammar:

        antlr4 -o src/main/java/snl2fl/fl/parser -package snl2fl.fl.parser FLGrammar.g4

3. Build RequirementsGrammar:
        
        antlr4 -o src/main/java/snl2fl/req/parser -package snl2fl.req.parser RequirementsGrammar.g4


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