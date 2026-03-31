package onlineauctionbiddingsystem;


public class OnlineAuctionBiddingSystem {

    public static void main(String[] args) {

        User alice  = new User("U001", "Alice",  UserTier.GOLD);
        User bob    = new User("U002", "Bob",    UserTier.SILVER);
        User carol  = new User("U003", "Carol",  UserTier.PLATINUM);
        User dave   = new User("U004", "Dave",   UserTier.BRONZE);
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(carol);
        System.out.println(dave);

 
        AuctionItem painting = new AuctionItem("A001", "Vintage Oil Painting",
                "18th-century landscape by unknown artist.",
                5000.00, 8000.00);
        AuctionItem laptop   = new AuctionItem("A002", "Gaming Laptop",
                "High-performance laptop, barely used.",
                30000.00, 45000.00);

        painting.openAuction();
        laptop.openAuction();

        try {
            painting.placeBid(alice, 5500.00);
            painting.placeBid(bob,   6200.00);
            painting.placeBid(carol, 7000.00);
            painting.placeBid(alice, 7800.00);   // Alice outbids Carol
            painting.placeBid(dave,  8500.00);   // Dave leads
            painting.placeBid(carol, 9100.00);   // Carol retakes lead
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        
        try {
            laptop.placeBid(bob,   31000.00);
            laptop.placeBid(dave,  35000.00);
            laptop.placeBid(alice, 40000.00);
            laptop.placeBid(bob,   48000.00);
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            painting.placeBid(dave, 7000.00); // current highest is ₹9100
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.printf(  "         userId=%s, attempted=₹%.2f, currentHighest=₹%.2f%n",
                    e.getUserId(), e.getAttemptedBid(), e.getCurrentHighest());
        }

  
        try {
            painting.placeBid(bob, 9100.00); // must strictly exceed
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        AuctionItem watch = new AuctionItem("A003", "Luxury Watch",
                "Swiss automatic, mint condition.", 10000.00, 15000.00);
        watch.openAuction();
        try {
            watch.placeBid(alice, 9999.00); // below starting price 10000
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

   
        painting.closeAuction();
        try {
            painting.placeBid(dave, 10000.00);
        } catch (InvalidBidException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

    
        painting.printLeaderboard();
        laptop.printLeaderboard();

   
        painting.printBidHistory();

        laptop.getTopBidders(3).forEach(e ->
            System.out.printf("   %-15s ₹%.2f%n", e.getKey().getName(), e.getValue()));

        System.out.println();
        laptop.closeAuction();
        watch.closeAuction(); // no bids — reserve not met
    }
}