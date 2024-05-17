package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class NumericRangeCheckerTest {

    @Test
    void testIsBetween() {
        assertTrue(NumericRangeChecker.DEFAULT_CHECKER.isBetween(5.0, 1.0, 10.0), "5.0 should be between 1.0 and 10.0");

        assertFalse(NumericRangeChecker.DEFAULT_CHECKER.isBetween(5.0, 6.0, 10.0), "5.0 should not be between 6.0 and 10.0");

        assertTrue(NumericRangeChecker.DEFAULT_CHECKER.isBetween(1.0, 1.0, 10.0), "1.0 should be between 1.0 and 10.0");

        assertTrue(NumericRangeChecker.DEFAULT_CHECKER.isBetween(10.0, 1.0, 10.0), "10.0 should be between 1.0 and 10.0");

        assertThrows(IllegalArgumentException.class, () -> NumericRangeChecker.DEFAULT_CHECKER.isBetween(5.0, 10.0, 1.0), "An exception should be thrown if minValue > maxValue");
    }
}
