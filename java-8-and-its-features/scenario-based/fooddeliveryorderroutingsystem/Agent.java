package fooddeliveryorderroutingsystem;

public class Agent {
    private String agentId;
    private int x, y; // coordinates
    private boolean available;

    public Agent(String agentId, int x, int y, boolean available) {
        this.agentId = agentId;
        this.x = x;
        this.y = y;
        this.available = available;
    }

    public String getAgentId() { return agentId; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Agent{" + agentId + " at (" + x + "," + y + "), Available=" + available + "}";
    }
}