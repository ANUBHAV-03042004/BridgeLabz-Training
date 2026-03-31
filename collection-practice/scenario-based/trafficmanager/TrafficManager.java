package trafficmanager;
import java.util.*;
public class TrafficManager {
	public static int addtoLL(CircularLinkedList cll, int sizeofLL, int capacity, Vehicle vehicle, Queue<Vehicle> queue, int queueSize) {
	    if (capacity < sizeofLL) {
	        cll.add(vehicle);
	        capacity++;
	        System.out.println(vehicle.getVehicleType() + " entered roundabout.");
	    } else {
	        if (queue.size() >= queueSize) {
	            System.out.println("Queue Overflow! " + vehicle.getVehicleType() + " cannot enter.");
	        } else {
	            queue.offer(vehicle);
	            System.out.println(vehicle.getVehicleType() + " added to queue.");
	        }
	    }
	    return capacity;
	}
	public static int removefromLL(CircularLinkedList cll, int sizeofLL, int capacity, Queue<Vehicle> queue) {
	    if (cll.head == null) return capacity;


	    cll.TakeRound();

	    Node temp = cll.head;
	    boolean removedAny = false;

	    do {
	        if (temp.vehicle.roundaboutTime <= 0) {
	            Vehicle leavingVehicle = temp.vehicle;
	            System.out.println(leavingVehicle.getVehicleType() + " has left the roundabout.");

	            cll.remove(leavingVehicle);
	            capacity--; // decrease capacity
	            removedAny = true;

	           
	            if (!queue.isEmpty()) {
	                Vehicle nextVehicle = queue.poll();
	                cll.add(nextVehicle);
	                capacity++; // increase capacity
	                System.out.println(nextVehicle.getVehicleType() + " entered from queue.");
	            }
	        }
	        temp = temp.next;
	    } while (temp != cll.head);

	    if (!removedAny) {
	        System.out.println("No vehicle left this round.");
	    }

	    return capacity;
	}

	public static void main(String[] args) {
	    int sizeofLL = 3;  
	    int capacity = 0; 
	    int queueSize = 5;  

	    CircularLinkedList cll = new CircularLinkedList();
	    Queue<Vehicle> queue = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.roundaboutTime, v2.roundaboutTime));


	    Vehicle car = new Vehicle("Car", 2);
	    Vehicle bike = new Vehicle("Bike", 3);
	    Vehicle truck = new Vehicle("Truck", 1);
	    Vehicle cycle = new Vehicle("Cycle", 4);
	    Vehicle bus = new Vehicle("Bus", 5);


	    capacity = addtoLL(cll, sizeofLL, capacity, car, queue, queueSize);
	    capacity = addtoLL(cll, sizeofLL, capacity, bike, queue, queueSize);
	    capacity = addtoLL(cll, sizeofLL, capacity, truck, queue, queueSize);
	    capacity = addtoLL(cll, sizeofLL, capacity, cycle, queue, queueSize); 
	    capacity = addtoLL(cll, sizeofLL, capacity, bus, queue, queueSize);   

	    for (int i = 1; i <= 5; i++) {
	        System.out.println("\nRound " + i);
	        capacity = removefromLL(cll, sizeofLL, capacity, queue);
	    }
	}
}
