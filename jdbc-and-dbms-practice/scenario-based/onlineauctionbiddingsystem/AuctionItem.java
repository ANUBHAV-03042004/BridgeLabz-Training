package onlineauctionbiddingsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

class AuctionItem {

    private final String        itemId;
    private final String        title;
    private final String        description;
    private final double        startingPrice;
    private final double        reservePrice;   
    private       AuctionStatus status;

    private final TreeMap<User, Double> userHighestBids = new TreeMap<>();


    private final List<Bid> bidHistory = new ArrayList<>();

    public AuctionItem(String itemId, String title, String description,
                       double startingPrice, double reservePrice) {
        this.itemId        = itemId;
        this.title         = title;
        this.description   = description;
        this.startingPrice = startingPrice;
        this.reservePrice  = reservePrice;
        this.status        = AuctionStatus.UPCOMING;
    }

   
    public void openAuction() {
        status = AuctionStatus.ACTIVE;
        System.out.println("[OPEN]  Auction started: '" + title
                           + "' | Starting price: ₹" + String.format("%.2f", startingPrice));
    }

    public void closeAuction() {
        status = AuctionStatus.CLOSED;
        System.out.println("[CLOSE] Auction closed: '" + title + "'");
        announceWinner();
    }

    public Bid placeBid(User user, double amount) throws InvalidBidException {

    
        if (status != AuctionStatus.ACTIVE) {
            throw new InvalidBidException(
                "Auction '" + title + "' is not active (status=" + status + ").");
        }

        double currentHighest = getCurrentHighestBid();

     
        double floor = bidHistory.isEmpty() ? startingPrice : currentHighest;
        if (amount <= floor) {
            throw new InvalidBidException(user.getUserId(), amount, floor);
        }

   
        Bid bid = new Bid(user, amount);
        bidHistory.add(bid);
        user.recordBid(amount);

   
        userHighestBids.merge(user, amount, Math::max);

        System.out.printf("[BID]   %s bid ₹%.2f on '%s'  (previous highest: ₹%.2f)%n",
                user.getName(), amount, title, floor);

        return bid;
    }

    public double getCurrentHighestBid() {
        if (bidHistory.isEmpty()) return 0.0;
        return bidHistory.stream().mapToDouble(Bid::getAmount).max().orElse(0.0);
    }

    public Optional<User> getCurrentLeader() {
        return bidHistory.stream()
                .max(Comparator.comparingDouble(Bid::getAmount))
                .map(Bid::getBidder);
    }

    public TreeMap<User, Double> getUserHighestBids() {
        return new TreeMap<>(userHighestBids);
    }

 
    public List<Map.Entry<User, Double>> getTopBidders(int n) {
        return userHighestBids.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(n)
                .collect(java.util.stream.Collectors.toList());
    }

    public void printBidHistory() {
        System.out.println("\nBid History: '" + title);
        if (bidHistory.isEmpty()) { System.out.println("   (no bids yet)"); return; }
        for (int i = 0; i < bidHistory.size(); i++) {
            System.out.printf("   #%-3d %s%n", i + 1, bidHistory.get(i));
        }
    }

    public void printLeaderboard() {
        System.out.println("\nLeaderboard: '" + title);
        System.out.println("   (TreeMap sorted by userId, value = personal highest bid)");
        if (userHighestBids.isEmpty()) { System.out.println("   (no bids yet)"); return; }

     
        userHighestBids.forEach((user, bid) ->
            System.out.printf("   %-8s %-15s ₹%.2f%n",
                    user.getUserId(), user.getName(), bid));

        System.out.println();
        System.out.printf("   Current Highest Bid : ₹%.2f%n", getCurrentHighestBid());
        getCurrentLeader().ifPresent(u ->
            System.out.printf(" Current Leader      : %s (%s)%n",
                    u.getName(), u.getUserId()));
    }

    private void announceWinner() {
        Optional<User> winner = getCurrentLeader();
        if (winner.isEmpty()) {
            System.out.println("   No bids were placed. Item unsold.");
            return;
        }
        double winningBid = getCurrentHighestBid();
        if (winningBid < reservePrice) {
            System.out.printf("   Reserve price ₹%.2f not met. Item unsold.%n", reservePrice);
        } else {
            System.out.printf("Winner: %s | Winning Bid: ₹%.2f%n",
                    winner.get().getName(), winningBid);
        }
    }

    public String        getItemId()       { return itemId;       }
    public String        getTitle()        { return title;        }
    public double        getStartingPrice(){ return startingPrice;}
    public double        getReservePrice() { return reservePrice; }
    public AuctionStatus getStatus()       { return status;       }
    public List<Bid>     getBidHistory()   { return Collections.unmodifiableList(bidHistory); }

    @Override
    public String toString() {
        return String.format("AuctionItem{id='%s', title='%s', status=%s, highestBid=₹%.2f}",
                itemId, title, status, getCurrentHighestBid());
    }
}
