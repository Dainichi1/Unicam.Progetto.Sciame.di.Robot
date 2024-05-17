package it.unicam.cs.MarcoTorquati.api.models;

import it.unicam.cs.MarcoTorquati.api.utils.*;

import java.util.*;


/**
 * The Circle class represents a circle shape. It extends the Shape class and adds
 * a radius property to define the dimensions of the circle.
 */
public class Circle extends Shape {

    /**
     * The radius of the circle.
     */
    private final double radius;


    /**
     * Constructs a new Circle object with the given radius, position, and label.
     *
     * @param radius The radius of the circle.
     * @param position The position of the circle represented as a Point object.
     * @param label The label associated with the circle.
     */
    public Circle(double radius, Point position, String label) {
        super(position, label);
        this.radius = radius;
    }

    /**
     * Returns the dimensions of the circle as a Tuple.
     * The first item in the Tuple is the radius, and the second item is -1.0 (indicating that the shape is circular).
     *
     * @return A Tuple containing the radius as the first item and -1.0 as the second item.
     */
    @Override
    public Tuple<Double, Double> getDimensions() {
        return Tuple.of(radius, -1.0);
    }

    /**
     * Compares this Circle object to the specified object for equality.
     *
     * @param o The object to compare this Circle against.
     * @return true if the given object represents a Circle with the same dimensions, coordinates, and label; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle that = (Circle) o;
        return Double.compare(that.radius, radius) == 0 &&
                Objects.equals(getCoordinates(), that.getCoordinates()) &&
                Objects.equals(getLabel(), that.getLabel());
    }

    /**
     * Returns a hash code value for this Circle object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(radius, getCoordinates(), getLabel());
    }
}
