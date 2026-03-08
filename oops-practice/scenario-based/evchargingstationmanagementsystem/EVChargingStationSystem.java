package evchargingstationmanagementsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

class EVChargingStationSystem {

    private final Map<Integer, ChargingSlot> slots        = new TreeMap<>();
    private final Queue<Vehicle>             waitingQueue = new LinkedList<>();
    private final List<ChargingSession>      sessions     = new ArrayList<>();
    private       PricingStrategy            activePricing;

    public EVChargingStationSystem(PricingStrategy pricing) {
        this.activePricing = pricing;
    }

    public void setPricing(PricingStrategy pricing) {
        this.activePricing = pricing;
        System.out.println("[PRICING] Switched to: " + pricing.getPricingName());
    }

    public void addSlot(ChargingSlot slot) {
        slots.put(slot.getSlotId(), slot);
        System.out.println("Slot added: " + slot);
    }

    public ChargingSession chargeVehicle(Vehicle vehicle, double units)
            throws NoChargingSlotAvailableException {
        for (ChargingSlot slot : slots.values()) {
            if (slot.isFree() && slot.getAllowedType() == vehicle.getType()) {
                slot.assign(vehicle);
                ChargingSession session = new ChargingSession(vehicle, slot.getSlotId(),
                        units, activePricing);
                sessions.add(session);
                slot.release();
                System.out.println("[CHARGED] " + session);
                return session;
            }
        }
        throw new NoChargingSlotAvailableException(vehicle.getVehicleId(),
                vehicle.getType().name());
    }

    public void queueVehicle(Vehicle vehicle) {
        try {
            chargeVehicle(vehicle, 0);
        } catch (NoChargingSlotAvailableException e) {
            System.out.println("[QUEUE]  No slot for " + vehicle.getVehicleId() + " — added to queue.");
            waitingQueue.offer(vehicle);
        }
    }

    public void processQueue(double unitsPerVehicle) {
        System.out.println("\nProcessing Waiting Queue");
        while (!waitingQueue.isEmpty()) {
            Vehicle v = waitingQueue.poll();
            try {
                chargeVehicle(v, unitsPerVehicle);
            } catch (NoChargingSlotAvailableException e) {
                System.out.println("[SKIP]   Still no slot for " + v.getVehicleId());
                waitingQueue.offer(v);
                break;
            }
        }
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (ChargingSession s : sessions) total += s.getBill();
        return total;
    }

    public double getTotalUnitsDispensed() {
        double total = 0.0;
        for (ChargingSession s : sessions) total += s.getUnitsConsumed();
        return total;
    }

    public void printSlots() {
        System.out.println("\nCharging Slots");
        slots.values().forEach(s -> System.out.println("  " + s));
    }

    public void printQueue() {
        System.out.println("\nWaiting Queue (" + waitingQueue.size() + ")");
        if (waitingQueue.isEmpty()) { System.out.println("  (empty)"); return; }
        waitingQueue.forEach(v -> System.out.println("  " + v));
    }

    public void printSessionReport() {
        System.out.println("\nSession Report (" + sessions.size() + " sessions)");
        System.out.printf("  %-10s %-18s %-6s %10s %-18s %12s%n",
                "SessionID", "Owner", "Slot", "Units(kWh)", "Pricing", "Bill(Rs.)");
        for (ChargingSession s : sessions) {
            System.out.printf("  %-10s %-18s %-6d %10.2f %-18s %12.2f%n",
                    s.getSessionId(), s.getVehicle().getOwnerName(),
                    s.getSlotId(), s.getUnitsConsumed(),
                    s.getPricing().getPricingName(), s.getBill());
        }
        System.out.printf("  Total Revenue       : Rs.%.2f%n", getTotalRevenue());
        System.out.printf("  Total Units Dispensed: %.2f kWh%n", getTotalUnitsDispensed());
    }
}