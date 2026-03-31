package fooddeliveryorderroutingsystem;

public class Order {
    private String orderId;
    private int x, y;

    public Order(String orderId, int x, int y) {
        this.orderId = orderId;
        this.x = x;
        this.y = y;
    }

    public String getOrderId() { return orderId; }
    public int getX() { return x; }
    public int getY() { return y; }


    @Override
    public String toString() {
        return "Order{" + orderId + " at (" + x + "," + y + ")}";
    }

}