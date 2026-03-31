package smarttrafficfinecollectionsystem;

abstract class FineCalculator {
    public abstract double calculate(Violation violation, int priorCount);
    public abstract String getCalculatorType();
}
