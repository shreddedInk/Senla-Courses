package view;

import action.ConsoleActions;
import model.book.Book;
import model.bookstore.BookStore;
import model.library.LibraryInventory;
import view.menu.MenuController;
import view.menu.Navigator;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class ConsoleUI {
    private final BookStore bookStore;

    public ConsoleUI(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public void run() {
        ConsoleActions consoleActions = new ConsoleActions(bookStore);
        Builder builder = new Builder();
        Navigator navigator = new Navigator(builder.buildMenu(bookStore, consoleActions));
        MenuController menuController = new MenuController(builder, navigator);
        menuController.run();
    }

    public static void main(String[] args) {
        Set<Book> availableBooks = new HashSet<>(Arrays.asList(
                new Book("1", "Mathematics", "Author A", 1997, 50.0, true),
                new Book("2", "English for Mathematics", "Author B", 1998, 30.0, true),
                new Book("3", "Java Basics", "Author C", 1999, 40.0, false),
                new Book("4", "Data Structures", "Author D", 2000, 60.0, false)
        ));
        BookStore bookStore = new BookStore(availableBooks, new LibraryInventory());
        ConsoleUI ui = new ConsoleUI(bookStore);
        ui.run();
    }
}
