package it.unicam.cs.MarcoTorquati.api.models;

import it.unicam.cs.MarcoTorquati.api.utils.*;


import java.util.*;

/**
 * The Rectangle class represents a rectangle shape in a 2D coordinate system.
 * It extends the Shape class to provide additional properties specific to rectangles.
 */
public class Rectangle extends Shape {

    private final double width;

    private final double height;

    /**
     * Constructs a Rectangle object with specified width, height, position, and label.
     *
     * @param width    The width of the rectangle.
     * @param height   The height of the rectangle.
     * @param position The position of the rectangle as a Point object.
     * @param label    The label of the rectangle.
     */
    public Rectangle(double width, double height, Point position, String label) {
        super(position, label);
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a Rectangle object with dimensions, position, and label.
     *
     * @param dimensions A Tuple containing width and height.
     * @param position   The position of the rectangle as a Point object.
     * @param label      The label of the rectangle.
     */
    public Rectangle(Tuple<Double, Double> dimensions, Point position, String label) {
        this(dimensions.item1(), dimensions.item2(), position, label);
    }

    /**
     * Gets the dimensions of the rectangle as a Tuple.
     *
     * @return A Tuple where the first item is the width and the second item is the height.
     */
    @Override
    public Tuple<Double, Double> getDimensions() {
        return Tuple.of(width, height);
    }

    /**
     * Checks if this Rectangle object is equal to another object.
     *
     * @param o The other object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle that)) return false;
        return Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0 &&
                getCoordinates().equals(that.getCoordinates()) &&
                getLabel().equals(that.getLabel());
    }

    /**
     * Generates a hash code for this Rectangle object.
     *
     * @return The hash code.
     */
    public int hashCode() {
        return Objects.hash(width, height, getCoordinates(), getLabel());
    }
}
