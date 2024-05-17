package it.unicam.cs.MarcoTorquati.api.models;

import it.unicam.cs.MarcoTorquati.api.utils.NumericRangeChecker;


/**
 * The Direction class represents a 2D direction vector using a Point object.
 * The x and y coordinates of the direction are constrained to be between -1.0 and 1.0 inclusive.
 */
public class Direction {

    /**
     * The point representing the direction vector.
     */
    private final Point point;

    /**
     * Constructs a new Direction object with the given x and y coordinates.
     * The values of x and y must be between -1.0 and 1.0 inclusive.
     *
     * @param x The x-coordinate of the direction.
     * @param y The y-coordinate of the direction.
     * @throws IllegalArgumentException if x or y is not between -1.0 and 1.0 inclusive.
     */
    public Direction(double x, double y) {
        this.validateParameters(x,y);
        this.point = new Point(x, y);
    }

    /**
     * Constructs a new Direction object with default x and y coordinates set to 0.0.
     */
    public Direction() {
        this.point = new Point();
    }

    /**
     * Validates the x and y coordinates to ensure they are between -1.0 and 1.0 inclusive.
     *
     * @param x The x-coordinate to validate.
     * @param y The y-coordinate to validate.
     * @throws IllegalArgumentException if x or y is not between -1.0 and 1.0 inclusive.
     */
    private void validateParameters(double x, double y) {
        if(!NumericRangeChecker.DEFAULT_CHECKER.isBetween(x, -1.0,1.0)
                || !NumericRangeChecker.DEFAULT_CHECKER.isBetween(y, -1.0,1.0)) {
            throw new IllegalArgumentException("Il valore deve essere compreso tra -1 e 1");
        }
    }

    /**
     * Sets the x-coordinate of the direction.
     *
     * @param x The new x-coordinate.
     * @throws IllegalArgumentException if x is not between -1.0 and 1.0 inclusive.
     */
    public void setX(double x) {
        this.validateParameters(x, this.point.getY());
        this.point.setX(x);
    }

    /**
     * Sets the y-coordinate of the direction.
     *
     * @param y The new y-coordinate.
     * @throws IllegalArgumentException if y is not between -1.0 and 1.0 inclusive.
     */
    public void setY(double y) {
        this.validateParameters(this.point.getX(), y);
        this.point.setY(y);
    }

    /**
     * Gets the x-coordinate of the direction.
     *
     * @return The x-coordinate.
     */
    public double getX(){
        return this.point.getX();
    }

    /**
     * Gets the y-coordinate of the direction.
     *
     * @return The y-coordinate.
     */
    public double getY(){
        return this.point.getY();
    }



}
