package model.order;

import model.book.Book;

import java.util.Date;
import java.util.Set;

public class PurchaseOrder {
    private String id;
    private Set<Book> cart;
    private OrderStatus status;
    private Date orderDate;

    public PurchaseOrder(String id, Set<Book> cart) {
        this.id = id;
        this.cart = cart;
        this.status = OrderStatus.OPEN;
        this.orderDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<Book> getCart() {
        return cart;
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(id).append("\n");
        details.append("Order Date: ").append(orderDate).append("\n");
        details.append("Status: ").append(status).append("\n");
        details.append("Books in order:\n");
        cart.forEach(book -> details.append("- ").append(book.getName()).append("\n"));
        return details.toString();
    }
}
