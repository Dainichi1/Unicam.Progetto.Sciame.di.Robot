package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;

/**
 * CommandContinue class implements the RobotInstruction interface to define a "Continue" command for a Robot.
 * The command makes the Robot continue its current action for a given number of seconds.
 */
public class CommandContinue implements RobotInstruction {

    /**
     * The total seconds the command should execute for.
     */
    private final int secondsToExecute;

    /**
     * The number of seconds the command has already been executed.
     */
    private int secondsExecuted;

    /**
     * The row number where the command is located within the program.
     */
    private final int rowNumber;

    /**
     * Constructs a CommandContinue object.
     *
     * @param s  The number of seconds for the command to execute.
     * @param rn The row number where the command is located in the program.
     */
    public CommandContinue(int s, int rn) {
        this.secondsToExecute = s;
        this.secondsExecuted = 0;
        this.rowNumber = rn;
    }

    /**
     * Executes the "continue" instruction on the provided Robot.
     *
     * @param robot The Robot object on which the instruction will be executed.
     */
    @Override
    public void runCommand(Robot robot) {
        robot.continueMove();
        this.secondsExecuted++;

    }

    /**
     * Checks if the Robot can move to the next instruction.
     *
     * @return The row number if the robot should continue executing the current instruction, -1 otherwise.
     */
    @Override
    public int canGoToNextInstruction() {
        return this.secondsExecuted < this.secondsToExecute ? this.rowNumber : -1;
    }

    /**
     * Clones the current CommandContinue object.
     *
     * @return A new CommandContinue object with the same configuration.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandContinue(this.secondsToExecute, this.rowNumber);
    }
}
