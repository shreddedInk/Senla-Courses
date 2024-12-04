import java.util.Date;
import java.util.Set;

public class PurchaseOrder {
    private Set<Book> cart;
    private OrderStatus status;
    private Date orderDate;

    public PurchaseOrder(Set<Book> cart) {
        this.cart = cart;
        this.status = OrderStatus.OPEN;
        this.orderDate = new Date();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(Book::getPrice).sum();
    }

    public boolean isCloseable() {
        return cart.stream().allMatch(Book::isAvailable);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order Date: ").append(orderDate).append("\n");
        details.append("Status: ").append(status).append("\n");
        details.append("Books in order:\n");
        cart.forEach(book -> details.append("- ").append(book.getName()).append("\n"));
        return details.toString();
    }
}
