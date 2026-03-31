package dateformatutility;

	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;

	interface DateFormat {
	    // Static method to format date with a given pattern
	    static String formatDate(LocalDateTime date, String pattern) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	        return date.format(formatter);
	    }
	}
	public class DateFormatUtility {
	    public static void main(String[] args) {
	        LocalDateTime now = LocalDateTime.now();

	        // Using the static interface method
	        String format1 = DateFormat.formatDate(now, "dd/MM/yyyy");
	        String format2 = DateFormat.formatDate(now, "MM-dd-yyyy");
	        String format3 = DateFormat.formatDate(now, "yyyy-MM-dd HH:mm");

	        System.out.println("Invoice Date (Format 1): " + format1);
	        System.out.println("Invoice Date (Format 2): " + format2);
	        System.out.println("Invoice Date (Format 3): " + format3);
	    }
	}