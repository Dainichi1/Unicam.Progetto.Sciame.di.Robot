package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void testConstructor() {
        assertNotNull(controller.getRobots());
        assertNotNull(controller.getShapes());
    }


    @Test
    void testReadInstructionList_Success() throws IOException {
        // Add a robot to pass the RobotsNotLoaded exception
        controller.getRobots().add(new Robot(new Point(0, 0)));

        File programFile = Files.createTempFile("program", ".txt").toFile();

        assertDoesNotThrow(() -> {
            List<String> lines = controller.readInstructionList(programFile);
            assertNotNull(lines);
        });
    }

    @Test
    void testReadShapeList_Success() throws IOException {
        File shapeListFile = Files.createTempFile("shapes", ".txt").toFile();

        assertDoesNotThrow(() -> {
            controller.readShapeList(shapeListFile);
            assertNotNull(controller.getShapes());
        });
    }

    @Test
    void testReadRobotList_Success() throws IOException {
        File robotListFile = Files.createTempFile("robots", ".txt").toFile();

        assertDoesNotThrow(() -> {
            controller.readRobotList(robotListFile);
            assertNotNull(controller.getRobots());
        });
    }

    @Test
    void testGenerateRandomRobots() {
        controller.generateRandomRobots(5, new Point(0, 0), new Point(10, 10));
        assertEquals(5, controller.getRobots().size());
    }

    @Test
    void testIsAllRobotFinished_True() {
        Robot r1 = new Robot(new Point(0, 0));
        Robot r2 = new Robot(new Point(1, 1));

        // Set the program state to terminated
        r1.setProgramTerminated(true);
        r2.setProgramTerminated(true);

        controller.getRobots().add(r1);
        controller.getRobots().add(r2);

        assertTrue(controller.isAllRobotFinished());
    }

    @Test
    void testIsAllRobotFinished_False() {
        Robot r1 = new Robot(new Point(0, 0));
        Robot r2 = new Robot(new Point(1, 1));

        // Set the program state for r1 to not terminated
        r1.setProgramTerminated(false);
        r2.setProgramTerminated(true);

        controller.getRobots().add(r1);
        controller.getRobots().add(r2);

        assertFalse(controller.isAllRobotFinished());
    }
}