package it.unicam.cs.MarcoTorquati.api.models;

import it.unicam.cs.MarcoTorquati.api.Program;

/**
 * The Robot class represents a robot in a 2D coordinate system.
 * It encapsulates the robot's position, direction, speed, and other operational characteristics.
 */
public class Robot {
    private Direction direction;
    private Point position;
    private double speed;
    private String labelToSignal;
    private Program program;
    private boolean isProgramTerminated;

    /**
     * Initializes a new Robot object at the specified position.
     *
     * @param position The initial position of the robot as a Point object.
     */
    public Robot(Point position) {
        this.position = position;
        this.direction = new Direction();
        this.labelToSignal = "";
        this.speed = 0;
    }

    /**
     * Initializes a new Robot object at the origin.
     */
    public Robot() {
        this(new Point());
    }

    /**
     * Retrieves the current position of the robot.
     *
     * @return The current position as a Point object.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of the robot.
     *
     * @param point The new position as a Point object.
     */
    public void setPosition(Point point) { this.position = point; }

    /**
     * Checks if the program loaded into the robot is terminated.
     *
     * @return true if the program is terminated, false otherwise.
     */
    public boolean isProgramTerminated() {
        return isProgramTerminated;
    }

    /**
     * Moves the robot in the given direction at the given speed.
     *
     * @param speed The speed at which to move the robot.
     * @param dir The direction in which to move the robot.
     */
    public void move(double speed, Direction dir) {
        this.direction = new Direction(dir.getX(), dir.getY());
        this.speed = speed;
        this.position.setX((speed * dir.getX()) + this.position.getX());
        this.position.setY((speed * dir.getY()) + this.position.getY());
    }

    /**
     * Continues to move the robot in its current direction and speed.
     */
    public void continueMove() {
        this.move(this.speed, this.direction);
    }

    /**
     * Retrieves the label currently being signaled by the robot.
     *
     * @return The signaled label.
     */
    public String getSignaledLabel() { return this.labelToSignal;}

    /**
     * Signals a label.
     *
     * @param label The label to signal.
     */
    public void signalLabel(String label) {
        this.labelToSignal = label;
    }

    /**
     * Stops signaling a label.
     *
     * @param label The label to stop signaling.
     * @throws IllegalArgumentException if the label doesn't match the current signaled label.
     */
    public void unsignalLabel(String label) throws IllegalArgumentException {
        if(!this.labelToSignal.isEmpty() && !this.labelToSignal.equalsIgnoreCase(label)) {
            throw new IllegalArgumentException("La label passata non è uguale a quella che il robot sta segnalando");
        }
        this.labelToSignal = "";
    }

    /**
     * Loads a program to be executed by the robot.
     *
     * @param program The program to load.
     */
    public void loadProgramToExecute(Program program) {
        this.program = program;
    }

    /**
     * Executes the next instruction in the program.
     *
     * @throws IllegalArgumentException if the instruction is invalid.
     */
    public void executeNextInstruction() throws IllegalArgumentException {
        this.isProgramTerminated = !this.program.executeInstruction(this);
    }

    /**
     * Sets the program termination status.
     *
     * @param isProgramTerminated true if the program is terminated, false otherwise.
     */
    public void setProgramTerminated(boolean isProgramTerminated) {
        this.isProgramTerminated = isProgramTerminated;
    }
}
