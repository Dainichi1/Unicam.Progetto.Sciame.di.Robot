package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class CommandRepeatTest {

    private CommandRepeat commandRepeat;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandRepeat = new CommandRepeat(3, 1);
        robot = new Robot(new Point(0, 0));
        robot.move(1.0, new Direction(1.0, 1.0));
    }

    @Test
    void testExecute() {
        robot.move(1.0, new Direction(1.0, 1.0));
        robot = new Robot(new Point(0, 0));

        Point initialPosition = robot.getPosition();

        commandRepeat.runCommand(robot);

        Point newPosition = new Point(1.0, 1.0);

        Point expectedNewPosition = new Point(
                initialPosition.getX() + 1.0,
                initialPosition.getY() + 1.0
        );

        assertEquals(expectedNewPosition, newPosition);
    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow;

        commandRepeat.runCommand(robot);
        nextRow = commandRepeat.canGoToNextInstruction();
        assertEquals(1, nextRow);

        commandRepeat.runCommand(robot);
        nextRow = commandRepeat.canGoToNextInstruction();
        assertEquals(1, nextRow);

        commandRepeat.runCommand(robot);
        nextRow = commandRepeat.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        CommandRepeat cloned = (CommandRepeat) commandRepeat.cloneObject();
        assertNotSame(cloned, commandRepeat);
        assertEquals(commandRepeat.canGoToNextInstruction(), cloned.canGoToNextInstruction());
    }
}
