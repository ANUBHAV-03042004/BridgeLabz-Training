package evchargingstationmanagementsystem;


class NightPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 5.5;

    @Override
    public double calculateBill(double unitsConsumed) {
        return unitsConsumed * RATE_PER_UNIT;
    }

    @Override
    public String getPricingName() { return "Night(Discounted)"; }
}