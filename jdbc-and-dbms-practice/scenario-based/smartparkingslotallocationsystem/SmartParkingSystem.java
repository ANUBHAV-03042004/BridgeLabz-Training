package smartparkingslotallocationsystem;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class SmartParkingSystem {

    private final Map<Integer, ParkingSlot> slots   = new TreeMap<>();
    private final Queue<Vehicle>            waitingQueue = new LinkedList<>();

    public void addSlot(ParkingSlot slot) {
        slots.put(slot.getSlotNumber(), slot);
        System.out.println("[+] Slot added: " + slot);
    }

    public ParkingSlot allocateSlot(Vehicle vehicle) throws NoParkingSlotAvailableException {
        for (Map.Entry<Integer, ParkingSlot> entry : slots.entrySet()) {
            ParkingSlot slot = entry.getValue();
            if (slot.isFree() && slot.getAllowedType() == vehicle.getType()) {
                slot.assignVehicle(vehicle);
                System.out.println("[ALLOC] " + vehicle + " → Slot " + slot.getSlotNumber());
                return slot;
            }
        }
        throw new NoParkingSlotAvailableException(vehicle.getType().name());
    }

    public void parkVehicle(Vehicle vehicle) {
        try {
            allocateSlot(vehicle);
        } catch (NoParkingSlotAvailableException e) {
            System.out.println("[WAIT]  No slot for " + vehicle + " — added to queue.");
            waitingQueue.offer(vehicle);
        }
    }

    public void releaseSlot(int slotNumber) {
        ParkingSlot slot = slots.get(slotNumber);
        if (slot == null) {
            System.out.println("[WARN]  Slot " + slotNumber + " not found.");
            return;
        }
        if (slot.isFree()) {
            System.out.println("[WARN]  Slot " + slotNumber + " is already free.");
            return;
        }
        Vehicle released = slot.getParkedVehicle();
        slot.freeSlot();
        System.out.println("[FREE]  Slot " + slotNumber + " released by " + released);

        processWaitingQueue(slot.getAllowedType());
    }

    private void processWaitingQueue(VehicleType freedType) {
        Queue<Vehicle> remaining = new LinkedList<>();
        boolean allocated = false;

        while (!waitingQueue.isEmpty()) {
            Vehicle next = waitingQueue.poll();
            if (!allocated && next.getType() == freedType) {
                try {
                    allocateSlot(next);
                    allocated = true;
                } catch (NoParkingSlotAvailableException e) {
                    remaining.offer(next);
                }
            } else {
                remaining.offer(next);
            }
        }
        waitingQueue.addAll(remaining);
    }

    public void printAllSlots() {
        System.out.println("\nAll Parking Slots");
        slots.values().forEach(s -> System.out.println("  " + s));
    }

    public void printWaitingQueue() {
        System.out.println("\nWaiting Queue (" + waitingQueue.size() + ")");
        if (waitingQueue.isEmpty()) { System.out.println("  (empty)"); return; }
        waitingQueue.forEach(v -> System.out.println("  " + v));
    }

    public void printAvailability() {
        long freeCar  = slots.values().stream().filter(s -> s.isFree() && s.getAllowedType() == VehicleType.CAR).count();
        long freeBike = slots.values().stream().filter(s -> s.isFree() && s.getAllowedType() == VehicleType.BIKE).count();
        System.out.println("\nAvailability");
        System.out.println("  Free CAR  slots : " + freeCar);
        System.out.println("  Free BIKE slots : " + freeBike);
        System.out.println("  Waiting queue   : " + waitingQueue.size());
    }
}