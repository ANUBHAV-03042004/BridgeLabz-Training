package disasterreliefresourceallocationsystem;


public class InsufficientResourceException extends Exception {
    private final String item;
    private final int    requested;
    private final int    available;

    public InsufficientResourceException(String item, int requested, int available) {
        super("Insufficient stock for [" + item + "] — requested: "
                + requested + ", available: " + available);
        this.item      = item;
        this.requested = requested;
        this.available = available;
    }

    public String getItem()      { return item;      }
    public int    getRequested() { return requested; }
    public int    getAvailable() { return available; }
}

