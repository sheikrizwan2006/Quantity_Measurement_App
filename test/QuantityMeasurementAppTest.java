import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // ---------- SAME UNIT ADDITION ----------

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        var q1 = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(12.0, q1.add(q2).getValue(), EPS);
    }

    // ---------- CROSS UNIT ADDITION ----------

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(24.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        var q1 = new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(5.08, q1.add(q2).getValue(), 1e-2); // tolerance for cm
    }

    // ---------- COMMUTATIVITY (FIXED) ----------

    @Test
    void testAddition_Commutativity() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        // Use equals instead of raw value comparison
        assertTrue(q1.add(q2).equals(q2.add(q1)));
    }

    // ---------- EDGE CASES ----------

    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(5.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_NullSecondOperand() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    // ---------- LARGE & SMALL VALUES ----------

    @Test
    void testAddition_LargeValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2e6, q1.add(q2).getValue(), EPS);
    }

    @Test
    void testAddition_SmallValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(0.001, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.002, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(0.003, q1.add(q2).getValue(), 1e-6);
    }
}