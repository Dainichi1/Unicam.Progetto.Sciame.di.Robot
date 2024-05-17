package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.Point;

/**
 * The CoordinatesTranslator class is responsible for translating Cartesian coordinates to screen coordinates.
 */
public class CoordinatesTranslator {

    /** The height of the screen */

    private final double height;

    /** The width of the screen */
    private final double width;

    /**
     * Constructs a new CoordinatesTranslator with the given screen height and width.
     *
     * @param height The height of the screen.
     * @param width  The width of the screen.
     */
    public CoordinatesTranslator(double height, double width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Translates a point's Cartesian coordinates to screen coordinates.
     *
     * @param cartesianCoordinates The point in Cartesian coordinates.
     * @return The point in screen coordinates.
     */
    public Point translateToScreenCoordinates(Point cartesianCoordinates) {
        double x = cartesianCoordinates.getX() + (this.width / 2);
        double y = (this.height / 2) - cartesianCoordinates.getY();
        return new Point(x, y);
    }
}
