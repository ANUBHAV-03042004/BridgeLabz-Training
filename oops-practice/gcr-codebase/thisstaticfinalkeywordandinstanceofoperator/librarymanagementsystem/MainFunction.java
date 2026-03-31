package librarymanagementsystem;

public class MainFunction {
    public static void main(String[] args) {
        // Creating Book objects
        Books book1 = new Books("ISBN001", "The Great Gatsby", "F. Scott Fitzgerald");
        Books book2 = new Books("ISBN002", "1984", "George Orwell");

        // Displaying shared library name
        Books.displayLibraryName();

        System.out.println("\n--- Book 1 Details ---");
        Books.showIfBook(book1); // Valid Book object

        System.out.println("\n--- Book 2 Details ---");
        Books.showIfBook(book2); // Valid Book object

        System.out.println("\n--- Invalid Object Check ---");
        Books.showIfBook("NotABookObject"); // Invalid object, handled by instanceof
    }
}
