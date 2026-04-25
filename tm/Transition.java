package tm;

/**
 * Transition data type
 * @author Benny Ernst, Holden Weber
 */
public class Transition {
    public final int nextState;
    public final int write;
    public final char move;

    /**
     * initializes a transition object
     * @param nextState - next state
     * @param write - char you are writing to the tape
     * @param move - direction tape head is moving
     */
    public Transition(int nextState, int write, char move) {
        this.nextState = nextState;
        this.write = write;
        this.move = move;
    }
}