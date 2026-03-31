package evchargingstationmanagementsystem;

class PeakHourPricing implements PricingStrategy {
    private static final double RATE_PER_UNIT = 14.0;
    private static final double SURCHARGE     = 20.0;

    @Override
    public double calculateBill(double unitsConsumed) {
        return (unitsConsumed * RATE_PER_UNIT) + SURCHARGE;
    }

    @Override
    public String getPricingName() { return "PeakHour"; }
}