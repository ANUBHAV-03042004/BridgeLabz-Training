package smartuniversitylibrarymanagementsystem;

class Book {
    private final String title;     // Required
    private final String author;    // Required
    private final String genre;     // Optional
    private final String edition;   // Optional

    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.edition = builder.edition;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + (genre != null ? ", Genre: " + genre : "");
    }

    // Static nested Builder class
    public static class BookBuilder {
        private final String title;
        private final String author;
        private String genre;
        private String edition;

        public BookBuilder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public BookBuilder setGenre(String genre) { this.genre = genre; return this; }
        public BookBuilder setEdition(String edition) { this.edition = edition; return this; }
        public Book build() { return new Book(this); }
    }
}