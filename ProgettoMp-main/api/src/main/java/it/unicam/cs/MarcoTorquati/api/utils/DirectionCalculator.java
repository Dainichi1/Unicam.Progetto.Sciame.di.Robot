package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.Direction;
import it.unicam.cs.MarcoTorquati.api.models.Point;

/**
 * The DirectionCalculator interface provides utility methods to calculate the direction
 * between two points in a Cartesian coordinate system.
 */
public interface DirectionCalculator {

    /**
     * Calculates the direction from a source point to a destination point.
     *
     * @param source      The starting point in Cartesian coordinates.
     * @param destination The ending point in Cartesian coordinates.
     * @return A Direction object representing the direction from the source to the destination.
     */
    static Direction calculate(Point source, Point destination) {
        double difX = DistanceCalculator.findCoordinatesDifference(source.getX(), destination.getX()) * checkSignOf(source.getX(), destination.getX());
        double difY = DistanceCalculator.findCoordinatesDifference(source.getY(), destination.getY()) * checkSignOf(source.getY(), destination.getY());
        double distance = DistanceCalculator.calculate(source, destination);
        if(distance == 0) { distance = 1; }
        return new Direction(difX / distance, difY / distance);
    }

    /**
     * Checks the sign of the difference between source and destination coordinates.
     *
     * @param source      The coordinate of the source point.
     * @param destination The coordinate of the destination point.
     * @return 1 if source is less than destination, -1 otherwise.
     */
    private static double checkSignOf(double source, double destination) {
        if(source < destination) {
            return 1;
        } else {
            return -1;
        }
    }
}
