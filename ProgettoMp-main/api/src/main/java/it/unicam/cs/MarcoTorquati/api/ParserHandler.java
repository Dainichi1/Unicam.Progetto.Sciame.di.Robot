package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import it.unicam.cs.MarcoTorquati.utilities.*;

import java.util.*;

/**
 * Implements the FollowMeParserHandler interface to parse and handle robot programs.
 */
public class ParserHandler implements FollowMeParserHandler {

    private NumericRangeChecker<Double> checker;

    private final Program program;

    private final List<Robot> robots;

    private final List<IShape> shapes;

    private Stack<Integer> stack;

    private Map<Integer, CommandIteretive> dictionary;

    private int instructionCounter;

    /**
     * Initializes a new ParserHandler with given lists of Robots and Shapes.
     *
     * @param robots List of Robot objects.
     * @param shapes List of IShape objects.
     */
    public ParserHandler(List<Robot> robots, List<IShape> shapes) {
        this.program = new Program();
        this.robots = robots;
        this.shapes = shapes;
    }

    /**
     * Called when parsing starts.
     */
    @Override
    public void parsingStarted() {
        this.checker = NumericRangeChecker.DEFAULT_CHECKER;
        this.stack = new Stack<>();
        this.dictionary = new HashMap<>();
        this.instructionCounter = 0;
    }

    /**
     * Called when parsing is done.
     */
    @Override
    public void parsingDone() {
        this.checker = null;
        this.stack.clear();
        this.dictionary.clear();
        this.instructionCounter = 0;
        this.robots.forEach(r -> r.loadProgramToExecute(Program.copyOf(this.program)));
    }

    /**
     * Handles move command.
     *
     * @param args Array of arguments for move command.
     */
    @Override
    public void moveCommand(double[] args) {
        if (isValidDirection(args[0], args[1])) {
            this.program.addInstruction(new CommandMove(new Direction(args[0], args[1]), args[2]));
            this.instructionCounter++;
        }
    }

    /**
     * Checks if given (x, y) forms a valid direction.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true if valid, false otherwise.
     */
    private boolean isValidDirection(double x, double y) {
        return checker.isBetween(x, -1.0, 1.0)
                && checker.isBetween(y, -1.0, 1.0);
    }

    /**
     * Handles moveRandom command.
     *
     * @param args Array of arguments for moveRandom command.
     */
    @Override
    public void moveRandomCommand(double[] args) {
        Point point1 = new Point(args[0], args[2]);
        Point point2 = new Point(args[1], args[3]);
        this.program.addInstruction(new CommandMoveRandom(point1, point2, args[4]));
        this.instructionCounter++;
    }

    /**
     * Handles signal command.
     *
     * @param label Label to be signaled.
     */
    @Override
    public void signalCommand(String label) {
        this.program.addInstruction(new CommandSignal(label));
        this.instructionCounter++;
    }

    /**
     * Handles unsignal command.
     *
     * @param label Label to be unsignaled.
     */
    @Override
    public void unsignalCommand(String label) {
        this.program.addInstruction(new CommandUnsignal(label));
        this.instructionCounter++;
    }

    /**
     * Handles follow command.
     *
     * @param label Label to be followed.
     * @param args Array of arguments for follow command.
     */
    @Override
    public void followCommand(String label, double[] args) {
        this.program.addInstruction(new CommandFollow(label, args[0], args[1], this.robots));
        this.instructionCounter++;
    }

    /**
     * Handles stop command.
     */
    @Override
    public void stopCommand() {
        this.program.addInstruction(new CommandStop());
        this.instructionCounter++;
    }

    /**
     * Handles continue command.
     *
     * @param s Number of instructions to skip.
     */
    @Override
    public void continueCommand(int s) {
        this.program.addInstruction(new CommandContinue(s, this.instructionCounter));
        this.instructionCounter++;
    }

    /**
     * Handles repeat command.
     *
     * @param n Number of times to repeat.
     */
    @Override
    public void repeatCommandStart(int n) {
        CommandIteretive instruction = new CommandRepeat(n, this.instructionCounter + 1);
        this.addIterativeCommand(instruction);
    }

    /**
     * Handles until command.
     *
     * @param label Label to be checked.
     */
    @Override
    public void untilCommandStart(String label) {
        CommandIteretive instruction = new CommandUntil(label, shapes, this.instructionCounter + 1);
        this.addIterativeCommand(instruction);
    }

    /**
     * Handles doForeverStart command.
     *
     */
    @Override
    public void doForeverStart() {
        CommandIteretive instruction = new CommandDoForever(this.instructionCounter + 1);
        this.addIterativeCommand(instruction);
    }

    /**
     * Handles addIterativeCommand command.
     * @param instruction CommandIteretive to be added.
     *
     */
    private void addIterativeCommand(CommandIteretive instruction) {
        this.dictionary.put(this.instructionCounter, instruction);
        this.stack.add(this.instructionCounter);
        this.program.addInstruction(instruction);
        this.instructionCounter++;
    }

    /**
     * Handles doneCommand command.
     *
     */
    @Override
    public void doneCommand() {
        int row = this.stack.pop();
        CommandIteretive commandIteretive = this.dictionary.get(row);
        commandIteretive.setEndOfIteration(this.instructionCounter + 1);
        RobotInstruction instruction = new CommandDone(row);
        this.program.addInstruction(instruction);
        this.instructionCounter++;
    }

}
