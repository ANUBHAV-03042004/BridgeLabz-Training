package digitalsubscriptionrenewalsystem;

import java.time.LocalDate;

public class RenewalRecord {
    private final String    userId;
    private final String    userName;
    private final PlanType  plan;
    private final double    amountCharged;
    private final String    discountApplied;
    private final LocalDate renewedOn;
    private final boolean   success;
    private final String    note;

    public RenewalRecord(String userId, String userName, PlanType plan,
                          double amountCharged, String discountApplied,
                          LocalDate renewedOn, boolean success, String note) {
        this.userId          = userId;
        this.userName        = userName;
        this.plan            = plan;
        this.amountCharged   = amountCharged;
        this.discountApplied = discountApplied;
        this.renewedOn       = renewedOn;
        this.success         = success;
        this.note            = note;
    }

    @Override
    public String toString() {
        return String.format("  %-6s %-15s %-10s Rs.%8.2f %-22s %-10s %s | %s",
                userId, userName, plan, amountCharged, discountApplied,
                renewedOn, success ? "SUCCESS" : "FAILED", note);
    }
}
