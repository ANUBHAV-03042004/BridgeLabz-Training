package digitalsubscriptionrenewalsystem;


class PaymentDeclinedException extends Exception {
    private final String userId;
    private final double amount;
    private final String reason;

    public PaymentDeclinedException(String userId, double amount, String reason) {
        super("Payment declined for user [" + userId + "] amount Rs." + amount + " — " + reason);
        this.userId = userId;
        this.amount = amount;
        this.reason = reason;
    }

    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public String getReason() { return reason; }
}
