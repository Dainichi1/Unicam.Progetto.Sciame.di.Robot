package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;



class CommandMoveTest {

    private CommandMove commandMove;
    private Robot robot;

    @BeforeEach
    void setUp() {
        Direction dir = new Direction(1.0, 1.0);
        double speed = 2.0;
        commandMove = new CommandMove(dir, speed);


        robot = new Robot(new Point(0, 0));
    }

    @Test
    void testExecute() {
        robot.setPosition(new Point(0, 0));


        System.out.println("Initial position: " + robot.getPosition());

        commandMove.runCommand(robot);


        Point newPosition = robot.getPosition();


        System.out.println("New position: " + newPosition);

        Point expectedNewPosition = new Point(
                0.0 + 2.0 * 1.0,
                0.0 + 2.0 * 1.0
        );


        System.out.println("Expected position: " + expectedNewPosition);

        assertEquals(expectedNewPosition, newPosition);
    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandMove.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        CommandMove cloned = (CommandMove) commandMove.cloneObject();

        assertNotSame(cloned, commandMove);
        assertEquals(commandMove.canGoToNextInstruction(), cloned.canGoToNextInstruction());
    }
}