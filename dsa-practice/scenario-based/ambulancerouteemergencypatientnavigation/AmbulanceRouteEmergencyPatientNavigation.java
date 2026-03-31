package ambulancerouteemergencypatientnavigation;

class CircularLinkedList {
    String department;
    String data;
    CircularLinkedList next;

    public CircularLinkedList(String department, String data) {
        this.department = department;
        this.data = data;
    }
}

class AmbulanceRouteEmergencyPatientNavigation {
    // RouteEmergencyPatientNavigation
    CircularLinkedList head = null;

    public void addroute(String department, String data) {
        CircularLinkedList newNode = new CircularLinkedList(department, data);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            CircularLinkedList temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    public String bedAvailable() {
        if (head == null) {
            return "No routes available";
        }

        CircularLinkedList temp = head;

        do {
            if (!temp.data.equalsIgnoreCase("Full")) {
                temp.data = "Full";
                head = temp.next; // move head to next route
                return "Bed available at : " + temp.department;
            }
            temp = temp.next;
        } while (temp != head);

        return "No beds available in any department";
    }

}
