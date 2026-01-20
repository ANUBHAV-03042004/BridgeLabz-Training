package trafficmanager;
class Node{
	Vehicle vehicle;
	Node next;
	Node(Vehicle vehicle){
		this.vehicle=vehicle;
	}
}
public class CircularLinkedList {

Node head=null;

public void add(Vehicle vehicle) {
	Node newNode = new Node(vehicle);
	if(head==null) {
		 head = newNode;
		   head.next = head; 
		   return ;
	}
	Node temp=head;
	while(temp.next!=head) {
		temp=temp.next;
	}
	temp.next= newNode;
	newNode.next=head;
}
public void remove(Vehicle vehicle) {
	if(head == null) return ;
	if(head.vehicle == vehicle) {
		Node temp1=head;
		while(temp1.next!=head) {
			temp1=temp1.next;
		}
		temp1.next=head.next;
		head=head.next;
		return ; 
	}
	Node temp=head;
	while(temp.next!=head && temp.next.vehicle != vehicle) {
		temp=temp.next;
	}
	if (temp.next.vehicle == vehicle) {
        temp.next = temp.next.next;
    }

}
public void TakeRound() {
	 if (head == null) return;
	    Node temp = head;
	    do {
	        temp.vehicle.roundaboutTime--;
	        temp = temp.next;
	    } while (temp != head);

}

}
