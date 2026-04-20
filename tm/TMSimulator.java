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

public class TMSimulator {
    public static void main(String[] args) {
        //TODO:
    }

    /** Set of states */
    private Object q;
    /** Set of input alphabet */
    private Object sigma;
    /** Transitions */
    private Object delta;
    /** Set of tape alphabet */
    private Object gamma;
    /** Starting state */
    private Object q0;
    /** End state */
    private Object qh;

    public TMSimulator() {
        this.q = new Object();
        this.sigma = new Object();
        this.delta = new Object();
        this.gamma = new Object();

    }

    public void loadMachine() {
        //TODO
        //read line 1: number of states
        //read line 2: number of symbols
        // read other gamma * (Q - 1), or (sigma + 1) * (Q - 1), lines, call addTransitions
    }

    public void addState(int states) {
        //TODO
        // do for loop for number states starting from 0 to n - 1, add to set
    }

    public void addTransition() {
        //TODO
    }

    public void addSigma(int symbols) {
        //TODO
        // do for loop for number of symbols starting from 1 to n, add to set
    }

    public void addGamma() {
        //TODO
        // add 0 to set for blank
        // call addSigma to add remaining to set
    }

    @Override
    public String toString() {
        //TODO
    }
}
