package it.unicam.cs.MarcoTorquati.api.utils;

/**
 * The NumericRangeChecker interface provides a method for checking if a
 * numeric value falls within a given range. The interface is generic and
 * can be specialized for any numeric type that is comparable.
 *
 * @param <T> The type of number to check. Must implement both Number and Comparable.
 */
@FunctionalInterface
public interface NumericRangeChecker<T extends Number & Comparable<T>> {

    /**
     * Checks if the given value is between minValue and maxValue, inclusive.
     *
     * @param value The value to check.
     * @param minValue The lower bound of the range.
     * @param maxValue The upper bound of the range.
     * @return true if value is within the range [minValue, maxValue], false otherwise.
     */
    boolean isBetween(T value, T minValue, T maxValue);

    /**
     * A default implementation of NumericRangeChecker for double values.
     */
    NumericRangeChecker<Double> DEFAULT_CHECKER = (value, minValue, maxValue) -> {
        if(minValue.compareTo(maxValue) > 0){
            throw new IllegalArgumentException("Il range non e' valido");
        }
        return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
    };
}
