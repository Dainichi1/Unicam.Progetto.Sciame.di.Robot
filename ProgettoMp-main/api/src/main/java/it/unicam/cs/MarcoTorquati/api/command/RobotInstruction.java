package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;

/**
 * The RobotInstruction interface defines the contract for instructions that can be executed by a Robot.
 * Any class that implements this interface must provide implementations for the methods to execute an instruction,
 * determine if the Robot can proceed to the next instruction, and clone the instruction object.
 */
public interface RobotInstruction {

    /**
     * Executes the instruction on the given Robot object.
     *
     * @param robot The Robot object on which to execute the instruction.
     * @throws IllegalArgumentException If invalid parameters are passed or if the operation is not allowed.
     */
    void runCommand(Robot robot) throws IllegalArgumentException;

    /**
     * Determines whether the Robot can proceed to the next instruction in the instruction set.
     * The method should return the index of the next instruction to execute or -1 if the Robot can proceed to the next instruction.
     *
     * @return The index of the next instruction to execute or -1 if the Robot can proceed to the next instruction.
     */
    int canGoToNextInstruction();

    /**
     * Creates a deep clone of the instruction object.
     * This method is used to create a new object that is a clone of the existing instruction object.
     *
     * @return A new RobotInstruction object, which is a deep clone of this instruction.
     */
    RobotInstruction cloneObject();
}
