package digitalsubscriptionrenewalsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Subscription {
    private final PlanType   plan;
    private final double     basePrice;
    private       LocalDate  startDate;
    private       LocalDate  expiryDate;
    private       SubscriptionStatus status;
    private       int        renewalCount;

    public Subscription(PlanType plan, double basePrice, LocalDate startDate, int durationDays) {
        this.plan         = plan;
        this.basePrice    = basePrice;
        this.startDate    = startDate;
        this.expiryDate   = startDate.plusDays(durationDays);
        this.status       = SubscriptionStatus.ACTIVE;
        this.renewalCount = 0;
    }

    public void renew(int durationDays) {
        this.expiryDate   = LocalDate.now().plusDays(durationDays);
        this.status       = SubscriptionStatus.ACTIVE;
        this.renewalCount++;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public long daysUntilExpiry() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }

    public PlanType            getPlan()         { return plan;         }
    public double              getBasePrice()    { return basePrice;    }
    public LocalDate           getStartDate()    { return startDate;    }
    public LocalDate           getExpiryDate()   { return expiryDate;   }
    public SubscriptionStatus  getStatus()       { return status;       }
    public int                 getRenewalCount() { return renewalCount; }
    public void                setStatus(SubscriptionStatus s) { this.status = s; }

    @Override
    public String toString() {
        return "Subscription[" + plan + ", expires=" + expiryDate
                + ", status=" + status + ", renewals=" + renewalCount + "]";
    }
}
