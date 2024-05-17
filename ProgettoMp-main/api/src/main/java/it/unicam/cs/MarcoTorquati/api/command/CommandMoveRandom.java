package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;

/**
 * Represents a command to move a robot to a random point within a defined region at a specified speed.
 */
public class CommandMoveRandom implements RobotInstruction {

    /**
     * The first point defining the region within which the robot can move.
     */
    private final Point position1;

    /**
     * The second point defining the region within which the robot can move.
     */
    private final Point position2;

    /**
     * The speed at which the robot should move.
     */
    private final double speed;

    /**
     * Constructs a new CommandMoveRandom instance with the specified points and speed.
     *
     * @param position1 The first point defining the region within which the robot can move.
     * @param position2 The second point defining the region within which the robot can move.
     * @param speed The speed at which the robot should move.
     */
    public CommandMoveRandom(Point position1, Point position2, double speed) {
        this.position1 = position1;
        this.position2 = position2;
        this.speed = speed;
    }

    /**
     * Executes the move command, moving the robot to a random point within the defined region at the specified speed.
     *
     * @param robot The robot to move.
     */
    @Override
    public void runCommand(Robot robot) {
        Point randomPoint = RandomCoordinatesCalculator.calculate(position1, position2);
        Direction dir = DirectionCalculator.calculate(robot.getPosition(), randomPoint);
        robot.move(speed, dir);

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
     * Creates a clone of this CommandMoveRandom object.
     *
     * @return A new CommandMoveRandom object with the same positions and speed as this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandMoveRandom(new Point(this.position1.getX(), this.position1.getY()),
                new Point(this.position2.getX(), this.position2.getY()), this.speed);
    }

}
