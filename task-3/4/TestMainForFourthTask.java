import java.util.HashSet;
import java.util.Set;

public class TestMainForFourthTask {
    public static void main(String[] args) {
        Set<Book> availableBooks = new HashSet<>();

        Book book1 = new Book("Mathematics", "me", 1997);
        Book book2 = new Book("English for Mathematics", "Mark Rider", 1998);
        Book book3 = new Book("Java Programming", "Senla", 1999);

        availableBooks.add(book1);
        availableBooks.add(book2);
        availableBooks.add(book3);

        BookStore bookStore = new BookStore(availableBooks);

        Set<Book> selectedBooks = new HashSet<>();
        selectedBooks.add(book1);
        selectedBooks.add(book2);

        PurchaseOrder purchaseOrder = bookStore.generateOrder(selectedBooks);

        bookStore.finalizeOrder(purchaseOrder);
        bookStore.addBookToInventory(book1);
        bookStore.addBookToInventory(book2);

        bookStore.removeBookFromInventory(book1);
        bookStore.finalizeOrder(purchaseOrder);

        bookStore.addBookToInventory(book1);
        bookStore.finalizeOrder(purchaseOrder);
    }
}
