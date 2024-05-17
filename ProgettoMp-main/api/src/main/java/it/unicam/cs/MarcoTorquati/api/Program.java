package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.Robot;
import it.unicam.cs.MarcoTorquati.api.command.RobotInstruction;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a program that contains a list of instructions for a robot to execute.
 */
public class Program {

    private List<RobotInstruction> robotInstructions;

    private int programCounter;

    /**
     * Constructs a new Program object with an empty list of instructions and initializes the program counter to 0.
     */
    public Program() {
        this.programCounter = 0;
        this.robotInstructions = new LinkedList<>();
    }

    /**
     * Adds a new instruction to the program's list of instructions.
     *
     * @param instruction The RobotInstruction object to add.
     * @throws IllegalArgumentException if the instruction is null.
     */
    public void addInstruction(RobotInstruction instruction) {
        if(instruction == null) {
            throw new IllegalArgumentException("L'istruzione non può essere nulla");
        }
        robotInstructions.add(instruction);
    }

    /**
     * Executes the next instruction in the program for the given robot.
     *
     * @param robot The Robot object for which to execute the instruction.
     * @return true if there are more instructions to execute, false otherwise.
     * @throws IllegalArgumentException if the robot is null.
     */
    public boolean executeInstruction(Robot robot) throws IllegalArgumentException {
        if(robot == null) throw new IllegalArgumentException("Il robot non può essere nullo");
        if(this.programCounter == robotInstructions.size()) { return false; }
        RobotInstruction instruction = this.robotInstructions.get(this.programCounter);
        instruction.runCommand(robot);
        int nextInstruction = instruction.canGoToNextInstruction();
        if(nextInstruction == -1)
            this.programCounter++;
        else
            this.programCounter = nextInstruction;
        return true;
    }

    /**
     * Creates a deep copy of the given Program object.
     *
     * @param p The Program object to copy.
     * @return A new Program object that is a deep copy of the given object.
     */
    public static Program copyOf(Program p) {
        Program copy = new Program();
        copy.robotInstructions = List.copyOf(p.robotInstructions.stream().map(RobotInstruction::cloneObject).toList());
        return copy;
    }
}
