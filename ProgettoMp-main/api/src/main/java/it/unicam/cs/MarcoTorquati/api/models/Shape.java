package it.unicam.cs.MarcoTorquati.api.models;

/**
 * The Shape class is an abstract representation of a shape on a 2D plane.
 * It serves as the base class for various types of shapes and implements the IShape interface.
 */
public abstract class Shape implements IShape {

    private final Point coordinates;

    private final String label;

    /**
     * Initializes a new Shape object with the given coordinates and label.
     *
     * @param coordinates The coordinates of the shape on the 2D plane.
     * @param label       The label associated with the shape.
     */
    protected Shape(Point coordinates, String label) {
        this.coordinates = coordinates;
        this.label = label;
    }

    /**
     * Retrieves the coordinates of the shape.
     *
     * @return The coordinates as a Point object.
     */
    @Override
    public Point getCoordinates() {
        return this.coordinates;
    }

    /**
     * Retrieves the label associated with the shape.
     *
     * @return The label as a String.
     */
    @Override
    public String getLabel() {
        return this.label;
    }
}
