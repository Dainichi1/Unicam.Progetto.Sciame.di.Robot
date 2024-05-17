package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;


/**
 * Represents a command to signal a label and continue the robot's current action.
 * This class implements the {@link RobotInstruction} interface.
 */
public class CommandSignal implements RobotInstruction {

    /**
     * The label to signal.
     */
    private final String labelToSignal;

    /**
     * Constructs a new CommandSignal instance with the specified label to signal.
     *
     * @param labelToSignal The label that will be signaled.
     */
    public CommandSignal(String labelToSignal) {
        this.labelToSignal = labelToSignal;
    }

    /**
     * Signals the label and continues the robot's current action.
     *
     * @param robot The robot that will signal the label and continue its current action.
     * @throws IllegalArgumentException if the robot is null.
     */
    @Override
    public void runCommand(Robot robot) {
        if (robot == null) {
            throw new IllegalArgumentException("Robot cannot be null");
        }
        robot.signalLabel(this.labelToSignal);
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
     * Creates a clone of this CommandSignal object.
     *
     * @return A new CommandSignal object with the same label as this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandSignal(this.labelToSignal);
    }
}
