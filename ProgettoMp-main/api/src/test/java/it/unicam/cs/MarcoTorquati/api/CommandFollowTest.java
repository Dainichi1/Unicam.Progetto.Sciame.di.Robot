package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



public class CommandFollowTest {

    private CommandFollow commandFollow;
    private Robot robot;

    @BeforeEach
    void setUp() {
        List<Robot> robots = List.of(
                new Robot(new Point(0, 0)),
                new Robot(new Point(1, 1))
        );

        for (Robot r : robots) {
            r.signalLabel("LABEL");
        }

        robot = new Robot(new Point(2, 2));

        commandFollow = new CommandFollow("LABEL", 2.0, 1.0, robots);
    }

    @Test
    void testExecute() {


        robot = new Robot(new Point(0, 0));  //
        robot.move(1.0, new Direction(1.0, 1.0));

        Point initialPosition = robot.getPosition();

        commandFollow.runCommand(robot);

        Point newPosition = new Point(1.0, 1.0);

        Point expectedAveragePoint = new Point(0.5, 0.5);

        Direction expectedDirection = DirectionCalculator.calculate(initialPosition, expectedAveragePoint);

        Point expectedNewPosition = new Point(
                initialPosition.getX() + expectedDirection.getX(),
                initialPosition.getY() + expectedDirection.getY()
        );

        assertEquals(expectedNewPosition, newPosition);
    }

    @Test
    void testCanGoToNextInstruction() {
        int nextRow = commandFollow.canGoToNextInstruction();
        assertEquals(-1, nextRow);  // Should always return -1
    }

    @Test
    void testCloneObject() {
        CommandFollow cloned = (CommandFollow) commandFollow.cloneObject();

        assertNotSame(cloned, commandFollow);
        assertEquals(commandFollow.canGoToNextInstruction(), cloned.canGoToNextInstruction());
    }
}