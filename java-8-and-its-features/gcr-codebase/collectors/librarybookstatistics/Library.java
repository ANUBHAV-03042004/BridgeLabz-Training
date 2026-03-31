package librarybookstatistics;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String genre;
    private int pages;

    public Book(String title, String genre, int pages) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPages() { return pages; }
}


public class Library {
	public static void main(String[] args) {
		Book b1 = new Book("The Silent Forest", "Fantasy", 320);
	    Book b2 = new Book("Numbers and Logic", "Education", 210);
	    Book b3 = new Book("Journey to Mars", "Science Fiction", 450);
	    Book b4 = new Book("Cooking with Love", "Cooking", 180);
	    Book b5 = new Book("The Hidden Truth", "Mystery", 275);
	    Book b6 = new Book("Life Lessons", "Biography", 360);
	    
	    Book b7 = new Book("Dragon’s Quest", "Fantasy", 410);
        Book b8 = new Book("Algebra Simplified", "Education", 190);
        Book b9 = new Book("Beyond the Stars", "Science Fiction", 520);
        Book b10 = new Book("Quick Meals for Busy Days", "Cooking", 200);
        Book b11 = new Book("Secrets of the Manor", "Mystery", 340);



	    List<Book> books = List.of(b1, b2,b3, b4, b5, b6,b7,b8,b9,b10,b11);
	    IntSummaryStatistics bookStatistics = books.stream().collect(Collectors.summarizingInt(Book::getPages));
	  System.out.println("Sum of pages : "+bookStatistics.getSum());
	  System.out.println("Average of Pages : "+bookStatistics.getAverage());
	  Map<String, Optional<Book>> maxPagesPerGenre = books.stream().collect(Collectors.groupingBy(Book::getGenre,Collectors.maxBy(Comparator.comparingInt(Book::getPages))));
	  maxPagesPerGenre.forEach((genre, bookOpt) ->
      System.out.println(genre + " → " + " (" + bookOpt.get().getPages() + " pages)")
  );

	}

}
