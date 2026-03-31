package validateanipaddress;

public class ValidateAnIPAddress {
    static boolean ValidateIP(String regex, String text) {
        return text.matches(regex);
    }

    public static void main(String args[]) {
        // Regex for IPv4 validation
        String regex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        String text = "192.168.43.46";

        System.out.println(ValidateIP(regex, text)); // true
    }
}