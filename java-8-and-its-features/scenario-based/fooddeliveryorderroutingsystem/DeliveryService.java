package fooddeliveryorderroutingsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

public class DeliveryService {
	List<Agent> agents = new ArrayList<>();
	Queue<Order> orders = new LinkedList<>();
	Map<Order,Agent> activeDeliveries = new HashMap<>();
	public void addAgent(Agent agent) {
	   agents.add(agent);
	   System.out.println("Agent Added Successfully !");
	}
	public void addOrder(Order order) {
		orders.add(order);
	}
	public void assignOrder() throws NoAgentAvailableException
	{
		if(orders.isEmpty()) return ; 
		
		Order order = orders.poll();
		Agent nearestAgent = agents.stream().filter(Agent::isAvailable).min(Comparator.comparingDouble(agent->
			calculateDistance(order, agent)
		)).orElseThrow(()-> new NoAgentAvailableException("no agent is free"));
		nearestAgent.setAvailable(true);
		activeDeliveries.put(order, nearestAgent);
		System.out.println("Assigned "+ order +" to "+nearestAgent);
		
	}
	public void cancelOrder(Order order) {
		Agent agent = activeDeliveries.remove(order);
		if(agent !=null) agent.setAvailable(true);
        System.out.println("Cancelled " + order + ", Agent " + agent.getAgentId() + " is now free.");
	}
	public void viewActiveDeliveries() {
		 activeDeliveries.forEach((order, agent) ->
	        System.out.println(order + " -> " + agent));
	}
	  public static double calculateDistance(Order order, Agent agent) {
	        int dx = order.getX() - agent.getX();
	        int dy = order.getY() - agent.getY();
	        return Math.sqrt(dx * dx + dy * dy); // Euclidean distance
	    }

	    public static void main(String[] args) {
	        DeliveryService service = new DeliveryService();
	        service.addAgent(new Agent("A1", 0, 0, true));
	        service.addAgent(new Agent("A2", 10, 10, true));

	        service.addOrder(new Order("O1", 2, 3));
	        service.addOrder(new Order("O2", 9, 9));

	        try {
	            service.assignOrder();
	            service.assignOrder();
	            service.viewActiveDeliveries();
	        } catch (NoAgentAvailableException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	}
