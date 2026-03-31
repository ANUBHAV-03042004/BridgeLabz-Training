package digitalsubscriptionrenewalsystem;

class SeasonalDiscount implements DiscountStrategy {
    private final double flatOff;

    public SeasonalDiscount(double flatOff) {
        this.flatOff = flatOff;
    }

    @Override
    public double applyDiscount(double basePrice) {
        return Math.max(0, basePrice - flatOff);
    }

    @Override
    public String getStrategyName() { return "Seasonal(flat Rs." + flatOff + ")"; }
}