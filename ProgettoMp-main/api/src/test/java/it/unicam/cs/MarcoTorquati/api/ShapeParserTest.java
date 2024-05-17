package it.unicam.cs.MarcoTorquati.api;


import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import it.unicam.cs.MarcoTorquati.utilities.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeParserTest {

    private ShapeParser shapeParser;

    @BeforeEach
    void setUp() {
        shapeParser = new ShapeParser();
    }

    @Test
    void testParseFromShapeData_Circle() {
        double[] args = {1.0, 1.0, 1.0};
        ShapeData circleData = new ShapeData("CircLabel", "CIRCLE", args);

        IShape expectedCircle = new Circle(1.0, new Point(1, 1), "CircLabel");

        IShape actualCircle = shapeParser.parseFromShapeData(circleData);

        assertEquals(expectedCircle, actualCircle, "Parsed shape should match the expected CircularShape.");
    }

    @Test
    void testParseFromShapeData_Rectangle() {
        double[] args = {2.0, 2.0, 2.0, 2.0};
        ShapeData rectangleData = new ShapeData("RectLabel", "RECTANGLE", args);
        IShape expectedRectangle = new Rectangle(2.0, 2.0, new Point(2, 2), "RectLabel");

        IShape actualRectangle = shapeParser.parseFromShapeData(rectangleData);

        assertEquals(expectedRectangle, actualRectangle, "Parsed shape should match the expected RectangularShape.");
    }

    @Test
    void testParseFromShapeData_InvalidShape() {
        double[] args = {1.0, 1.0, 1.0, 1.0};
        ShapeData invalidData = new ShapeData("InvalidLabel", "INVALID", args);

        assertThrows(IllegalArgumentException.class, () -> shapeParser.parseFromShapeData(invalidData), "An exception should be thrown for invalid shape types.");
    }
}
