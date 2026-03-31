package smarttrafficfinecollectionsystem;

public class StandardFineCalculator extends FineCalculator {
    @Override
    public double calculate(Violation violation, int priorCount) {
        return violation.getBaseFine();
    }

    @Override
    public String getCalculatorType() { return "Standard"; }
}