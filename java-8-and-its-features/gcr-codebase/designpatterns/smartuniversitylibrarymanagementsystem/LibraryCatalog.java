package smartuniversitylibrarymanagementsystem;

import java.util.*;

//Singleton + Subject
class LibraryCatalog {
 private static LibraryCatalog instance;
 private List<Observer> observers = new ArrayList<>();
 private List<String> booksAvailable = new ArrayList<>();

 private LibraryCatalog() {} // Private constructor

 public static synchronized LibraryCatalog getInstance() {
     if (instance == null) {
         instance = new LibraryCatalog();
     }
     return instance;
 }

 public void subscribe(Observer observer) { observers.add(observer); }
 
 public void addBook(String bookName) {
     booksAvailable.add(bookName);
     notifyAllObservers("New book available: " + bookName);
 }

 private void notifyAllObservers(String message) {
     for (Observer observer : observers) {
         observer.update(message);
     }
 }
}


