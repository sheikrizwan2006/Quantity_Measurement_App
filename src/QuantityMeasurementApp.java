// ---------- INTERFACE ----------
interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}


// ---------- LENGTH UNIT ----------
enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}


// ---------- WEIGHT UNIT ----------
enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}


// ---------- MAIN CLASS ----------
public class QuantityMeasurementApp {

    // ---------- GENERIC QUANTITY ----------
    public static class Quantity<U extends IMeasurable> {

        private final double value;
        private final U unit;

        private static final double EPS = 1e-2;

        public Quantity(double value, U unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        // Conversion
        public Quantity<U> convertTo(U targetUnit) {
            double base = toBase();
            double result = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(result, targetUnit);
        }

        // Addition (same unit result)
        public Quantity<U> add(Quantity<U> other) {
            double sum = this.toBase() + other.toBase();
            double result = unit.convertFromBaseUnit(sum);
            return new Quantity<>(result, unit);
        }

        // Addition (target unit)
        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            double sum = this.toBase() + other.toBase();
            double result = targetUnit.convertFromBaseUnit(sum);
            return new Quantity<>(result, targetUnit);
        }

        // Equality
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity<?> other = (Quantity<?>) obj;

            // prevent comparing length with weight
            if (!this.unit.getClass().equals(other.unit.getClass())) return false;

            return Math.abs(this.toBase() - other.toBase()) <= EPS;
        }
    }

    // ---------- MAIN METHOD ----------
    public static void main(String[] args) {

        // Length Example
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

        System.out.println("Length Equal: " + l1.equals(l2));
        System.out.println("Length Convert (ft -> inch): " + l1.convertTo(LengthUnit.INCH).getValue());
        System.out.println("Length Add: " + l1.add(l2).getValue());

        // Weight Example
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equal: " + w1.equals(w2));
        System.out.println("Weight Convert (kg -> gram): " + w1.convertTo(WeightUnit.GRAM).getValue());
        System.out.println("Weight Add: " + w1.add(w2).getValue());
    }
}