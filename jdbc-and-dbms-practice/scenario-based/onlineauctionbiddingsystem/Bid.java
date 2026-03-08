package onlineauctionbiddingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Bid implements Comparable<Bid> {

    private final String        bidId;
    private final User          bidder;
    private final double        amount;
    private final LocalDateTime timestamp;

    private static int counter = 100;

    public Bid(User bidder, double amount) {
        this.bidId     = "BID-" + (++counter);
        this.bidder    = bidder;
        this.amount    = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String        getBidId()    { return bidId;    }
    public User          getBidder()   { return bidder;   }
    public double        getAmount()   { return amount;   }
    public LocalDateTime getTimestamp(){ return timestamp;}

 
    @Override
    public int compareTo(Bid other) {
        return Double.compare(this.amount, other.amount);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("Bid{id='%s', bidder='%s', amount=₹%.2f, time=%s}",
                bidId, bidder.getName(), amount, timestamp.format(fmt));
    }
}