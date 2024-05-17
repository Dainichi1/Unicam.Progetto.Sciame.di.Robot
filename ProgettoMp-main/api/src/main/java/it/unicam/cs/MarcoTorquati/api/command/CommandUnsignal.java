package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;


/**
 * Represents a command to unsignal a label from a robot.
 * This class implements the {@link RobotInstruction} interface.
 */
public class CommandUnsignal implements RobotInstruction {
    private final String labelToUnsignal;

    /**
     * Constructs a new CommandUnsignal object with the specified label to unsignal.
     *
     * @param labelToUnsignal The label that will be unsignaled from the robot.
     */
    public CommandUnsignal(String labelToUnsignal) {
        this.labelToUnsignal = labelToUnsignal;
    }

    /**
     * Executes the unsignal command on the given robot.
     * Removes the specified label from the robot and continues its movement.
     *
     * @param robot The robot on which to execute the unsignal command.
     * @throws IllegalArgumentException If the robot is null or the label cannot be unsignaled.
     */
    @Override
    public void runCommand(Robot robot) throws IllegalArgumentException {
        robot.unsignalLabel(this.labelToUnsignal);
        robot.continueMove();

    }

    /**
     * Determines that the robot can proceed to the next instruction.
     *
     * @return -1, indicating that the robot can proceed to the next instruction.
     */
    @Override
    public int canGoToNextInstruction() {
        return -1;
    }

    /**
     * Creates a clone of this CommandUnsignal object.
     *
     * @return A new CommandUnsignal object, which is a clone of this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandUnsignal(this.labelToUnsignal);
    }

}
