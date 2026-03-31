package smartcitytransportandservicemanagementsystem;

public interface TransportService {
 default void printServiceDetails() {
	 System.out.println("Transport Service");

}
int getFare();
}
