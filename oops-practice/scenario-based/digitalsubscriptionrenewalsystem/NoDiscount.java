package digitalsubscriptionrenewalsystem;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice) { return basePrice; }

    @Override
    public String getStrategyName() { return "NoDiscount"; }
}
