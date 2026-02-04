package smartuniversitylibrarymanagementsystem;

public class SmartLibraryApp {
    public static void main(String[] args) {
        // 1. Get Singleton Catalog
        LibraryCatalog catalog = LibraryCatalog.getInstance();

        // 2. Use Factory to create users
        User s1 = UserFactory.createUser("STUDENT", "Alice");
        User f1 = UserFactory.createUser("FACULTY", "Dr. Smith");

        // 3. Use Observer to subscribe to updates
        catalog.subscribe(s1);
        catalog.subscribe(f1);

        // 4. Use Builder to create a complex book
        Book newBook = new Book.BookBuilder("Data Structures", "Cormen")
                            .setGenre("Computer Science")
                            .setEdition("3rd Edition")
                            .build();

        // Trigger Notification
        System.out.println("Adding to catalog: " + newBook);
        catalog.addBook(newBook.toString());
    }
}
