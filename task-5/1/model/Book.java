package model;

import java.util.Date;

public class Book {
    private String name;
    private String author;
    private int publishingYear;
    private double price;
    private boolean available;
    private Date lastSoldDate;

    public Book(String name, String author, int publishingYear, double price, boolean available) {
        this.name = name;
        this.author = author;
        this.publishingYear = publishingYear;
        this.price = price;
        this.available = available;
        this.lastSoldDate = null;
    }

    public String getName() {

        return name;
    }

    public String getAuthor() {

        return author;
    }

    public int getPublishingYear() {

        return publishingYear;
    }

    public double getPrice() {

        return price;
    }

    public boolean isAvailable() {

        return available;
    }

    public void setAvailable(boolean available) {

        this.available = available;
    }

    public Date getLastSoldDate() {

        return lastSoldDate;
    }

    public void setLastSoldDate(Date lastSoldDate) {

        this.lastSoldDate = lastSoldDate;
    }

    public String getDescription() {
        return "Title: " + name +
                "\nAuthor: " + author +
                "\nYear: " + publishingYear +
                "\nPrice: " + price +
                "\nAvailability: " + (available ? "Available" : "Not Available");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return name.equals(book.name) && author.equals(book.author);
    }

    @Override
    public int hashCode() {

        return name.hashCode() + author.hashCode();
    }
}
