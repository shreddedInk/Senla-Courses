package model;

public class LibraryInventory {

    public static void markBookAsAvailable(Book book) {
        if (!book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("book.Book \"" + book.getName() + "\" is now marked as available.");
        } else {
            System.out.println("book.Book \"" + book.getName() + "\" is already available.");
        }
    }

    public static void removeBookFromInventory(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("book.Book \"" + book.getName() + "\" is now removed from inventory.");
        } else {
            System.out.println("book.Book \"" + book.getName() + "\" is already unavailable.");
        }
    }
}
