package evchargingstationmanagementsystem;

class StandardPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 8.0;

    @Override
    public double calculateBill(double unitsConsumed) {
        return unitsConsumed * RATE_PER_UNIT;
    }

    @Override
    public String getPricingName() { return "Standard"; }
}