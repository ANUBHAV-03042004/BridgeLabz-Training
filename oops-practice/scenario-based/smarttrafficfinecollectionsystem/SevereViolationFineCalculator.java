package smarttrafficfinecollectionsystem;

public class SevereViolationFineCalculator extends FineCalculator {
    private static final double SEVERE_SURCHARGE = 2000.0;

    @Override
    public double calculate(Violation violation, int priorCount) {
        double base = violation.getBaseFine() + SEVERE_SURCHARGE;
        if (priorCount > 0) {
            base *= 1.5;
        }
        return base;
    }

    @Override
    public String getCalculatorType() { return "SevereViolation"; }
}