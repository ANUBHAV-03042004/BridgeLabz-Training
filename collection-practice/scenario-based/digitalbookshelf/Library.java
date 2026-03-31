package digitalbookshelf;
import java.util.*;
public class Library {
LinkedList<Book> library = new LinkedList<>();
HashMap<String,LinkedList<Book>> catalog= new HashMap<>();
public void addBook(Book book) {
	if(library.contains(book)) return;
	else library.add(book);
}
public void removeBook(Book book) {
	if(library.contains(book)) library.remove(book);
	return ; 
}
public void updateCatalog() {

	catalog.clear(); 
    for (Book book : library) {
        catalog.putIfAbsent(book.genre, new LinkedList<>());
        catalog.get(book.genre).add(book);
    }

}
public void showCatalog() {
	for (String key : catalog.keySet()) {
	    System.out.println("Key: " + key);
	    for(Book book: catalog.get(key) ) {
	    	System.out.println(book.bookName);
	    	System.out.println(book.author);
	    }
	}
}
public static void main(String[] args) {
	Book b1= new Book("java","Comedy","Aks");
	Book b2= new Book("c","programming","Aks");
	Book b3= new Book("pyhton","programming","prakash");
	Book b4= new Book("rust","Comedy","Sahil");
	Library lib= new Library();
	lib.addBook(b4);
	lib.addBook(b3);
	lib.addBook(b2);
	lib.addBook(b1);
	lib.updateCatalog();
	lib.showCatalog();
}

}
