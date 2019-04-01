# LeetCode
This project contains all source files of the questions I solved on LeetCode.
All the source files are contained in the src folder.

All others files and folders are auxiliary.

One can clone this repository to his own disk. 
Some compile errors may occur at the first run. 
They should be caused by the class dependency.
To solve this, one can run the ```ManCompile.bat``` at its position. 
It also gives a feasible compile order.

## How to use/read these codes?

Unfortunately, these codes are organized for the convenience of writing them, but not using or reading them.
But these words can help.

Like lc101_110/LC101_110.java,
it should contain all solutions to questions from 101 to 110.
For each question, I may give more than one solutions, specified as  
class  Solution101, Solution101_2, Solution101_3 and so on.

Some of them may be wrong. 
And I will point it out before the class definition in the source files.
For the accepted solution, 
before the class definition are time and space performance.

Of course these information can be placed in a better place.
But for my first try on Leet Code, 
I do all things as simple as the way can be. 

## Contents:

### src
It contains all packages and source files (.java files).

### input
It contains input text filess.

### output:
It contains output text files.

Input and output folders are used to test and debug.

### ManCompile.bat 
It can be used to compile all java files in Windows systems. 
One can slightly modify it to select which files to compile.

### ManRun.bat 
Run selected .class file in cmd.

### bin 
This is the output path. It contains all output .class file. 
And I set git to not track all .class files. 
After running ManCompile.bat, one will see this folder.
