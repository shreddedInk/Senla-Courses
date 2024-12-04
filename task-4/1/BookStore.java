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


    public PurchaseOrder generateOrder(Set<Book> selectedBooks) {
        PurchaseOrder order = new PurchaseOrder(selectedBooks);
        activePurchaseOrders.add(order);

        for (Book book : selectedBooks) {
            if (!book.isAvailable()) {
                pendingRequests.add(new LibraryRequest(book));
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
        long sixMonthsInMillis = 6L * 30 * 24 * 60 * 60 * 1000; // 6 месяцев в миллисекундах
        return availableBooks.stream()
                .filter(book -> {
                    Date lastSoldDate = book.getLastSoldDate(); // Добавьте поле и метод для последней даты продажи в `Book`.
                    if (lastSoldDate == null) return true;
                    return currentDate.getTime() - lastSoldDate.getTime() > sixMonthsInMillis;
                })
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getPublishingYear))
                .toList();
    }


}
