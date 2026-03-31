package digitalsubscriptionrenewalsystem;

import java.time.LocalDate;

public class DigitalSubscriptionRenewalSystem {

    public static void main(String[] args) {


        SubscriptionManager manager = new SubscriptionManager();

      
        User u1 = new User("U001", "Alice",   "alice@mail.com",  PaymentMethod.CREDIT_CARD, 500.0);
        User u2 = new User("U002", "Bob",     "bob@mail.com",    PaymentMethod.UPI,         200.0);
        User u3 = new User("U003", "Carol",   "carol@mail.com",  PaymentMethod.DEBIT_CARD,  1000.0);
        User u4 = new User("U004", "Dave",    "dave@mail.com",   PaymentMethod.WALLET,      50.0);
        User u5 = new User("U005", "Eve",     "eve@mail.com",    PaymentMethod.CREDIT_CARD, 800.0);
        User u6 = new User("U006", "Frank",   "frank@mail.com",  PaymentMethod.UPI,         300.0);

        u1.assignSubscription(new Subscription(PlanType.PREMIUM,  499.0, LocalDate.now().minusDays(28), 30));
        u2.assignSubscription(new Subscription(PlanType.BASIC,    99.0,  LocalDate.now().minusDays(29), 30));
        u3.assignSubscription(new Subscription(PlanType.STANDARD, 199.0, LocalDate.now().minusDays(25), 30));
        u4.assignSubscription(new Subscription(PlanType.BASIC,    99.0,  LocalDate.now().minusDays(31), 30));
        u5.assignSubscription(new Subscription(PlanType.PREMIUM,  499.0, LocalDate.now().minusDays(27), 30));
        u6.assignSubscription(new Subscription(PlanType.STANDARD, 199.0, LocalDate.now().minusDays(30), 30));

        u4.setPaymentShouldFail(false);

        manager.registerUser(u1);
        manager.registerUser(u2);
        manager.registerUser(u3);
        manager.registerUser(u4);
        manager.registerUser(u5);
        manager.registerUser(u6);

        manager.printAllUsers();

        System.out.println("\nExpired Users (before renewal)");
        manager.getExpiredUsers().forEach(u -> System.out.println("  " + u.getName()
                + " — expired: " + u.getSubscription().getExpiryDate()));

        System.out.println("\nManual Renewal with Loyalty Discount");
        try {
            manager.renewSubscription("U003", new LoyaltyDiscount(15.0));
        } catch (PaymentDeclinedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nManual Renewal with Seasonal Discount");
        try {
            manager.renewSubscription("U005", new SeasonalDiscount(50.0));
        } catch (PaymentDeclinedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        System.out.println("\nTriggering PaymentDeclinedException (low balance)");
        try {
            manager.renewSubscription("U004", new NoDiscount());
        } catch (PaymentDeclinedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("         userId=" + e.getUserId()
                    + ", amount=Rs." + e.getAmount()
                    + ", reason=" + e.getReason());
        }

        System.out.println("\nTriggering PaymentDeclinedException (card declined)");
        u6.setPaymentShouldFail(true);
        try {
            manager.renewSubscription("U006", new LoyaltyDiscount(10.0));
        } catch (PaymentDeclinedException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        manager.autoRenewAll(new LoyaltyDiscount(10.0));

        manager.printAllUsers();

        System.out.println("\nActive Users");
        manager.getActiveUsers().forEach(u -> System.out.println("  " + u.getName()
                + " [" + u.getSubscription().getPlan() + "] expires: "
                + u.getSubscription().getExpiryDate()));

        manager.printPlanDistribution();
        manager.printRenewalReport();
    }
}