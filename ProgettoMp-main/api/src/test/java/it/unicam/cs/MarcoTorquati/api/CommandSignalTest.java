package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.command.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandSignalTest {

    private CommandSignal commandSignal;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandSignal = new CommandSignal("TEST_LABEL");
        robot = new Robot(new Point(0, 0));
    }

    @Test
    void testExecute() {

        commandSignal.runCommand(robot);

        assertEquals("TEST_LABEL", robot.getSignaledLabel());


    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandSignal.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        RobotInstruction cloned = commandSignal.cloneObject();

        assertNotSame(cloned, commandSignal);
        assertEquals(commandSignal.canGoToNextInstruction(), cloned.canGoToNextInstruction());

        assertNotEquals(commandSignal, cloned);
    }

    @Test
    void testExecute_WithNullRobot() {
        try {
            commandSignal.runCommand(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
