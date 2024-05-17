package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.Circle;
import it.unicam.cs.MarcoTorquati.api.models.IShape;
import it.unicam.cs.MarcoTorquati.api.models.Point;
import it.unicam.cs.MarcoTorquati.utilities.ShapeData;

/**
 * The CircleFactory class is an implementation of the IShapeFactory interface.
 * It is responsible for creating Circle objects based on provided ShapeData.
 */
public class CircleFactory implements IShapeFactory{

    /**
     * Creates a Circle shape based on the provided ShapeData.
     *
     * @param shapeData The ShapeData object containing the necessary parameters to create a Circle.
     * @return A new Circle object.
     */
    @Override
    public IShape createShape(ShapeData shapeData) {
        double x = shapeData.args()[0];
        double y = shapeData.args()[1];
        double radius = shapeData.args()[2];
        return new Circle(radius, new Point(x, y), shapeData.label());
    }
}
