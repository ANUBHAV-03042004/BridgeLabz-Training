package digitalsubscriptionrenewalsystem;

public class User {
    private final String        userId;
    private final String        name;
    private final String        email;
    private final PaymentMethod paymentMethod;
    private       double        walletBalance;
    private       Subscription  subscription;
    private       boolean       paymentShouldFail;

    public User(String userId, String name, String email,
                PaymentMethod paymentMethod, double walletBalance) {
        this.userId           = userId;
        this.name             = name;
        this.email            = email;
        this.paymentMethod    = paymentMethod;
        this.walletBalance    = walletBalance;
        this.paymentShouldFail = false;
    }

    public void assignSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void setPaymentShouldFail(boolean fail) {
        this.paymentShouldFail = fail;
    }

    public boolean deductPayment(double amount) {
        if (paymentShouldFail) return false;
        if (walletBalance < amount) return false;
        walletBalance -= amount;
        return true;
    }

    public String        getUserId()           { return userId;           }
    public String        getName()             { return name;             }
    public String        getEmail()            { return email;            }
    public PaymentMethod getPaymentMethod()    { return paymentMethod;    }
    public double        getWalletBalance()    { return walletBalance;    }
    public Subscription  getSubscription()     { return subscription;     }
    public boolean       isPaymentShouldFail() { return paymentShouldFail;}

    @Override
    public String toString() {
        return "User[" + userId + ", " + name + ", " + email + ", payment=" + paymentMethod + "]";
    }
}
