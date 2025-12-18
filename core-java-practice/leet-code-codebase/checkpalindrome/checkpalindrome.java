package checkpalindrome;

// Program to check whether a number is palindrome or not
public class CheckPalindrome {

    // Main function to execute program
    public static void main(String[] args) {

        int originalNumber = 131;
        int numberToCheck = originalNumber;
        int reversedNumber = 0;

        while (numberToCheck > 0) {
            int digit = numberToCheck % 10;
            reversedNumber = reversedNumber * 10 + digit;
            numberToCheck = numberToCheck / 10;
        }

        if (originalNumber == reversedNumber) {
            System.out.println(originalNumber + " is a Palindrome number");
        } else {
            System.out.println(originalNumber + " is NOT a Palindrome number");
        }
    }
}
