package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandStopTest {

    private CommandStop commandStop;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandStop = new CommandStop();
        robot = new Robot(new Point(1, 1));
        robot.move(1.0, new Direction(1.0, 1.0));
    }

    @Test
    void testExecute() {
        Point initialPosition = robot.getPosition();

        commandStop.runCommand(robot);

        assertEquals(initialPosition, robot.getPosition());
    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandStop.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        RobotInstruction cloned = commandStop.cloneObject();

        assertNotSame(cloned, commandStop);
        assertEquals(commandStop.canGoToNextInstruction(), cloned.canGoToNextInstruction());

        assertNotEquals(commandStop, cloned);
    }
}
