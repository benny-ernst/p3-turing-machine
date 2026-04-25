//4 (total number of states)
//1 (number of symbols)
//1,1,R (next state, write symbol, move)        0
//2,1,L                                         0
//0,1,L                                         1
//1,1,R                                         1
//1,1,L                                         2
//3,1,R                                         2

//need println and tostring for outputting result
// use parameter where java TMSimulator {file0.txt}
package tm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * Simulates a Turing Machine
 * @author Benny Ernst, Holden Weber
 */
public class TMSimulator {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            printUsage();
            return;
        }

        TMSimulator simulator = new TMSimulator();
        simulator.loadMachine(args[0]);
        simulator.run();
        System.out.println(simulator);
    }

    /**
     * prints usage message
     */
    private static void printUsage() {
        System.out.println("Usage: java TMSimulator <input_file>");
    }

    /** Number of states */
    private int numStates;
    /** Number of symbols */
    private int numSymbols;
    /** Transitions */
    private Transition[][] transitions;
    /** Symbols on the tape  */
    private Map<Long, Integer> tape = new HashMap<>();
    /** Position of the tape head */
    private long headPos = 0;
    /** Current state the TM is in */
    private int currState = 0;
    /** Tracker for farmost left position on the tape */
    private long minVisited = 0;
    /** Tracker for farmost right position on the tape */
    private long maxVisited = 0;

    /**
     * loads the Turing Machine from given input file
     * @param filename - input file
     * @throws FileNotFoundException - if file is not found
     */
    public void loadMachine(String filename) throws FileNotFoundException {
        File file = new File(filename);
        String line;
        try (Scanner scr = new Scanner(file)){
            this.numStates = Integer.parseInt(scr.nextLine().trim());
            this.numSymbols = Integer.parseInt(scr.nextLine().trim());
            this.transitions = new Transition[numStates - 1][numSymbols + 1];

            for (int i = 0; i < numStates - 1; i++) {
                for (int j = 0; j < numSymbols + 1; j++) {
                    line = scr.nextLine();
                    line = line.trim();
                    String[] tokens = line.split(",");

                    int nextState = Integer.parseInt(tokens[0]);
                    int write = Integer.parseInt(tokens[1]);
                    char move = tokens[2].charAt(0);


                    this.transitions[i][j] = new Transition(nextState, write, move);
                }
            }

            if (scr.hasNextLine()) {
                line = scr.nextLine();
                if (line != null && !line.trim().isEmpty()) {
                    String input = line.trim();
                    for (int i = 0; i < input.length(); i++) {
                        int val = Character.getNumericValue(input.charAt(i));
                        tape.put((long)i, val);
                        maxVisited = Math.max(maxVisited, i);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Runs the simulated Turing Machine
     */
    public void run() {
        int acceptedState = numStates - 1;

        while (currState != acceptedState) {

            int currSym = tape.getOrDefault(headPos, 0);
            Transition t = transitions[currState][currSym];

            tape.put(headPos, t.write);
            currState = t.nextState;
            if (t.move == 'R') {
                headPos++;
            } else if (t.move == 'L') {
                headPos--;
            } else {
                throw new IllegalStateException("Invalid move '" + t.move + "'");
            }

            minVisited = Math.min(minVisited, headPos);
            maxVisited = Math.max(maxVisited, headPos);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (long i = minVisited; i <= maxVisited; i++) {
            sb.append(tape.get(i));
        }
        return sb.toString();
    }
}
