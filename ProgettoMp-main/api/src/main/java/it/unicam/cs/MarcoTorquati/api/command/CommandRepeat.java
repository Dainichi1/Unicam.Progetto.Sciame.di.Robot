package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;


/**
 * Represents a command to repeatedly execute a block of instructions for a given number of iterations.
 * This class extends {@link CommandIteretive}.
 */
public class CommandRepeat extends CommandIteretive {

    /**
     * The total number of iterations to execute the block of instructions.
     */
    private final int iterations;

    /**
     * The number of iterations that have been completed.
     */
    private int doneIterations;

    /**
     * Constructs a new CommandRepeat instance with the specified number of iterations and the first row of iteration.
     *
     * @param iterations The total number of iterations to execute the block of instructions.
     * @param firstRowIteration The index of the first instruction within the block to iterate.
     */
    public CommandRepeat(int iterations, int firstRowIteration) {
        super(firstRowIteration);
        this.iterations = iterations;
        this.doneIterations = 0;
    }

    /**
     * Executes one iteration of the block of instructions and increments the count of completed iterations.
     *
     * @param robot The robot that will execute the block of instructions.
     */
    @Override
    public void runCommand(Robot robot) {
        this.doneIterations += 1;
        robot.continueMove();

    }

    /**
     * Determines whether the robot can proceed to the next instruction or should repeat the current block.
     *
     * @return The index of the first instruction within the block if more iterations are needed, or -1 otherwise.
     */
    @Override
    public int canGoToNextInstruction() {
        if (this.doneIterations < this.iterations) {
            return this.firstRowIteration;
        } else {
            return -1;
        }
    }

    /**
     * Creates a clone of this CommandRepeat object.
     *
     * @return A new CommandRepeat object with the same number of iterations and starting index as this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        CommandRepeat ri = new CommandRepeat(this.iterations, this.firstRowIteration);
        ri.endOfIteration = this.endOfIteration;
        return ri;
    }
}
