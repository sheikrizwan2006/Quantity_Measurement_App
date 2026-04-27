import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-3;

    @Test
    void testEquality() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testConvertTo() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCH).getValue(), EPS);
    }

    @Test
    void testAdd_UC6() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAdd_UC7_TargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        assertEquals(0.6667, q1.add(q2, LengthUnit.YARDS).getValue(), EPS);
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET);
        });
    }
}