package it.unicam.cs.MarcoTorquati.api.command;


/**
 * Represents an abstract class for iterative robot commands. These commands typically involve
 * repeating a set of instructions from a starting row to an ending row in the instruction set.
 */
public abstract class CommandIteretive implements RobotInstruction {

    /**
     * The index of the first row in the instruction set where the iteration starts.
     */
    protected final int firstRowIteration;

    /**
     * The index of the last row in the instruction set where the iteration ends.
     */
    protected int endOfIteration;

    /**
     * Constructs a new CommandIteretive instance with the specified starting row for the iteration.
     *
     * @param firstRowIteration The index of the first row where the iteration starts.
     */
    protected CommandIteretive(int firstRowIteration) {
        this.firstRowIteration = firstRowIteration;
    }

    /**
     * Sets the index of the last row where the iteration should end.
     *
     * @param index The index of the last row for the iteration.
     */
    public void setEndOfIteration(int index) {
        this.endOfIteration = index;
    }
}
