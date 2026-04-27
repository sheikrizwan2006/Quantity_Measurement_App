import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-3;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET).getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH).getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(0.6667, q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS).getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(5.08, q1.add(q2, QuantityMeasurementApp.LengthUnit.CENTIMETERS).getValue(), EPS);
    }

    @Test
    void testAddition_Commutativity_WithTargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS);
        var r2 = q2.add(q1, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPS);
    }

    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(1.6667, q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS).getValue(), EPS);
    }

    @Test
    void testAddition_NullTargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }
}