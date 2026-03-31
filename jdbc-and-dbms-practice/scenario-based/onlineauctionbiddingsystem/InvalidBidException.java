package onlineauctionbiddingsystem;

class InvalidBidException extends Exception {

    private final String userId;
    private final double attemptedBid;
    private final double currentHighest;

    public InvalidBidException(String userId, double attemptedBid, double currentHighest) {
        super(String.format(
            "User [%s] bid ₹%.2f is invalid — must exceed current highest bid of ₹%.2f.",
            userId, attemptedBid, currentHighest));
        this.userId         = userId;
        this.attemptedBid   = attemptedBid;
        this.currentHighest = currentHighest;
    }

    public InvalidBidException(String message) {
        super(message);
        this.userId         = "";
        this.attemptedBid   = 0;
        this.currentHighest = 0;
    }

    public String getUserId()         { return userId;         }
    public double getAttemptedBid()   { return attemptedBid;   }
    public double getCurrentHighest() { return currentHighest; }
}

