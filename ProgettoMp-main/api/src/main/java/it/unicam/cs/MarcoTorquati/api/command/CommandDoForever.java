package it.unicam.cs.MarcoTorquati.api.command;


import it.unicam.cs.MarcoTorquati.api.models.*;

/**
 * Represents a "Do Forever" command for a robot. This command will continue to execute
 * indefinitely, causing the robot to move continuously.
 */
public class CommandDoForever extends CommandIteretive {

    /**
     * Constructs a new CommandDoForever instance with the specified row number for the first iteration.
     *
     * @param firstRowIteration the row number where the iteration starts in the list of instructions.
     */
    public CommandDoForever(int firstRowIteration) {

        super(firstRowIteration);
    }

    /**
     * Executes the command on the given robot, causing it to continue moving.
     *
     * @param robot the robot on which to execute the command.
     */
    @Override
    public void runCommand(Robot robot) {

        robot.continueMove();
    }

    /**
     * Determines whether the robot can proceed to the next instruction.
     * Since this is a "Do Forever" command, it always returns the first row of the iteration.
     *
     * @return the row number of the first instruction in the iteration.
     */
    @Override
    public int canGoToNextInstruction() {
        return firstRowIteration;
    }

    /**
     * Creates and returns a clone of this CommandDoForever object.
     *
     * @return a clone of this CommandDoForever object.
     */
    @Override
    public RobotInstruction cloneObject() {
        CommandDoForever dfi = new CommandDoForever(this.firstRowIteration);
        dfi.endOfIteration = this.endOfIteration;
        return dfi;
    }


}
