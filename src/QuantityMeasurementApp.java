// QuantityMeasurementApp.java

public class QuantityMeasurementApp {

    // Step 1: Enum for Units
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Step 2: Generic Quantity Class
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or type check
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion to feet
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Are equal? " + q1.equals(q2));
    }
}