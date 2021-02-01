import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Library {
    // Add the missing implementation to this class

    // Create an addBook method
        // Before doing that I'll need to create array lists of data type Book
    String location;
    ArrayList<Book> libraryBooks = new ArrayList<Book>();

    boolean libraryHasEver;
    boolean libraryHasCurrently;

    public Library(String address) {
        this.location = address;
    }

    private void addBook(Book newBook) {
        libraryBooks.add(newBook);
    }

    private static void printOpenHours(){
        System.out.println("Library hours are 9am to 5pm daily.");
    }

    private void printAddress(){
        System.out.println(location);
    }

    private void borrowBook(String bookToBorrow){

        // This function loops through the titles of the books in the array list (those belonging to a given library),
        // sees first whether the library has that item in their inventory (if not, returns message saying so), and
        // if it does, it checks to see whether that title is currently checked out (if so, returns, message saying so).
        // If the book is in the possession of that library and available at time of request, then it changes boolean value
        // of Book object instance field 'borrowed' to true.

        for(int i = 0; i < libraryBooks.size(); i++){
            if(bookToBorrow == libraryBooks.get(i).title) {
                libraryHasEver = true;
                libraryHasCurrently = !libraryBooks.get(i).borrowed;
                libraryBooks.get(i).borrowed = !libraryBooks.get(i).borrowed;
            }
        }

        if(libraryHasEver) {
            System.out.print("Well we do own that title. Let me see if it's currently available... ");
            if(libraryHasCurrently){
                System.out.println("Hark! We have it right over here.");
            } else {
                System.out.println("Mate it just ain't your day! We do own that title but it's not in stock.");
            }
        } else {
            System.out.println("Sorry mate, we don't even have that book in our inventory!");
        }

        libraryHasEver = false;
    }

    // Function checks to see if library has records of book that client is trying to return
    // If so, checks it in.  If not, tells user they're at wrong library.
    private void returnBook(String bookToReturn){

        for(int i = 0; i < libraryBooks.size(); i++){
            if(bookToReturn == libraryBooks.get(i).title) {
                libraryHasEver = true;
                libraryBooks.get(i).borrowed = false;
                break;
            }
        }

        if(libraryHasEver) {
            System.out.println("We got that book checked in for you.");

        } else {
            System.out.println("Are you sure you're at the right library?  We don't have that title here.");
        }
    }

    // Shows which books, if any, are in a library's possession.
    private void printAvailableBooks(){

        if(libraryBooks.size() == 0) {
            System.out.println("No books in this sad little library.");
        } else {
            // Tracks how many books are available for checkout and compares to total list size
            int booksAvailable = 0;

            System.out.println("We have these books available right now: ");
            for(int i = 0; i < libraryBooks.size(); i++){
                if(libraryBooks.get(i).borrowed == false) {
                    System.out.println(libraryBooks.get(i).title);
                    booksAvailable++;
                }
            }

            // Uses the tracking variable above (booksAvailable) to determine whether to tell user of books
            // that the library owns but which are currently checked out

            if(booksAvailable < libraryBooks.size()){
                System.out.print("\nWe own these other books, but they're currently checked out:\n");
                for(int i = 0; i < libraryBooks.size(); i++){
                    if(libraryBooks.get(i).borrowed == true) {
                        System.out.println(libraryBooks.get(i).title);
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        System.out.println(firstLibrary.libraryBooks.get(0).title);

        // Print opening hours and the addresses
        printOpenHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();


        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings...");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Da Vinci Code");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
}