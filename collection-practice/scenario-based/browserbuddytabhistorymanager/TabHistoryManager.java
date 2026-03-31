package browserbuddytabhistorymanager;

import java.util.Stack;

public class TabHistoryManager extends TabNavigation {
	Stack<Tab> stack = new Stack<>();
class Node{
	private Tab data;
	Node next,prev;
	Node(Tab data){
		this.data=data;
		this.next=null;
		this.prev=null;
	}
	public Tab getdata() {
		return data;
	}
	@Override
	public String toString() {
		return getdata().website +" "+ getdata().description;	
	}
}

Node head=null;
@Override
public void addhistory(Tab data) {
	Node history=new Node(data);
	if(head==null)
	{
		head=history;
	}
	head.next=history;
	history.prev=head;
	head=history;
	showcurrenthistory();
}
@Override
public void backhistory() {
	if(head==null)
	{
		System.out.println("No history created");
	    return;
	}
	if(head.prev==null)
	{
		System.out.println("reached end");
		return;
	}
	else {
		head=head.prev;
		showcurrenthistory();
	}
}

@Override
public void showcurrenthistory() {
	if(head==null) {
		System.out.println("No history created yet");
		return;
	}
	System.out.println(head.toString());
}
public static void main(String args[]) {
	Tab Tab1= new Tab("google.com - ","hi i am google");
	Tab Tab2= new Tab("wordpress.com - ","hi i am wordpress");
	Tab Tab3= new Tab("firefox.com - ","hi i am firefox");
	Tab Tab4= new Tab("quora.com - ","hi i am quora");
	Tab Tab5= new Tab("capg.com - ","hi i am capg");
	TabHistoryManager history= new TabHistoryManager ();
	history.addhistory(Tab1);
	history.addhistory(Tab2);
	history.addhistory(Tab3);
	history.addhistory(Tab4);
	history.backhistory();
	history.backhistory();
	
	history.forwardhistory();
	history.addhistory(Tab5);
	history.closeTab(Tab5);
	history.closeTab(Tab2);
	history.restoreTab();
	history.backhistory();
	
	
}
@Override
void forwardhistory() {
    if (head == null) {
        System.out.println("No history created");
        return;
    }
    if (head.next == null) {
        System.out.println("Reached end of history.");
    } else {
        head = head.next;
        showcurrenthistory();
    }
}
@Override
void closeTab(Tab data) {
    if (head == null) {
        System.out.println("No tabs open.");
        return;
    }

    Node temp = head;
    while (temp != null) {
        if (temp.data.equals(data)) {
            stack.push(data);

            if (temp.prev != null) {
                temp.prev.next = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }

            if (temp == head) {
                head = temp.prev != null ? temp.prev : temp.next;
            }

            System.out.println("Closed tab: " + data.website + data.description);
            return;
        }
        temp = temp.prev; 
    }
}
@Override
void restoreTab() {
    if (stack.isEmpty()) {
        System.out.println("No tabs to restore.");
        return;
    }

    Tab restored = stack.pop();
    Node newNode = new Node(restored);

    if (head == null) {
        head = newNode;
    } else {
        newNode.prev = head;
        newNode.next = head.next;
        if (head.next != null) {
            head.next.prev = newNode;
        }
        head.next = newNode;
        head = newNode; // move head to restored tab
    }

    System.out.println("Restored tab: " + restored.website + restored.description);
    showcurrenthistory();
}
}
