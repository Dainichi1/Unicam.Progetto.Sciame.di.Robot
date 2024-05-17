package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;


/**
 * Represents a "Done" command for a robot. This command signifies the end of an iteration
 * or a loop and directs the program to go back to a specific instruction row.
 */
public class CommandDone implements RobotInstruction {

    /**
     * The row number to which the instruction pointer should jump back.
     */
    private final int backNumberRow;

    /**
     * Constructs a new CommandDone instance with the specified row number to jump back to.
     *
     * @param irn the row number to which the instruction should jump back.
     */
    public CommandDone(int irn) {
        this.backNumberRow = irn;
    }

    /**
     * Executes the command on the given robot, causing it to continue moving.
     * Note that the primary function of this command is to change the instruction pointer,
     * but the robot will also continue to move.
     *
     * @param robot the robot on which to execute the command.
     */
    @Override
    public void runCommand(Robot robot) {
        robot.continueMove();
    }

    /**
     * Determines the row number to which the instruction pointer should jump back.
     *
     * @return the row number to which to jump back.
     */
    @Override
    public int canGoToNextInstruction() {
        return this.backNumberRow;
    }

    /**
     * Creates and returns a clone of this CommandDone object.
     *
     * @return a clone of this CommandDone object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandDone(this.backNumberRow);
    }
}
