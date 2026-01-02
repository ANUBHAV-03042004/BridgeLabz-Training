package customerservicecalllogmanager;

import java.util.Scanner;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CallLogManager manager = new CallLogManager();

        System.out.println("Enter the phone number:");
        long phonenumber = sc.nextLong();
        sc.nextLine();

        System.out.println("Enter the message:");
        String message = sc.nextLine();

        String currenttime = LocalTime.now().toString();
        System.out.println("Current time: " + currenttime);

        CallLog call = new CallLog(phonenumber, message, currenttime);
        manager.addCall(call);

        System.out.println("\nSearching for keyword 'hello':");
        manager.searchByKeyword("hello");

        System.out.println("\nFiltering by time substring:");
        manager.filterByTime(currenttime.substring(0, 5)); 
    }
}