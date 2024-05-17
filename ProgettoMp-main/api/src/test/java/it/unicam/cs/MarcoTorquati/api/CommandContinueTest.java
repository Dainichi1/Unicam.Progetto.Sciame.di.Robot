package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;



class CommandContinueTest {

    private CommandContinue commandContinue;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandContinue = new CommandContinue(3, 1);
        robot = new Robot(new Point(0, 0));
    }

    @Test
    void testExecute() {
        Point initialPosition = new Point(1.0, 1.0);
        robot.move(1.0, new Direction(1.0, 1.0));
        commandContinue.runCommand(robot);
        Point newPosition = robot.getPosition();
        Point expectedNewPosition = new Point(
                initialPosition.getX() + 1.0 * 1.0,
                initialPosition.getY() + 1.0 * 1.0
        );

        assertEquals(expectedNewPosition, newPosition);
    }

    @Test
    void testCanGoToNextInstruction_NotReady() {

        commandContinue.runCommand(robot);
        commandContinue.runCommand(robot);
        int nextRow = commandContinue.canGoToNextInstruction();
        assertEquals(1, nextRow);
    }

    @Test
    void testCanGoToNextInstruction_Ready() {

        commandContinue.runCommand(robot);
        commandContinue.runCommand(robot);
        commandContinue.runCommand(robot);
        int nextRow = commandContinue.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        CommandContinue cloned = (CommandContinue) commandContinue.cloneObject();
        assertNotSame(cloned, commandContinue);
        assertEquals(commandContinue.canGoToNextInstruction(), cloned.canGoToNextInstruction());
    }
}
