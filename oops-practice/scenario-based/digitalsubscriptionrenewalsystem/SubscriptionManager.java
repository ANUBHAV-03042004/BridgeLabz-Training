package digitalsubscriptionrenewalsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SubscriptionManager {

    private final List<User>           users   = new ArrayList<>();
    private final List<RenewalRecord>  records = new ArrayList<>();
    private static final int           RENEWAL_DURATION_DAYS = 30;

    public void registerUser(User user) {
        users.add(user);
        System.out.println("Registered: " + user);
    }

    public User findUser(String userId) {
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
    }

    public void renewSubscription(String userId, DiscountStrategy strategy)
            throws PaymentDeclinedException {
        User user = findUser(userId);
        Subscription sub = user.getSubscription();

        if (sub == null) {
            System.out.println("[SKIP]   " + user.getName() + " has no subscription.");
            return;
        }

        double discountedPrice = strategy.applyDiscount(sub.getBasePrice());

        if (!user.deductPayment(discountedPrice)) {
            records.add(new RenewalRecord(userId, user.getName(), sub.getPlan(),
                    discountedPrice, strategy.getStrategyName(),
                    LocalDate.now(), false, "Insufficient balance or payment failed"));
            throw new PaymentDeclinedException(userId, discountedPrice,
                    user.isPaymentShouldFail()
                            ? "Card declined by bank"
                            : "Insufficient wallet balance (balance: Rs." + user.getWalletBalance() + ")");
        }

        sub.renew(RENEWAL_DURATION_DAYS);
        records.add(new RenewalRecord(userId, user.getName(), sub.getPlan(),
                discountedPrice, strategy.getStrategyName(),
                LocalDate.now(), true, "Renewed until " + sub.getExpiryDate()));

        System.out.println("[RENEW]  " + user.getName() + " (" + sub.getPlan() + ")"
                + " → Rs." + String.format("%.2f", discountedPrice)
                + " via " + strategy.getStrategyName()
                + " | new expiry: " + sub.getExpiryDate());
    }

    public void autoRenewAll(DiscountStrategy strategy) {
        System.out.println("\nAuto-Renewal Pass");
        for (User user : users) {
            Subscription sub = user.getSubscription();
            if (sub == null) continue;
            if (sub.getStatus() == SubscriptionStatus.CANCELLED) continue;
            if (sub.isExpired() || sub.daysUntilExpiry() <= 3) {
                try {
                    DiscountStrategy applied = sub.getRenewalCount() == 0
                            ? new FirstRenewalDiscount()
                            : strategy;
                    renewSubscription(user.getUserId(), applied);
                } catch (PaymentDeclinedException e) {
                    System.out.println("[CAUGHT] " + e.getMessage());
                    sub.setStatus(SubscriptionStatus.SUSPENDED);
                }
            }
        }
    }

    public List<User> getExpiredUsers() {
        return users.stream()
                .filter(u -> u.getSubscription() != null && u.getSubscription().isExpired())
                .collect(Collectors.toList());
    }

    public List<User> getActiveUsers() {
        return users.stream()
                .filter(u -> u.getSubscription() != null
                        && u.getSubscription().getStatus() == SubscriptionStatus.ACTIVE
                        && !u.getSubscription().isExpired())
                .collect(Collectors.toList());
    }

    public Map<PlanType, Long> countByPlan() {
        return users.stream()
                .filter(u -> u.getSubscription() != null)
                .collect(Collectors.groupingBy(
                        u -> u.getSubscription().getPlan(), Collectors.counting()));
    }

    public void printAllUsers() {
        System.out.println("\nAll Users");
        System.out.printf("  %-6s %-15s %-10s %-12s %8s %12s%n",
                "ID", "Name", "Plan", "Status", "Expiry", "Balance(Rs.)");
        for (User u : users) {
            Subscription sub = u.getSubscription();
            String plan   = sub != null ? sub.getPlan().name()   : "N/A";
            String status = sub != null ? sub.getStatus().name() : "N/A";
            String expiry = sub != null ? sub.getExpiryDate().toString() : "N/A";
            System.out.printf("  %-6s %-15s %-10s %-12s %8s %12.2f%n",
                    u.getUserId(), u.getName(), plan, status, expiry, u.getWalletBalance());
        }
    }

    public void printRenewalReport() {
        System.out.println("\nRenewal Report (" + records.size() + " records)");
        System.out.printf("  %-6s %-15s %-10s %10s %-22s %-10s %s%n",
                "ID", "Name", "Plan", "Amount", "Discount", "Date", "Result | Note");
        records.forEach(System.out::println);

        double totalRevenue = records.stream()
                .filter(r -> r.toString().contains("SUCCESS"))
                .mapToDouble(r -> 0)
                .sum();

        long success = records.stream().filter(r -> r.toString().contains("SUCCESS")).count();
        long failed  = records.stream().filter(r -> r.toString().contains("FAILED")).count();
        System.out.println("  Success: " + success + "  |  Failed: " + failed);
    }

    public void printPlanDistribution() {
        System.out.println("\nPlan Distribution");
        countByPlan().forEach((plan, count) ->
            System.out.printf("  %-12s : %d user(s)%n", plan, count));
    }
}
