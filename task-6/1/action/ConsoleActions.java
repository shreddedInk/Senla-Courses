package action;

import model.book.Book;
import model.bookstore.BookStore;
import model.order.PurchaseOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void importBooks() {
        System.out.print("Enter the path to the CSV file for importing books: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                bookStore.importBooksFromCSV(filePath);
                System.out.println("Books imported successfully!");
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to import books: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while importing books: " + e.getMessage());
        }
    }

    public void exportBooks() {
        System.out.print("Enter the path to the CSV file for exporting books: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path.getParent())) {
                bookStore.exportBooksToCSV(filePath);
                System.out.println("Books exported successfully!");
            } else {
                System.out.println("Directory not found: " + path.getParent());
            }
        } catch (IOException e) {
            System.out.println("Failed to export books: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while exporting books: " + e.getMessage());
        }
    }

    public void importOrders() {
        System.out.print("Enter the path to the CSV file for importing orders: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                bookStore.importOrdersFromCSV(filePath);
                System.out.println("Orders imported successfully!");
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to import orders: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while importing orders: " + e.getMessage());
        }
    }

    public void exportOrders() {
        System.out.print("Enter the path to the CSV file for exporting orders: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path.getParent())) {
                bookStore.exportOrdersToCSV(filePath);
                System.out.println("Orders exported successfully!");
            } else {
                System.out.println("Directory not found: " + path.getParent());
            }
        } catch (IOException e) {
            System.out.println("Failed to export orders: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while exporting orders: " + e.getMessage());
        }
    }

    public void importRequests() {
        System.out.print("Enter the path to the CSV file for importing requests: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                bookStore.importRequestsFromCSV(filePath);
                System.out.println("Requests imported successfully!");
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Failed to import requests: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while importing requests: " + e.getMessage());
        }
    }

    public void exportRequests() {
        System.out.print("Enter the path to the CSV file for exporting requests: ");
        String filePath = scanner.nextLine();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path.getParent())) {
                bookStore.exportRequestsToCSV(filePath);
                System.out.println("Requests exported successfully!");
            } else {
                System.out.println("Directory not found: " + path.getParent());
            }
        } catch (IOException e) {
            System.out.println("Failed to export requests: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while exporting requests: " + e.getMessage());
        }
    }
}
