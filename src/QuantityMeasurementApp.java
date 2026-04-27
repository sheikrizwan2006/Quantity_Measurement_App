// LengthUnit (TOP LEVEL)
enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toFeet(double value) {
        return value * factor;
    }

    public double fromFeet(double value) {
        return value / factor;
    }
}


// MAIN CLASS
public class QuantityMeasurementApp {

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException();
            if (!Double.isFinite(value)) throw new IllegalArgumentException();

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // UC5
        public QuantityLength convertTo(LengthUnit target) {
            double base = toFeet();
            double result = target.fromFeet(base);
            return new QuantityLength(result, target);
        }

        // UC6
        public QuantityLength add(QuantityLength other) {
            double sum = this.toFeet() + other.toFeet();
            double result = unit.fromFeet(sum);
            return new QuantityLength(result, unit);
        }

        // UC7
        public QuantityLength add(QuantityLength other, LengthUnit target) {
            double sum = this.toFeet() + other.toFeet();
            double result = target.fromFeet(sum);
            return new QuantityLength(result, target);
        }

        // Equality
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }
}