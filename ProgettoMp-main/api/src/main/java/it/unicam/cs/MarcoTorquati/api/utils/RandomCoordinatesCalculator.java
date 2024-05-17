package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.Point;

import java.util.Random;

/**
 * The RandomCoordinatesCalculator interface provides a static method for generating
 * a random Point within a specified bounding box defined by two points.
 */
public interface RandomCoordinatesCalculator {

    /**
     * Generates a random Point within the bounding box defined by position1 and position2.
     * The coordinates of the generated Point will be between the coordinates of position1 and position2.
     *
     * @param position1 The first point defining one corner of the bounding box.
     * @param position2 The second point defining the opposite corner of the bounding box.
     * @return A random Point within the bounding box.
     */
    static Point calculate(Point position1, Point position2) {
        Random random = new Random();
        double x = position1.getX() + (position2.getX() - position1.getX()) * random.nextDouble();
        double y = position1.getY() + (position2.getY() - position1.getY()) * random.nextDouble();
        return new Point(x, y);
    }
}
