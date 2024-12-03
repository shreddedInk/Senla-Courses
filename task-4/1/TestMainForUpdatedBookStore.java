import java.text.SimpleDateFormat;
import java.util.*;

public class TestMainForUpdatedBookStore {
    public static void main(String[] args) throws Exception {
        // Initialize available books
        Set<Book> bookSet = new HashSet<>();
        Book book1 = new Book("Mathematics", "Author A", 1997, 50.0, true);
        Book book2 = new Book("English for Mathematics", "Author B", 1998, 30.0, true);
        Book book3 = new Book("Java Basics", "Author C", 1999, 40.0, false);
        Book book4 = new Book("Data Structures", "Author D", 2000, 60.0, false);
        bookSet.addAll(Arrays.asList(book1, book2, book3, book4));

        // Initialize BookStore
        BookStore bookStore = new BookStore(bookSet);

        // Create a purchase order
        Set<Book> orderSet1 = new HashSet<>(Arrays.asList(book1, book2));
        PurchaseOrder order1 = bookStore.generateOrder(orderSet1);

        // Create another purchase order with unavailable books
        Set<Book> orderSet2 = new HashSet<>(Arrays.asList(book3, book4));
        PurchaseOrder order2 = bookStore.generateOrder(orderSet2);

        // View available books sorted by name
        System.out.println("\nBooks sorted alphabetically:");
        bookStore.getAvailableBooksSorted(Comparator.comparing(Book::getName))
                .forEach(book -> System.out.println(book.getDescription()));

        // Close order 1
        bookStore.finalizeOrder(order1);

        // Try to finalize order 2 (should fail because books are unavailable)
        bookStore.finalizeOrder(order2);

        // Resolve book requests (make unavailable books available)
        bookStore.resolveBookRequest(book3);
        bookStore.resolveBookRequest(book4);

        // Finalize order 2
        bookStore.finalizeOrder(order2);

        // View completed orders in a date range
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2023-01-01");
        Date endDate = sdf.parse("2023-12-31");

        System.out.println("\nCompleted orders between " + sdf.format(startDate) + " and " + sdf.format(endDate) + ":");
        bookStore.getCompletedOrdersByPeriod(startDate, endDate, Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));

        // Calculate total earnings in a period
        double totalEarnings = bookStore.getTotalEarningsByPeriod(startDate, endDate);
        System.out.println("\nTotal earnings for the period: $" + totalEarnings);

        // Count completed orders in a period
        long completedOrderCount = bookStore.getCompletedOrderCountByPeriod(startDate, endDate);
        System.out.println("\nTotal completed orders for the period: " + completedOrderCount);

        // View pending book requests
        System.out.println("\nPending book requests:");
        bookStore.getPendingRequests().forEach(request -> System.out.println(request.getRequestedBook().getName()));

        // View details of an order
        System.out.println("\nOrder 1 details:");
        System.out.println(order1.getOrderDetails());
    }
}
