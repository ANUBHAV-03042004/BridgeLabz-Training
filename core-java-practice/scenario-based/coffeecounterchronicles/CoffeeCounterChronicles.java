package coffeecounterchronicles;
import java.util.Scanner;

public class CoffeeCounterChronicles {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
while(true) {
        System.out.println("=====================================");
        System.out.println("        Welcome to Java Café       ");
        System.out.println("=====================================\n");

        // Hot Coffees
        System.out.println(" Hot Coffees");
        System.out.println(" - Espresso ................. 1");
        System.out.println(" - Cappuccino................ 2");
        System.out.println(" - Latte  ................... 3");
        System.out.println(" - Flat White ............... 4\n");

        System.out.println("==========================================");
        System.out.println(" Enter the order number to order coffee!");
        System.out.println("===========================================\n");

        String coffeetype = sc.next();
        String coffeequantity;
        int total_price = 0;

        switch (coffeetype) {
            case "1":
                System.out.println(" - Espresso");
                System.out.println("    Small  (100ml) .... ₹120.......1");
                System.out.println("    Medium (150ml) .... ₹150.......2");
                System.out.println("    Large  (200ml) .... ₹180.......3\n");

                System.out.println("Enter the number beside price to order quantity you want :");
                coffeequantity = sc.next();

                switch (coffeequantity) {
                    case "1": total_price += 120; break;
                    case "2": total_price += 150; break;
                    case "3": total_price += 180; break;
                    case "exit": break;

                }
                break;

            case "2":
                System.out.println(" - Cappuccino");
                System.out.println("    Small  (100ml) .... ₹120.......1");
                System.out.println("    Medium (150ml) .... ₹150.......2");
                System.out.println("    Large  (200ml) .... ₹180.......3\n");

                System.out.println("Enter the number beside price to order quantity you want :");
                coffeequantity = sc.next();

                switch (coffeequantity) {
                    case "1": total_price += 120; break;
                    case "2": total_price += 150; break;
                    case "3": total_price += 180; break;
                    case "exit": break;

                }
                break;

            case "3":
                System.out.println(" - Latte");
                System.out.println("    Small  (100ml) .... ₹130.......1");
                System.out.println("    Medium (150ml) .... ₹160.......2");
                System.out.println("    Large  (200ml) .... ₹190.......3\n");

                System.out.println("Enter the number beside price to order quantity you want :");
                coffeequantity = sc.next();

                switch (coffeequantity) {
                    case "1": total_price += 130; break;
                    case "2": total_price += 160; break;
                    case "3": total_price += 190; break;
                    case "exit": break;

                }
                break;

            case "4":
                System.out.println(" - Flat White");
                System.out.println("    Small  (100ml) .... ₹140.......1");
                System.out.println("    Medium (150ml) .... ₹170.......2");
                System.out.println("    Large  (200ml) .... ₹200.......3\n");

                System.out.println("Enter the number beside price to order quantity you want :");
                coffeequantity = sc.next();

                switch (coffeequantity) {
                    case "1": total_price += 140; break;
                    case "2": total_price += 170; break;
                    case "3": total_price += 200; break;
                    case "exit": break;

                }
                break;

          
        }

        System.out.println("\n=====================================");
        System.out.println(" Your total bill is: ₹" + total_price);
        System.out.println(" Thank you for visiting Java Café!");
        System.out.println("=====================================");
    }
    }
}