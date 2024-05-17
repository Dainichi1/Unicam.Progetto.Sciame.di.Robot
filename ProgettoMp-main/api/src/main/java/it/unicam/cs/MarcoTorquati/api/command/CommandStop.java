package it.unicam.cs.MarcoTorquati.api.command;

import it.unicam.cs.MarcoTorquati.api.models.*;

/**
 * Represents a command to stop the robot's movement.
 * This class implements the {@link RobotInstruction} interface.
 */
public class CommandStop implements RobotInstruction{

    /**
     * Stops the robot's movement by setting its speed and direction to zero.
     *
     * @param robot The robot whose movement will be stopped.
     */
    @Override
    public void runCommand(Robot robot) {
        robot.move(0, new Direction(0,0));

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
     * Creates a clone of this CommandStop object.
     *
     * @return A new CommandStop object, which is a clone of this object.
     */
    @Override
    public RobotInstruction cloneObject() {
        return new CommandStop();
    }


}
