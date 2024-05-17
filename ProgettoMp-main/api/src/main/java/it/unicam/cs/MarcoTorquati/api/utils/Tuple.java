package it.unicam.cs.MarcoTorquati.api.utils;

/**
 * A generic Tuple class that holds a pair of objects.
 *
 * @param <L> The type of the first element in the tuple.
 * @param <R> The type of the second element in the tuple.
 */
public record Tuple<L, R>(L item1, R item2) {

    /**
     * Retrieves the first item in the tuple.
     *
     * @return The first item of type L.
     */
    @Override
    public L item1() {
        return item1;
    }

    /**
     * Retrieves the second item in the tuple.
     *
     * @return The second item of type R.
     */
    @Override
    public R item2() {
        return item2;
    }

    /**
     * Converts the tuple into a string representation.
     *
     * @return A string representation of the tuple.
     */
    @Override
    public String toString() {
        return "( " + this.item1 + ", " + this.item2 + ")";
    }

    /**
     * Factory method to create a new Tuple object.
     *
     * @param <L> Type of the first item.
     * @param <R> Type of the second item.
     * @param item1 The first item of the tuple.
     * @param item2 The second item of the tuple.
     * @return A new Tuple object containing the provided items.
     */
    public static <L, R> Tuple<L, R> of(L item1, R item2) {
        return new Tuple<>(item1, item2);
    }
}
