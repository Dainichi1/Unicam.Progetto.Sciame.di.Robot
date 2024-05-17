package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    void setUp() {
        rectangle = new Rectangle(4.0, 5.0, new Point(1.0, 2.0), "TestLabel");
    }

    @Test
    void testConstructorAndAttributes() {
        assertEquals("TestLabel", rectangle.getLabel());



        Tuple<Double, Double> dimensions = rectangle.getDimensions();
        assertEquals(4.0, dimensions.item1());
        assertEquals(5.0, dimensions.item2());
    }

    @Test
    void testGetDimensions() {
        Tuple<Double, Double> dimensions = rectangle.getDimensions();
        assertEquals(4.0, dimensions.item1());
        assertEquals(5.0, dimensions.item2());
    }
}
