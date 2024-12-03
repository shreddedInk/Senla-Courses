import java.util.*;
import java.util.stream.Collectors;

public class BookStore {
    private Set<Book> availableBooks;
    private ArrayList<PurchaseOrder> activePurchaseOrders;
    private ArrayList<LibraryRequest> pendingRequests;

    public BookStore(Set<Book> availableBooks) {
        this.availableBooks = availableBooks;
        this.activePurchaseOrders = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
    }

    public List<Book> getAvailableBooksSorted(Comparator<Book> comparator) {
        return availableBooks.stream().sorted(comparator).collect(Collectors.toList());
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
}
