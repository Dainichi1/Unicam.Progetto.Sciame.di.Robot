package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.Point;

/**
 * The DistanceCalculator interface provides utility methods to calculate
 * the distance between two points in a Cartesian coordinate system.
 */
public interface DistanceCalculator {

    /**
     * Calculates the Euclidean distance between a source point and a destination point.
     *
     * @param source      The starting point in Cartesian coordinates.
     * @param destination The ending point in Cartesian coordinates.
     * @return The Euclidean distance between the source and destination points.
     */
    static double calculate(Point source, Point destination) {
        double difX = findCoordinatesDifference(source.getX(), destination.getX());
        double difY = findCoordinatesDifference(source.getY(), destination.getY());
        return Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));
    }

    /**
     * Finds the absolute difference between two coordinate values.
     *
     * @param source      The coordinate of the source point.
     * @param destination The coordinate of the destination point.
     * @return The absolute difference between the source and destination coordinates.
     */
    static double findCoordinatesDifference(double source, double destination) {
        return Math.abs(destination - source);
    }
}
