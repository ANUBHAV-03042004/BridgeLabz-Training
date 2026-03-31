package digitalsubscriptionrenewalsystem;

public class FirstRenewalDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double basePrice) {
        return basePrice * 0.5;
    }

    @Override
    public String getStrategyName() { return "FirstRenewal(50% off)"; }
}
