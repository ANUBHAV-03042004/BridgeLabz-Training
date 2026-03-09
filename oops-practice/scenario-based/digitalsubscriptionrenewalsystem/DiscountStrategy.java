package digitalsubscriptionrenewalsystem;

public interface DiscountStrategy {
    double applyDiscount(double basePrice);
    String getStrategyName();
}