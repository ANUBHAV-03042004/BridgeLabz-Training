package digitalsubscriptionrenewalsystem;

public class LoyaltyDiscount implements DiscountStrategy {
    private final double discountPercent;

    public LoyaltyDiscount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice - (basePrice * discountPercent / 100.0);
    }

    @Override
    public String getStrategyName() { return "Loyalty(" + discountPercent + "%)"; }
}
