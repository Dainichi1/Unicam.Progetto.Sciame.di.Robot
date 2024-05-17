package it.unicam.cs.MarcoTorquati.api.utils;

import it.unicam.cs.MarcoTorquati.api.models.IShape;
import it.unicam.cs.MarcoTorquati.utilities.ShapeData;

import java.util.HashMap;
import java.util.Map;

/**
 * The ShapeParser class is responsible for parsing ShapeData objects
 * and creating corresponding IShape objects using appropriate factories.
 */
public class ShapeParser {

    /**
     * A mapping of shape types (like "CIRCLE", "RECTANGLE") to their corresponding factories.
     */
    private final Map<String, IShapeFactory> factoryMap;

    /**
     * Constructor initializes the factory mapping with built-in shape types.
     */
    public ShapeParser() {
        factoryMap = new HashMap<>();
        factoryMap.put("CIRCLE", new CircleFactory());
        factoryMap.put("RECTANGLE", new RectangleFactory());
    }

    /**
     * Parses the given ShapeData object to create an IShape object.
     *
     * @param shapeData The ShapeData object containing the information to create a shape.
     * @return An IShape object corresponding to the ShapeData.
     * @throws IllegalArgumentException if the shape type in ShapeData is not recognized.
     */
    public IShape parseFromShapeData(ShapeData shapeData) {
        String shapeType = shapeData.shape();
        if (!factoryMap.containsKey(shapeType)) {
            throw new IllegalArgumentException("Invalid shape: " + shapeType);
        }
        IShapeFactory factory = factoryMap.get(shapeType);
        return factory.createShape(shapeData);
    }
}
