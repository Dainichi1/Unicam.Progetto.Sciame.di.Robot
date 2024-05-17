package it.unicam.cs.MarcoTorquati.api.models;

import it.unicam.cs.MarcoTorquati.api.utils.Tuple;

/**
 * The IShape interface represents a shape with specific coordinates, dimensions, and a label.
 * Implementing classes should provide concrete implementations for these properties.
 */
public interface IShape {

    /**
     * Gets the coordinates of the shape's position.
     *
     * @return A Point object representing the shape's position.
     */
    Point getCoordinates();

    /**
     * Gets the dimensions of the shape.
     *
     * @return A Tuple containing the dimensions of the shape.
     * The first item in the tuple represents the width, and the second item represents the height.
     * For circular shapes, the second item should be set to -1.0.
     */
    Tuple<Double, Double> getDimensions();

    /**
     * Gets the label associated with the shape.
     *
     * @return A String representing the label of the shape.
     */
    String getLabel();
}
