package onlineauctionbiddingsystem;


class User implements Comparable<User> {

    private final String   userId;
    private final String   name;
    private final UserTier tier;
    private       double   totalBidAmount; 
    private       int      totalBidsPlaced;

    public User(String userId, String name, UserTier tier) {
        this.userId         = userId;
        this.name           = name;
        this.tier           = tier;
        this.totalBidAmount = 0.0;
        this.totalBidsPlaced = 0;
    }

    public String   getUserId()          { return userId;          }
    public String   getName()            { return name;            }
    public UserTier getTier()            { return tier;            }
    public double   getTotalBidAmount()  { return totalBidAmount;  }
    public int      getTotalBidsPlaced() { return totalBidsPlaced; }

    public void recordBid(double amount) {
        totalBidAmount += amount;
        totalBidsPlaced++;
    }

    @Override
    public int compareTo(User other) {
        return this.userId.compareTo(other.userId);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        return userId.equals(((User) o).userId);
    }

    @Override
    public int hashCode() { return userId.hashCode(); }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', tier=%s}", userId, name, tier);
    }
}