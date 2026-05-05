import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-2;

    // ---------- LENGTH TESTS ----------

    @Test
    void testLengthEquality() {
        var q1 = new QuantityMeasurementApp.Quantity<>(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity<>(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testLengthConversion() {
        var q = new QuantityMeasurementApp.Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCH).getValue(), EPS);
    }

    @Test
    void testLengthAddition() {
        var q1 = new QuantityMeasurementApp.Quantity<>(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity<>(12.0, LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

    // ---------- WEIGHT TESTS ----------

    @Test
    void testWeightEquality() {
        var q1 = new QuantityMeasurementApp.Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testWeightConversion() {
        var q = new QuantityMeasurementApp.Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM).getValue(), EPS);
    }

    @Test
    void testWeightAddition() {
        var q1 = new QuantityMeasurementApp.Quantity<>(1.0, WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

    // ---------- CROSS CATEGORY ----------

    @Test
    void testCrossCategoryComparison() {
        var length = new QuantityMeasurementApp.Quantity<>(1.0, LengthUnit.FEET);
        var weight = new QuantityMeasurementApp.Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // ---------- EDGE CASES ----------

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Quantity<>(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }
}