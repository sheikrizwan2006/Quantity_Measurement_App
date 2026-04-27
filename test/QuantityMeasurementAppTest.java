// QuantityMeasurementAppTest.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(72.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCH),
                1e-3);
    }

    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.0;

        double converted = QuantityMeasurementApp.convert(original,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        double back = QuantityMeasurementApp.convert(converted,
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0, null,
                    QuantityMeasurementApp.LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN,
                    QuantityMeasurementApp.LengthUnit.FEET,
                    QuantityMeasurementApp.LengthUnit.INCH);
        });
    }

    @Test
    void testConversion_PrecisionTolerance() {
        double result = QuantityMeasurementApp.convert(1.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(0.393701, result, 1e-6);
    }
}