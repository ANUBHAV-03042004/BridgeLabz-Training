package smarttrafficfinecollectionsystem;

public class RepeatOffenderFineCalculator extends FineCalculator {
    private static final double REPEAT_MULTIPLIER = 1.5;
    private static final double HABITUAL_MULTIPLIER = 2.0;
    private static final int    HABITUAL_THRESHOLD  = 5;

    @Override
    public double calculate(Violation violation, int priorCount) {
        double base = violation.getBaseFine();
        if (priorCount >= HABITUAL_THRESHOLD) {
            return base * HABITUAL_MULTIPLIER;
        }
        return base * REPEAT_MULTIPLIER;
    }

    @Override
    public String getCalculatorType() { return "RepeatOffender"; }
}