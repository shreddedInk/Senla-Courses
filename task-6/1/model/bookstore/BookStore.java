package model.bookstore;

import model.book.Book;
import model.library.LibraryInventory;
import model.library.LibraryRequest;
import model.order.OrderStatus;
import model.order.PurchaseOrder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BookStore {
    private Set<Book> availableBooks;
    private ArrayList<PurchaseOrder> activePurchaseOrders;
    private ArrayList<LibraryRequest> pendingRequests;
    private LibraryInventory libraryInventory;

    public BookStore(Set<Book> availableBooks, LibraryInventory libraryInventory) {
        this.availableBooks = availableBooks;
        this.activePurchaseOrders = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
        this.libraryInventory = libraryInventory;
    }

    public List<Book> getAvailableBooks() {
        return new ArrayList<>(availableBooks);
    }

    public List<PurchaseOrder> getOrders() {
        return new ArrayList<>(activePurchaseOrders);
    }

    public PurchaseOrder generateOrder(Set<Book> selectedBooks) {
        PurchaseOrder order = new PurchaseOrder(UUID.randomUUID().toString(), selectedBooks);
        activePurchaseOrders.add(order);

        for (Book book : selectedBooks) {
            if (!book.isAvailable()) {
                pendingRequests.add(new LibraryRequest(UUID.randomUUID().toString(), book));
                System.out.println("Request created for unavailable book: " + book.getName());
            }
        }

        System.out.println("A new order has been generated!");
        return order;
    }

    public void finalizeOrder(PurchaseOrder order) {
        if (!order.isCloseable()) {
            System.out.println("Order cannot be finalized! Some books are not available.");
            return;
        }

        order.setStatus(OrderStatus.CLOSED);
        System.out.println("Order has been finalized!");
    }

    public void resolveBookRequest(Book book) {
        book.setAvailable(true);
        pendingRequests.removeIf(request -> request.matchesBook(book));
        System.out.println("Book request resolved: " + book.getName());
    }

    public void addOrder(PurchaseOrder order) {
        activePurchaseOrders.add(order);
    }

    public List<LibraryRequest> getPendingRequests() {
        return new ArrayList<>(pendingRequests);
    }

    public List<PurchaseOrder> getCompletedOrdersByPeriod(Date startDate, Date endDate, Comparator<PurchaseOrder> comparator) {
        return activePurchaseOrders.stream()
                .filter(order -> order.getStatus() == OrderStatus.CLOSED)
                .filter(order -> !order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public double getTotalEarningsByPeriod(Date startDate, Date endDate) {
        return activePurchaseOrders.stream()
                .filter(order -> order.getStatus() == OrderStatus.CLOSED)
                .filter(order -> !order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate))
                .mapToDouble(PurchaseOrder::getTotalPrice)
                .sum();
    }

    public long getCompletedOrderCountByPeriod(Date startDate, Date endDate) {
        return activePurchaseOrders.stream()
                .filter(order -> order.getStatus() == OrderStatus.CLOSED)
                .filter(order -> !order.getOrderDate().before(startDate) && !order.getOrderDate().after(endDate))
                .count();
    }

    public List<Book> sortBooks(Comparator<Book> comparator) {
        List<Book> sortedBooks = new ArrayList<>(availableBooks);
        sortedBooks.sort(comparator);
        return sortedBooks;
    }

    public List<PurchaseOrder> sortOrders(Comparator<PurchaseOrder> comparator) {
        return activePurchaseOrders.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Book> sortRequests(Comparator<Book> comparator) {
        Map<Book, Long> requestCounts = pendingRequests.stream()
                .collect(Collectors.groupingBy(LibraryRequest::getRequestedBook, Collectors.counting()));

        return requestCounts.keySet().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Book> getStaleBooks(Date currentDate) {
        long sixMonthsInMillis = 6L * 30 * 24 * 60 * 60 * 1000;
        return availableBooks.stream()
                .filter(book -> {
                    Date lastSoldDate = book.getLastSoldDate();
                    if (lastSoldDate == null) return true;
                    return currentDate.getTime() - lastSoldDate.getTime() > sixMonthsInMillis;
                })
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getPublishingYear))
                .collect(Collectors.toList());
    }

    public void importBooksFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String author = values[2];
                int publishingYear = Integer.parseInt(values[3]);
                double price = Double.parseDouble(values[4]);
                boolean available = Boolean.parseBoolean(values[5]);

                Book book = new Book(id, name, author, publishingYear, price, available);
                availableBooks.add(book);
            }
        }
    }

    public void exportBooksToCSV(String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Book book : availableBooks) {
                fw.write(book.getId() + "," + book.getName() + "," + book.getAuthor() + "," +
                        book.getPublishingYear() + "," + book.getPrice() + "," + book.isAvailable() + "\n");
            }
        }
    }

    public void importOrdersFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                Set<Book> cart = new HashSet<>();
                for (int i = 1; i < values.length; i++) {
                    int finalI = i;
                    Book book = availableBooks.stream()
                            .filter(b -> b.getId().equals(values[finalI]))
                            .findFirst()
                            .orElse(null);
                    if (book != null) {
                        cart.add(book);
                    }
                }
                PurchaseOrder order = new PurchaseOrder(id, cart);
                activePurchaseOrders.add(order);
            }
        }
    }


    public void exportOrdersToCSV(String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (PurchaseOrder order : activePurchaseOrders) {
                fw.write(order.getId() + "," + String.join(",", order.getCart().stream().map(Book::getId).toArray(String[]::new)) + "\n");
            }
        }
    }

    public void importRequestsFromCSV(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                Book book = availableBooks.stream()
                        .filter(b -> b.getId().equals(values[1]))
                        .findFirst()
                        .orElse(null);
                if (book != null) {
                    LibraryRequest request = new LibraryRequest(id, book);
                    pendingRequests.add(request);
                }
            }
        }
    }


    public void exportRequestsToCSV(String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (LibraryRequest request : pendingRequests) {
                fw.write(request.getId() + "," + request.getRequestedBook().getId() + "\n");
            }
        }
    }

}
