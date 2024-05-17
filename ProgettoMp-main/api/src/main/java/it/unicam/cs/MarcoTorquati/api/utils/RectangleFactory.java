package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.IShape;
import it.unicam.cs.MarcoTorquati.api.models.Point;
import it.unicam.cs.MarcoTorquati.api.models.Rectangle;
import it.unicam.cs.MarcoTorquati.utilities.ShapeData;

/**
 * The RectangleFactory class is an implementation of the IShapeFactory interface
 * and is responsible for creating Rectangle objects.
 */
public class RectangleFactory implements IShapeFactory{

    /**
     * Creates a Rectangle object based on the provided ShapeData.
     *
     * @param shapeData An object containing the arguments and label for creating the shape.
     * @return An IShape object, which in this case is a Rectangle.
     */
    @Override
    public IShape createShape(ShapeData shapeData) {
        double x = shapeData.args()[0];
        double y = shapeData.args()[1];
        double width = shapeData.args()[2];
        double height = shapeData.args()[3];
        return new Rectangle(Tuple.of(width, height), new Point(x, y), shapeData.label());
    }
}
