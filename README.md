
# SpecPro

SpecPro is an open-source Java library for supporting
analysis and development of formal requirements.

Currently SpecPro supports a structured language based on the Property Specification Patterns (PSPs) defined in [1][2].
The language also supports numerical constraints that are encoded in Linear Temporal Logic (LTL). 
An example of accepted input can be found [here](https://github.com/SAGE-Lab/robot-arm-usecase). 

SpecPro is a rework and extension of [snl2fl](https://github.com/SAGE-Lab/snl2fl). 

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

## Build
    
You can build a new distribution of the software simply running in the project dir the following command:
   
    ./gradlew build
      
It will automatically build a .zip and a .tar files in the `build/distributions` directory.
To run SpecPro simply decompress one of the two files and execute the command
   
    ./bin/SpecPro
      
It will prompt the help message showing the list of available options.
   
Alternatively, you can run the application directly with gradle, substituting `argN` as needed.
      
    ./gradlew run -PappArgs="['arg1', 'args2', 'args3']" 
      
## Run
      
There are many way to run SpecPro and they differ for the kind of output generated.

The command line interface currently support the following tasks:

* **translate**: translate the requirement specification into the corresponding satisfiability checking problem.

* **consistency**: translate and perform the Consistency Checking of the input requirement specification. 
                   In order to use this option, the `SPECPRO_NUSMV` environment variable has to be set if NuSMV 
                   is the chosen model checker, and `SPECPRO_AALTA` if Aalta is the choosen one.
                   
* **muc**: perform the Inconsistency Explanation (or equivalently MUC extraction) of the inconsistent specification.
                  `SPECPRO_NUSMV` or `SPECPRO_AALTA` has to be set as in the consistency checking task.
                
* **atg**  _(experimental)_: performs automatic test generation of the given specification. To run, it requires 
                             [Spot](https://spot.lrde.epita.fr/) to be installed.

The full list of options is available with `SpecPro <task> --help` command.
 
An exampple is:
    
    ./bin/SpecPro translate --nusmv -i <inputfile> -o <outputfile>

     

## Build the grammars

There are two grammars used in **SpecPro**: 

* [LTLGrammar.g4](src/antlr/LTLGrammar.g4): defines the formal LTL formulae syntax
* [RequirementsGrammar.g4](src/antlr/RequirementsGrammar.g4): defines the grammar for patterns

The grammars are built using [ANTLR4](http://www.antlr.org/), you need to download and install
it in order to rebuild them. The installation guide is available
[here](https://github.com/antlr/antlr4/blob/master/doc/getting-started.md).
 
Once you have installed ANTLR4, all you have to do is:

1. Open a terminal in the project root directory and move to `src/antlr/`

2. Build LTLGrammar:

        antlr4 -o ../main/java/it/sagelab/specpro/fe/ltl/parser -package it.sagelab.specpro.fe.ltl.parser LTLGrammar.g4

3. Build RequirementsGrammar:
        
        antlr4 -o ../main/java/it/sagelab/specpro/fe/psp/parser -package it.sagelab.specpro.fe.psp.parser RequirementsGrammar.g4

## References

   [1] Dwyer, M.B., Avrunin, G.S., Corbett, J.C.: Patterns in property
   specifications for finite-state verification. In: Software
   Engineering, 1999. Proceedings of the 1999 International Conference
   on, pp. 411–420. IEEE (1999)

   [2] http://ps-patterns.wikidot.com/
