package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;

/**
 * Represents a command to move a robot in a given direction at a specified speed.
 */
public class CommandMove implements RobotInstruction {

    /**
     * The direction in which the robot should move.
     */
    private final Direction direction;

    /**
     * The speed at which the robot should move.
     */
    private final double speed;

    /**
     * Constructs a new CommandMove instance with the specified direction and speed.
     *
     * @param dir The direction in which the robot should move.
     * @param speed The speed at which the robot should move.
     */
    public CommandMove(Direction dir, double speed){
        this.direction = dir;
        this.speed = speed;
    }

    /**
     * Executes the move command, moving the robot in the specified direction at the specified speed.
     *
     * @param robot The robot to move.
     */
    @Override
    public void runCommand(Robot robot) {
        robot.move(this.speed, this.direction);

    }

    /**
     * Indicates that the instruction has been executed and the robot can proceed to the next instruction.
     *
     * @return -1, as the robot can proceed immediately to the next instruction.
     */
    @Override
    public int canGoToNextInstruction() {
        return -1;
    }

    /**
     * Creates a clone of this CommandMove object.
     *
     * @return A new CommandMove object with the same direction and speed as this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandMove(new Direction(this.direction.getX(), this.direction.getY()), this.speed);
    }

}
