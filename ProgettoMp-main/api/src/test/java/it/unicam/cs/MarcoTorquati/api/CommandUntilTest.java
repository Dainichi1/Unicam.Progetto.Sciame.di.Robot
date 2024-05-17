package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.command.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandUntilTest {

    private CommandUntil commandUntil;
    private Robot robot;
    private List<IShape> shapes;

    @BeforeEach
    void setUp() {
        IShape rectangularShape = new Rectangle(2.0, 2.0, new Point(2, 2), "RectLabel");
        IShape circularShape = new Circle(1.0, new Point(5, 5), "CircLabel");

        shapes = Arrays.asList(rectangularShape, circularShape);

        commandUntil = new CommandUntil("RectLabel", shapes, 1);

        robot = new Robot(new Point(0, 0));
    }

    @Test
    void testExecute_WithRectangularShape() {

        robot.move(1.0, new Direction(1.0, 1.0));

        commandUntil.runCommand(robot);

        assertEquals(new Point(2.0, 2.0), robot.getPosition());


        robot.move(1.5, new Direction(1.0, 1.0));

        commandUntil.runCommand(robot);

        assertEquals(1, commandUntil.canGoToNextInstruction());
    }

    @Test
    void testExecute_WithCircularShape() {
        commandUntil = new CommandUntil("CircLabel", shapes, 1);

        robot.move(4.0, new Direction(1.0, 1.0));

        commandUntil.runCommand(robot);

        assertEquals(new Point(8.0, 8.0), robot.getPosition());

        assertEquals(1, commandUntil.canGoToNextInstruction());

        robot.move(0.8, new Direction(1.0, 1.0));

        commandUntil.runCommand(robot);

        assertEquals(1, commandUntil.canGoToNextInstruction());
    }

    @Test
    void testCloneObject() {
        RobotInstruction cloned = commandUntil.cloneObject();

        assertNotSame(cloned, commandUntil);
    }
}
