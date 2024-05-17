package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceCalculatorTest {

    @Test
    void testCalculateDistance() {
        Point source = new Point(0, 0);
        Point destination = new Point(3, 4);

        double expectedDistance = 5.0;
        double actualDistance = DistanceCalculator.calculate(source, destination);

        assertEquals(expectedDistance, actualDistance, 0.001, "The calculated distance should match the expected distance.");
    }

    @Test
    void testCalculateDistanceWithNegativeCoordinates() {
        Point source = new Point(-1, -1);
        Point destination = new Point(-4, -5);

        double expectedDistance = 5.0;
        double actualDistance = DistanceCalculator.calculate(source, destination);

        assertEquals(expectedDistance, actualDistance, 0.001, "The calculated distance should match the expected distance.");
    }

    @Test
    void testFindCoordinatesDifference() {
        double source = 1.0;
        double destination = 4.0;

        double expectedDifference = 3.0;
        double actualDifference = DistanceCalculator.findCoordinatesDifference(source, destination);

        assertEquals(expectedDifference, actualDifference, 0.001, "The calculated coordinate difference should match the expected difference.");
    }
}
