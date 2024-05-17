package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionCalculatorTest {

    @Test
    void testCalculateDirection() {
        Point source = new Point(0, 0);
        Point destination = new Point(3, 4);

        Direction expectedDirection = new Direction(0.6, 0.8);
        Direction actualDirection = DirectionCalculator.calculate(source, destination);

        assertEquals(expectedDirection.getX(), actualDirection.getX(), 0.001, "The X component of the direction should match.");
        assertEquals(expectedDirection.getY(), actualDirection.getY(), 0.001, "The Y component of the direction should match.");
    }

    @Test
    void testCalculateDirectionWithSamePoint() {
        Point source = new Point(0, 0);
        Point destination = new Point(0, 0);

        Direction expectedDirection = new Direction(0, 0);
        Direction actualDirection = DirectionCalculator.calculate(source, destination);

        assertEquals(expectedDirection.getX(), actualDirection.getX(), 0.001, "The X component of the direction should be zero.");
        assertEquals(expectedDirection.getY(), actualDirection.getY(), 0.001, "The Y component of the direction should be zero.");
    }

    @Test
    void testCalculateDirectionWithNegativeCoordinates() {
        Point source = new Point(-1, -1);
        Point destination = new Point(-4, -5);

        Direction expectedDirection = new Direction(-0.6, -0.8);  // ipotetici valori calcolati manualmente
        Direction actualDirection = DirectionCalculator.calculate(source, destination);

        assertEquals(expectedDirection.getX(), actualDirection.getX(), 0.001, "The X component of the direction should match.");
        assertEquals(expectedDirection.getY(), actualDirection.getY(), 0.001, "The Y component of the direction should match.");
    }
}
