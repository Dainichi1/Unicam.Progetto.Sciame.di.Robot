package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    private Shape shape;


    private static class TestShape extends Shape {
        public TestShape(Point coordinates, String label) {
            super(coordinates, label);
        }

        @Override
        public Tuple<Double, Double> getDimensions() {
            return null;
        }
    }

    @BeforeEach
    void setUp() {
        shape = new TestShape(new Point(1.0, 2.0), "TestLabel");
    }

    @Test
    void testConstructor() {
        assertNotNull(shape, "Shape object should be initialized.");
    }

    @Test
    void testGetCoordinates() {
        Point coordinates = shape.getCoordinates();
        assertEquals(new Point(1.0, 2.0), coordinates, "Coordinates should match.");
    }

    @Test
    void testGetLabel() {
        String label = shape.getLabel();
        assertEquals("TestLabel", label, "Label should match.");
    }
}
