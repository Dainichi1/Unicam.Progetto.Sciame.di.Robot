package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.IShape;
import it.unicam.cs.MarcoTorquati.utilities.ShapeData;

/**
 * The IShapeFactory interface defines a factory method for creating shapes.
 * Implementing classes should provide their own logic for creating specific
 * types of shapes based on the input ShapeData.
 */
public interface IShapeFactory {

    /**
     * Creates an IShape object based on the given ShapeData.
     *
     * @param shapeData The data necessary for constructing the shape.
     *                  This usually includes coordinates and dimensions.
     * @return An IShape object representing the shape.
     */
    IShape createShape(ShapeData shapeData);
}
