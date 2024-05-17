package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.models.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    private Direction direction;

    @BeforeEach
    void setUp() {
        direction = new Direction(0.5, 0.5);
    }

    @Test
    void testConstructorAndAttributes() {
        // Test initial X and Y
        assertEquals(0.5, direction.getX());
        assertEquals(0.5, direction.getY());
    }

    @Test
    void testSetXAndSetY_ValidValues() {
        direction.setX(0.2);
        direction.setY(0.7);

        assertEquals(0.2, direction.getX());
        assertEquals(0.7, direction.getY());
    }

    @Test
    void testSetXAndSetY_InvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            direction.setX(1.5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            direction.setY(2.0);
        });
    }

    @Test
    void testGetXAndGetY() {
        assertEquals(0.5, direction.getX());
        assertEquals(0.5, direction.getY());
    }
}
