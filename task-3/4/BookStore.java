import java.util.ArrayList;
import java.util.Set;

public class BookStore {
    private Set<Book> availableBooks;
    private ArrayList<PurchaseOrder> activePurchaseOrders;
    private ArrayList<LibraryRequest> pendingRequests;

    public BookStore(Set<Book> availableBooks) {
        this.availableBooks = availableBooks;
        activePurchaseOrders = new ArrayList<>();
        pendingRequests = new ArrayList<>();
    }

    public PurchaseOrder generateOrder(Set<Book> selectedBooks) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(selectedBooks);
        activePurchaseOrders.add(purchaseOrder);
        System.out.println("A new order has been generated!");
        return purchaseOrder;
    }

    public void finalizeOrder(PurchaseOrder purchaseOrder) {
        if (purchaseOrder.canBeClosed() && purchaseOrder.getCurrentStatus() == OrderStatus.OPEN) {
            purchaseOrder.updateStatus(OrderStatus.CLOSED);
            System.out.println("Order has been finalized!");
        } else {
            System.out.println("Order cannot be finalized!");
        }
    }

    public void voidOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.updateStatus(OrderStatus.CANCELED);
        System.out.println("Order has been voided.");
    }

    public void addBookToInventory(Book book) {
        LibraryInventory.markBookAsAvailable(book);
        resolveBookRequest(book);
    }

    public void removeBookFromInventory(Book book) {
        LibraryInventory.removeBookFromInventory(book);
    }

    public void resolveBookRequest(Book book) {
        pendingRequests.removeIf(request -> request.getRequestedBook().equals(book));
        System.out.println("Book request has been resolved.");
    }
}
