public class LibraryInventory {

    public static void markBookAsAvailable(Book book) {
        book.changeAvailability(true);
    }

    public static void removeBookFromInventory(Book book) {
        book.changeAvailability(false);
    }
}
