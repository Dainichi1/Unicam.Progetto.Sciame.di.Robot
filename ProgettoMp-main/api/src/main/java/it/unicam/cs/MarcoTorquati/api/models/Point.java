package it.unicam.cs.MarcoTorquati.api.models;

import java.util.Objects;

/**
 * The Point class represents a point in a 2D coordinate system.
 */
public class Point {

    private double x;

    private double y;

    /**
     * Constructs a Point object with specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor that initializes the point at the origin (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x The new x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y The new y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Checks if this Point object is equal to another object.
     *
     * @param obj The other object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    /**
     * Generates a hash code for this Point object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a string representation of the Point object.
     *
     * @return A string in the format "{ x: X; y: Y }".
     */
    @Override
    public String toString() {
        return "{ x: " + this.x + "; y: " + this.y + "}";
    }
}
