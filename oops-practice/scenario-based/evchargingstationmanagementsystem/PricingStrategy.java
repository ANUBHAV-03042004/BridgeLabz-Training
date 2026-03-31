package evchargingstationmanagementsystem;

interface PricingStrategy {
    double calculateBill(double unitsConsumed);
    String getPricingName();
}