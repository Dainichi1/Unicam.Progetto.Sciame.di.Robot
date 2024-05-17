package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class CommandMoveRandomTest {
    private CommandMoveRandom commandMoveRandom;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandMoveRandom = new CommandMoveRandom(new Point(0, 0), new Point(1, 1), 1.0);

        robot = new Robot(new Point(2, 2));
    }

    @Test
    void testExecute() {
        robot.move(1.0, new Direction(1.0, 1.0));

        robot = new Robot(new Point(0, 0));

        Point initialPosition = robot.getPosition();

        commandMoveRandom.runCommand(robot);

        Point newPosition = new Point(1.0, 1.0);

        // Calculate expected new position
        Point expectedNewPosition = new Point(
                initialPosition.getX() + 1.0,
                initialPosition.getY() + 1.0
        );

    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandMoveRandom.canGoToNextInstruction();
        assertEquals(-1, nextRow);  // Should always return -1
    }

    @Test
    void testCloneObject() {
        CommandMoveRandom cloned = (CommandMoveRandom) commandMoveRandom.cloneObject();

        assertEquals(commandMoveRandom.canGoToNextInstruction(), cloned.canGoToNextInstruction());
    }
}
