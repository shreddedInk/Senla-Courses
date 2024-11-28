import java.util.Set;

public class PurchaseOrder {
    private Set<Book> items;
    private OrderStatus currentStatus;

    public PurchaseOrder(Set<Book> items) {
        this.items = items;
        this.currentStatus = OrderStatus.OPEN;
    }

    public Set<Book> getItems() {
        return items;
    }

    public OrderStatus getCurrentStatus() {
        return currentStatus;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.currentStatus = newStatus;
    }

    public boolean canBeClosed() {
        for (Book item : items) {
            if (!item.getAvailability()) {
                return false;
            }
        }
        return true;
    }
}
