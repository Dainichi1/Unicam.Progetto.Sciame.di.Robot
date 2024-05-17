package it.unicam.cs.MarcoTorquati.api;

import it.unicam.cs.MarcoTorquati.api.utils.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {

    @Test
    void testTupleCreation() {
        Tuple<Integer, Double> tuple = Tuple.of(1, 2.5);

        assertEquals(1, tuple.item1());
        assertEquals(2.5, tuple.item2());

        assertEquals("( 1, 2.5)", tuple.toString());
    }

    @Test
    void testTupleEquality() {
        Tuple<Integer, Double> tuple1 = Tuple.of(1, 2.5);
        Tuple<Integer, Double> tuple2 = Tuple.of(1, 2.5);
        Tuple<Integer, Double> tuple3 = Tuple.of(2, 2.5);

        assertTrue(tuple1.equals(tuple2));

        assertFalse(tuple1.equals(tuple3));
    }
}
