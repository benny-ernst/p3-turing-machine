****************
* Project 3 Turing Machine
* CS 361 Sec 001
* 24 April 2025
* Benny Ernst and Holden Weber
****************
## OVERVIEW:

This program simulates a Turing Machine based on input from a
test .txt file. 

## INCLUDED FILES:

* TMSimulator.java - Simulates Turing Machine
* Transition.java - Stores next state, write, and move in transition object
* README - this file

  
## COMPILING AND RUNNING:

From the directory containing all source files (run from /p3-turing-machine), compile the
driver class (and all dependencies) with the command:

```
$ javac tm/*.java
```

Run the compiled class file with the command:

```
$ java tm.TMSimulator <filename>
```

Be sure to include the location of the designated filename you want to run. For example, to run test2.txt from the test folder, simply run:

```
$ java tm.TMSimulator test/file2.txt
```

Console output will give the results after the program finishes.

## PROGRAM DESIGN AND IMPORTANT CONCEPTS:

We approached our Turing Machine simulation from an iterative perspective.
We chose to read the input files line-by-line and used the split method to 
delimit the transitions by comma. We also chose to write a loadMachine method 
instead of a constructor because the values of the Turing Machine are given 
from the test txt input files; not from tests like JUNIT. The loadMachine is 
basically a constructor in its own right; initializing the instance variables
based on the provided input. 

We included the Transition class to hold the 3 values of a transition because 
we did not want to create a nested map instance variable in the TMSimulator 
class. We made the instance variables public but final, so they are immutable. 

We think that this program could be improved with better applied iteration 
techniques to the TMSimulator. 

## TESTING:

We performed a ton of smoke testing, testing many invalid inputs and ensuring that the load function properly stores the correct values. The program handles bad input well. It will throw errors if there are too many or not enough transition entries based off the given number of states and symbols. The system can also detect if a symbol or state should not exist as well as catching bad movement (anything but 'R' or 'L').

## DISCUSSION:

We had some trouble thinking conceptually how to represent something infinite on a computer since computers obviously cannot be infinite. We thought of using a set at first, but quickly realized that if we needed to move the tape left past the start position, we would end up with negative indexes, which simply wasn't possible. We landed on using a map to represent an address (long), which supported negative "indexes", mapped to its value. 

Since we had to print the output, we had to keep track of where we had visited to produce the output when traversing the machine. The machine at the time only knew about the cells it had written/read from. We decided to use minVisited and maxVisited to represent the leftmost and rightmost index in the tape that was visited during simulation. This created some problems as we had to really think about where to create these checks to decrement/increment minVisited and maxVisited. This created some mistakes that were hard to track since we weren't sure if it was a problem with the loadMachine() method or something deeper. We eventually narrowed it down, finding that we were missing an essential computation in the run() method to get the Math.min/Math.max of the minVisited/maxVisited and headPos during each while loop. 

## EXTRA CREDIT:

This project awards extra credit if our code runs the simulation the
fastest compared to the other teams. That is to be proven on 4/27/26. 

## SOURCES:

N/A
