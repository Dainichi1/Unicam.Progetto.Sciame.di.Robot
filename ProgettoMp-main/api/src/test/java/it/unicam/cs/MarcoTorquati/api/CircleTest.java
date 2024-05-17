package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    private Circle circle;

    @BeforeEach
    void setUp() {
        circle = new Circle(5.0, new Point(2.0, 3.0), "TestLabel");
    }

    @Test
    void testConstructorAndAttributes() {
        // Test label
        assertEquals("TestLabel", circle.getLabel());

        // Test position
        Point position = circle.getCoordinates();
        assertEquals(2.0, position.getX());
        assertEquals(3.0, position.getY());

        // Test radius
        Tuple<Double, Double> dimensions = circle.getDimensions();
        assertEquals(5.0, dimensions.item1());
        assertEquals(-1.0, dimensions.item2());
    }

    @Test
    void testGetDimensions() {
        Tuple<Double, Double> dimensions = circle.getDimensions();
        assertEquals(5.0, dimensions.item1());
        assertEquals(-1.0, dimensions.item2());
    }
}
