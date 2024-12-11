package model.order;

import model.book.Book;

import java.util.Date;
import java.util.Set;

public class PurchaseOrder {
    private static int idCounter = 0;
    private final int id;
    private Set<Book> cart;
    private OrderStatus status;
    private Date orderDate;

    public PurchaseOrder(Set<Book> cart) {
        this.cart = cart;
        this.status = OrderStatus.OPEN;
        this.orderDate = new Date();
        this.id = ++idCounter;
    }

    public int getId() {
        return id;
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
