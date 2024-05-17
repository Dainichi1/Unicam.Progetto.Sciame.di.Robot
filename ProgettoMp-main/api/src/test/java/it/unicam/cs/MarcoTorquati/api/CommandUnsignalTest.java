package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandUnsignalTest {

    private CommandUnsignal commandUnsignal;
    private Robot robot;

    @BeforeEach
    void setUp() {
        commandUnsignal = new CommandUnsignal("TEST_LABEL");
        robot = new Robot(new Point(1, 1));
        robot.signalLabel("TEST_LABEL");
    }

    @Test
    void testExecute() {
        commandUnsignal.runCommand(robot);
        assertEquals("", robot.getSignaledLabel());
    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandUnsignal.canGoToNextInstruction();
        assertEquals(-1, nextRow);
    }

    @Test
    void testCloneObject() {
        RobotInstruction cloned = commandUnsignal.cloneObject();

        assertNotSame(cloned, commandUnsignal);
        assertEquals(commandUnsignal.canGoToNextInstruction(), cloned.canGoToNextInstruction());

        assertNotEquals(commandUnsignal, cloned);
    }

    @Test
    void testExecute_WithInvalidLabel() {
        try {
            robot.signalLabel("ANOTHER_LABEL");

            commandUnsignal.runCommand(robot);

            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
