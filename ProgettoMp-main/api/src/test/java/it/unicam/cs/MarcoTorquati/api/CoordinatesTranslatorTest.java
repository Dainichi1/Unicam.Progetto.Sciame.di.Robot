package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTranslatorTest {

    private CoordinatesTranslator translator;

    @BeforeEach
    void setUp() {
        translator = new CoordinatesTranslator(200.0, 300.0);
    }

    @Test
    void testTranslateToScreenCoordinates() {
        Point cartesianPoint = new Point(-50.0, 100.0);


        Point expectedScreenPoint = new Point(100.0, 0.0);

        Point actualScreenPoint = translator.translateToScreenCoordinates(cartesianPoint);

        assertEquals(expectedScreenPoint, actualScreenPoint, "Screen coordinates should match the expected values.");
    }
}
