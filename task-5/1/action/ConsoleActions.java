package action;

import model.bookstore.BookStore;
import model.order.PurchaseOrder;
import model.book.Book;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleActions {
    private final BookStore bookStore;
    private final Scanner scanner;

    public ConsoleActions(BookStore bookStore) {
        this.bookStore = bookStore;
        this.scanner = new Scanner(System.in);
    }

    public void viewBooks() {
        System.out.println("\n=== Available Books ===");
        bookStore.getAvailableBooks().forEach(book -> System.out.println(book.getDescription()));
    }

    public void viewBooksByName() {
        System.out.println("\n=== Available Books (Sorted by Name) ===");
        bookStore.sortBooks(Comparator.comparing(Book::getName))
                .forEach(book -> System.out.println(book.getDescription()));
    }

    public void viewBooksByPublishingYear() {
        System.out.println("\n=== Available Books (Sorted by Publishing Year) ===");
        bookStore.sortBooks(Comparator.comparing(Book::getPublishingYear))
                .forEach(book -> System.out.println(book.getDescription()));
    }

    public void viewBooksByPrice() {
        System.out.println("\n=== Available Books (Sorted by Price) ===");
        bookStore.sortBooks(Comparator.comparing(Book::getPrice))
                .forEach(book -> System.out.println(book.getDescription()));
    }

    public void viewBooksByAvailability() {
        System.out.println("\n=== Available Books (Sorted by Availability) ===");
        bookStore.sortBooks(Comparator.comparing(Book::isAvailable).reversed())
                .forEach(book -> System.out.println(book.getDescription()));
    }

    public void viewOrders() {
        System.out.println("\n=== Active Orders ===");
        bookStore.getOrders().forEach(order -> System.out.println(order.getOrderDetails()));
    }

    public void viewOrdersByDate() {
        System.out.println("\n=== Active Orders (Sorted by Order Date) ===");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));
    }

    public void viewOrdersByTotalPrice() {
        System.out.println("\n=== Active Orders (Sorted by Total Price) ===");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getTotalPrice).reversed())
                .forEach(order -> System.out.println(order.getOrderDetails()));
    }

    public void viewOrdersByStatus() {
        System.out.println("\n=== Active Orders (Sorted by Status) ===");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getStatus))
                .forEach(order -> System.out.println(order.getOrderDetails()));
    }

    public void createOrder() {
        System.out.println("\n=== Create an Order ===");
        System.out.print("Enter the names of the books to order (comma-separated): ");
        String[] bookNames = scanner.nextLine().split(",");
        Set<Book> selectedBooks = new HashSet<>();

        for (String bookName : bookNames) {
            bookStore.getAvailableBooks().stream()
                    .filter(book -> book.getName().equalsIgnoreCase(bookName.trim()))
                    .findFirst()
                    .ifPresentOrElse(
                            selectedBooks::add,
                            () -> System.out.println("Book \"" + bookName.trim() + "\" not found or unavailable.")
                    );
        }

        if (!selectedBooks.isEmpty()) {
            PurchaseOrder order = bookStore.generateOrder(selectedBooks);
            System.out.println("Order created successfully: " + order.getOrderDetails());
        } else {
            System.out.println("No valid books selected for the order.");
        }
    }

    public void finalizeOrder() {
        System.out.println("\n=== Finalize an Order ===");
        System.out.print("Enter the date of the order to finalize (format: yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
        System.out.print("Enter the names of the books in the order (comma-separated): ");
        String[] bookNames = scanner.nextLine().split(",");

        Set<String> bookNameSet = Arrays.stream(bookNames)
                .map(String::trim)
                .collect(Collectors.toSet());

        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getOrderDate)).stream()
                .filter(order -> {
                    String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(order.getOrderDate());
                    if (!orderDate.equals(dateInput)) {
                        return false;
                    }

                    Set<String> orderBookNames = order.getCart().stream()
                            .map(Book::getName)
                            .collect(Collectors.toSet());
                    return orderBookNames.equals(bookNameSet);
                })
                .findFirst()
                .ifPresentOrElse(
                        order -> {
                            bookStore.finalizeOrder(order);
                            System.out.println("Order finalized successfully!");
                        },
                        () -> System.out.println("Order not found.")
                );
    }

    public void resolveBookRequest() {
        System.out.println("\n=== Resolve Book Request ===");
        System.out.print("Enter the name of the book to resolve: ");
        String bookName = scanner.nextLine();

        bookStore.getAvailableBooks().stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName.trim()))
                .findFirst()
                .ifPresentOrElse(
                        book -> {
                            bookStore.resolveBookRequest(book);
                            System.out.println("Book request resolved successfully!");
                        },
                        () -> System.out.println("Book not found.")
                );
    }

    public void viewStaleBooks() {
        System.out.println("\n=== Stale Books ===");
        Date currentDate = new Date();
        bookStore.getStaleBooks(currentDate)
                .forEach(book -> System.out.println(book.getDescription()));
    }
}
