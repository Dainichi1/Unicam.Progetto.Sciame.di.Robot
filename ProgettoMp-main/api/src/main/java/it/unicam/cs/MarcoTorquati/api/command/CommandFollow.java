package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;

import java.util.*;

/**
 * Represents a "Follow" command for a robot. This command instructs a robot to follow other robots
 * with a specific label within a certain distance, moving at a specified speed.
 */
public class CommandFollow implements RobotInstruction {

    /**
     * The label of the robots to follow.
     */
    private final String labelToFollow;

    /**
     * The maximum distance within which to follow other robots.
     */
    private final double distance;

    /**
     * The speed at which to follow other robots.
     */
    private final double speed;

    /**
     * The list of all robots, used to find robots to follow.
     */
    private final List<Robot> robots;

    /**
     * Constructs a new CommandFollow instance.
     *
     * @param labelToFollow The label of the robots to follow.
     * @param distance      The maximum distance within which to follow.
     * @param speed         The speed at which to follow.
     * @param robots        The list of all robots.
     */
    public CommandFollow(String labelToFollow, double distance, double speed, List<Robot> robots) {
        this.labelToFollow = labelToFollow;
        this.distance = distance;
        this.speed = speed;
        this.robots = robots;
    }

    /**
     * Executes the command on the given robot. The robot will follow other robots with the specified label
     * within the specified distance, at the specified speed.
     *
     * @param robot The robot on which to execute the command.
     * @throws IllegalArgumentException if the command cannot be executed.
     */
    @Override
    public void runCommand(Robot robot) throws IllegalArgumentException {
        List<Robot> robotWithLabel = this.getFilteredRobot(robot);
        if(!robotWithLabel.isEmpty()) {
            Point averagePoint = this.calculateAveragePoint(robotWithLabel);
            Direction dir = DirectionCalculator.calculate(robot.getPosition(), averagePoint);
            robot.move(this.speed, dir);

        } else {
            moveToRandom(robot);
        }
    }

    /**
     * Returns a list of robots with the specified label within the specified distance from the given robot.
     *
     * @param robot The robot from which to calculate the distance.
     * @return A list of robots with the specified label within the specified distance from the given robot.
     */
    private List<Robot> getFilteredRobot(Robot robot) {
        return this.robots.stream()
                .filter(x -> x.getSignaledLabel().equalsIgnoreCase(this.labelToFollow))
                .filter(x -> DistanceCalculator.calculate(robot.getPosition(), x.getPosition()) <= this.distance)
                .toList();
    }

    /**
     * Moves the robot to a random position within the specified distance.
     * @param robot
     * @throws IllegalArgumentException
     */
    private void moveToRandom(Robot robot) throws IllegalArgumentException {
        Point point1 = new Point(-this.distance, -this.distance);
        Point point2 = new Point(this.distance, this.distance);
        new CommandMoveRandom(point1, point2, this.speed).runCommand(robot);
    }

    /**
     * Calculates the average point of the given list of robots.
     *
     * @param robotWithLabel The list of robots from which to calculate the average point.
     * @return The average point of the given list of robots.
     */
    private Point calculateAveragePoint(List<Robot> robotWithLabel) {
        double averageX = robotWithLabel.stream().mapToDouble(x -> x.getPosition().getX()).sum()
                / robotWithLabel.stream().mapToDouble(x -> x.getPosition().getX()).count();
        double averageY = robotWithLabel.stream().mapToDouble(x -> x.getPosition().getY()).sum()
                / robotWithLabel.stream().mapToDouble(x -> x.getPosition().getY()).count();
        return new Point(averageX, averageY);
    }

    /**
     * Determines whether the robot can move to the next instruction.
     *
     * @return -1, indicating that the robot should continue executing this instruction indefinitely.
     */
    @Override
    public int canGoToNextInstruction() {
        return -1;
    }

    /**
     * Creates and returns a clone of this CommandFollow object.
     *
     * @return a clone of this CommandFollow object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandFollow(this.labelToFollow, this.distance, this.speed, this.robots);
    }
}
