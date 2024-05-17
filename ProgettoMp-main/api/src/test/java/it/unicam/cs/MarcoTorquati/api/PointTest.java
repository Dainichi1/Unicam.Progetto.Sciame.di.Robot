package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    private Point point;

    @BeforeEach
    void setUp() {
        point = new Point(1.0, 2.0);
    }

    @Test
    void testConstructorAndAttributes() {
        // Test initial X and Y
        assertEquals(1.0, point.getX());
        assertEquals(2.0, point.getY());
    }

    @Test
    void testSetXAndSetY() {
        point.setX(3.0);
        point.setY(4.0);

        assertEquals(3.0, point.getX());
        assertEquals(4.0, point.getY());
    }

    @Test
    void testEquals() {
        Point anotherPoint = new Point(1.0, 2.0);
        assertTrue(point.equals(anotherPoint));

        anotherPoint.setX(3.0);
        assertNotEquals(point, anotherPoint);
    }

    @Test
    void testHashCode() {
        Point anotherPoint = new Point(1.0, 2.0);
        assertEquals(Objects.hash(1.0, 2.0), point.hashCode());
        assertEquals(point.hashCode(), anotherPoint.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("{ x: 1.0; y: 2.0}", point.toString());
    }
}
