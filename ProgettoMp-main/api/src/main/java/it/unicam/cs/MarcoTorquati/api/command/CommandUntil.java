package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;

import java.util.*;

/**
 * Represents a command to execute until a condition involving a specific shape label is met.
 * This class extends the {@link CommandIteretive} class.
 */
public class CommandUntil extends CommandIteretive {
    private final String labelToFind;
    private final List<IShape> shapes;
    private boolean hasToExit;

    /**
     * Constructs a new CommandUntil object with the specified label to find, shapes list, and the first row of iteration.
     *
     * @param labelToFind The label of the shape to find.
     * @param shapes The list of shapes available.
     * @param firstRowIteration The row where the iteration starts.
     */
    public CommandUntil(String labelToFind, List<IShape> shapes, int firstRowIteration) {
        super(firstRowIteration);
        this.labelToFind = labelToFind;
        this.shapes = shapes;
        this.hasToExit = false;
    }

    /**
     * Executes the until command on the given robot.
     * Continues the robot's movement until it meets the specified condition based on the shape's label.
     *
     * @param robot The robot on which to execute the until command.
     */
    @Override
    public void runCommand(Robot robot) {
        IShape shape = this.shapes.stream().filter(x -> x.getLabel().equalsIgnoreCase(this.labelToFind)).findFirst().orElse(null);
        if(shape != null) {
            if(shape.getDimensions().item2() == -1) {
                this.checkCircularShape(robot, shape);
            } else {
                this.checkRectangularShape(robot, shape);
            }
        }
        robot.continueMove();
    }

    /**
     * Checks if the robot is inside a rectangular shape and updates the hasToExit flag accordingly.
     *
     * @param robot The robot to check.
     * @param shape The rectangular shape to check against.
     */
    private void checkRectangularShape(Robot robot, IShape shape) {
        double width = shape.getDimensions().item1();
        double height = shape.getDimensions().item2();
        Point topLeft = new Point(shape.getCoordinates().getX() - (width / 2), shape.getCoordinates().getY() - (height / 2));
        Point bottomRight = new Point(shape.getCoordinates().getX() + (width / 2), shape.getCoordinates().getY() + (height / 2));
        this.hasToExit = NumericRangeChecker.DEFAULT_CHECKER.isBetween(robot.getPosition().getX(), topLeft.getX(), bottomRight.getX()) &&
                NumericRangeChecker.DEFAULT_CHECKER.isBetween(robot.getPosition().getY(), topLeft.getY(), bottomRight.getY());
    }

    /**
     * Checks if the robot is inside a circular shape and updates the hasToExit flag accordingly.
     *
     * @param robot The robot to check.
     * @param shape The circular shape to check against.
     */
    private void checkCircularShape(Robot robot, IShape shape) {
        double radius = shape.getDimensions().item1();
        double distance = DistanceCalculator.calculate(robot.getPosition(), shape.getCoordinates());
        this.hasToExit = distance <= radius;
    }

    /**
     * Determines whether the robot can proceed to the next instruction or should repeat the current iteration.
     *
     * @return The index of the next instruction to execute.
     */
    @Override
    public int canGoToNextInstruction() {
        return this.hasToExit ? this.endOfIteration : this.firstRowIteration;
    }

    /**
     * Creates a clone of this CommandUntil object.
     *
     * @return A new CommandUntil object, which is a clone of this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        CommandUntil ui = new CommandUntil(this.labelToFind, this.shapes, this.firstRowIteration);
        ui.endOfIteration = this.endOfIteration;
        return ui;
    }
}
