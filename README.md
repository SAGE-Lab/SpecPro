
# SpecPro

SpecPro is a rework and extension of [snl2fl](https://github.com/SAGE-Lab/snl2fl). 

**N.B.: It is a work in progress, refer to the main repository for usage.**  


## Attribution

   SpecPro is open source software released under the [LGPLv3 license](LICENSE). If you use it, please acknowledge it by citing:

    @inproceedings{narizzano2018consistency,
      title={Consistency of property specification patterns with boolean and constrained numerical signals},
      author={Narizzano, Massimo and Pulina, Luca and Tacchella, Armando and Vuotto, Simone},
      booktitle={NASA Formal Methods Symposium},
      pages={383--398},
      year={2018},
      organization={Springer}
    }

## Build and Run
    
   You can build a new distribution of the software simply running in the project dir the following command:
   
      ./gradlew build
      
   It will automatically build a .zip and a .tar files in the `build/distributions` directory.
   To run SpecPro simply decompress one of the two files and execute the command
   
      ./bin/SpecPro
      
   It will prompt the help message showing the list of available options.
      
   There are many way to run SpecPro and they differ for the kind of output generated.
   There are 4 output provided: 
    
   * SMV with INVAR, generate a NuSMV problem with inequalities managed by INVAR
    
         ./bin/SpecPro --nusmv <inputfile> <outputfile>
    
   * SMV without INVAR generate a NuSMV problem with inequalities written into LTLSPEC 
    
         ./bin/SpecPro --nusmv --noinvar <inputfile> <outputfile> 
    
   * PANDA output, it writes the formula as a LTL, used by PANDA tool  
    
         ./bin/SpecPro --panda <inputfile> <outputfile> 
    
   * AALTA output, it writes the formula as a LTL, used by AALTA tool (add --negated option if you want the negated formula)
    
         ./bin/SpecPro --aalta <inputfile> <outputfile>

   
   Alternatively, you can run the application directly with gradle, substituting `argN` as needed.
   
      ./gradlew run -PappArgs="['arg1', 'args2', 'args3']" 
   
     

## Build the grammars

There are two grammars used in **SpecPro**: 

* [FLGrammar.g4](FLGrammar.g4): defines the formal formulae syntax
* [RequirementsGrammar.g4](RequirementsGrammar.g4): defines the grammar for patterns

The grammars are built using [ANTLR4](http://www.antlr.org/), you need to download and install
it in order to rebuild them. The installation guide is available
[here](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md).
 
Once you have installed ANTLR4, all you have to do is:

1. Open a terminal and move to the it.sagelab.it.sagelab.fe.snl2fl root directory

2. Build FLGrammar:

        antlr4 -o src/main/java/it/sagelab/fe/ltl/parser -package it.sagelab.fe.ltl.parser FLGrammar.g4

3. Build RequirementsGrammar:
        
        antlr4 -o src/main/java/it/sagelab/fe/snl2fl/parser -package it.sagelab.fe.snl2fl.parser RequirementsGrammar.g4

