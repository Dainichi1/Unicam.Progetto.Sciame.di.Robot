package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import it.unicam.cs.MarcoTorquati.utilities.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Controller class for managing Robots and Shapes, and for executing parsed robot programs.
 */
public class Controller {
    private final List<Robot> robots;
    private final List<IShape> shapes;
    private final FollowMeParser parser;

    /**
     * Initializes a new Controller with given lists of Robots and Shapes.
     *
     * @param robots List of Robot objects.
     * @param shapes List of IShape objects.
     */
    public Controller(List<Robot> robots, List<IShape> shapes) {
        this.robots = robots;
        this.shapes = shapes;
        final FollowMeParserHandler handler = new ParserHandler(this.robots, this.shapes);
        this.parser = new FollowMeParser(handler);

    }

    /**
     * Default constructor initializes a new Controller with empty lists of Robots and Shapes.
     */
    public Controller() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    /**
     * Reads and parses the program file to execute robot instructions.
     *
     * @param programFile File containing the robot program.
     * @return List of lines in the program file.
     * @throws IOException If reading the file fails.
     * @throws it.unicam.cs.MarcoTorquati.utilities.FollowMeParserException If parsing the program fails.
     * @throws IllegalArgumentException If the list of robots is empty.
     */
    public List<String> readInstructionList(File programFile) throws IOException, FollowMeParserException, IllegalArgumentException {
        List<String> lines = Files.readAllLines(programFile.toPath());
        this.parser.parseRobotProgram(programFile);
        return lines;
    }

    /**
     * Reads and parses the shape list file to create shapes in the environment.
     *
     * @param shapeListFile File containing the shape list.
     * @throws IOException If reading the file fails.
     * @throws it.unicam.cs.MarcoTorquati.utilities.FollowMeParserException If parsing the shape list fails.
     */
    public void readShapeList(File shapeListFile) throws IOException, FollowMeParserException {
        List<ShapeData> shapeDataList = this.parser.parseEnvironment(shapeListFile);
        List<IShape> shapeList = shapeDataList.stream().map(new ShapeParser()::parseFromShapeData).toList();
        this.shapes.addAll(shapeList);
    }

    /**
     * Reads and creates robots from a file.
     *
     * @param robotListFile File containing the list of robots.
     * @throws IOException If reading the file fails.
     */
    public void readRobotList(File robotListFile) throws IOException {
        List<String> lines = Files.readAllLines(robotListFile.toPath());
        for (String line : lines) {
            String[] elements = line.trim().toUpperCase().split(" ");
            this.robots.add(new Robot(new Point(Double.parseDouble(elements[1]), Double.parseDouble(elements[2]))));
        }
    }

    /**
     * Generates a given number of random robots within specified coordinates.
     *
     * @param robotsNumber The number of robots to generate.
     * @param minPoint The minimum coordinates point.
     * @param maxPoint The maximum coordinates point.
     */
    public void generateRandomRobots(int robotsNumber, Point minPoint, Point maxPoint) {
        for (int i = 0; i < robotsNumber; i++) {
            Point position = RandomCoordinatesCalculator.calculate(minPoint, maxPoint);
            this.robots.add(new Robot(position));
        }
    }

    /**
     * Executes the next instruction for each robot.
     *
     * @throws IllegalArgumentException If a problem occurs during instruction execution.
     */
    public void nextInstruction() throws IllegalArgumentException {
        for (Robot r : this.robots) {
            if (!r.isProgramTerminated()) {
                r.executeNextInstruction();
            }
        }
    }

    /**
     * Checks if all robots have finished executing their programs.
     *
     * @return true if all robots have finished, false otherwise.
     */
    public boolean isAllRobotFinished() {
        return this.robots.stream().filter(Robot::isProgramTerminated).count() ==
                this.robots.size();
    }

    /**
     * Returns the list of robots.
     *
     * @return List of Robot objects.
     */
    public List<Robot> getRobots() {
        return robots;
    }

    /**
     * Returns the list of shapes.
     *
     * @return List of IShape objects.
     */
    public List<IShape> getShapes() {
        return shapes;
    }

}
