package view;


import model.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleUI {
    private final BookStore bookStore;
    private final Scanner scanner;

    public ConsoleUI(BookStore bookStore) {
        this.bookStore = bookStore;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== Bookstore Management System ===");
            System.out.println("1. View books");
            System.out.println("2. View orders");
            System.out.println("3. Create an order");
            System.out.println("4. Finalize an order");
            System.out.println("5. Resolve book request");
            System.out.println("6. View stale books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> viewBooks();
                case 2 -> viewOrders();
                case 3 -> createOrder();
                case 4 -> finalizeOrder();
                case 5 -> resolveBookRequest();
                case 6 -> viewStaleBooks();
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewBooks() {
        System.out.println("\n=== Available Books ===");
        bookStore.sortBooks(Comparator.comparing(Book::getName))
                .forEach(book -> System.out.println(book.getDescription()));
    }

    private void viewOrders() {
        System.out.println("\n=== Active Orders ===");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));
    }

    private void createOrder() {
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

    private void finalizeOrder() {
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

    private void resolveBookRequest() {
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

    private void viewStaleBooks() {
        System.out.println("\n=== Stale Books ===");
        Date currentDate = new Date();
        bookStore.getStaleBooks(currentDate)
                .forEach(book -> System.out.println(book.getDescription()));
    }

    public static void main(String[] args) {
        Set<Book> availableBooks = new HashSet<>(Arrays.asList(
                new Book("Mathematics", "Author A", 1997, 50.0, true),
                new Book("English for Mathematics", "Author B", 1998, 30.0, true),
                new Book("Java Basics", "Author C", 1999, 40.0, false),
                new Book("Data Structures", "Author D", 2000, 60.0, false)
        ));
        BookStore bookStore = new BookStore(availableBooks, new LibraryInventory());
        ConsoleUI ui = new ConsoleUI(bookStore);
        ui.run();
    }
}
