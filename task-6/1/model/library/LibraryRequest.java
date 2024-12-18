package model.library;

import model.book.Book;

public class LibraryRequest {
    private String id;
    private Book requestedBook;

    public LibraryRequest(String id, Book requestedBook) {
        this.id = id;
        this.requestedBook = requestedBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    public boolean matchesBook(Book book) {
        return requestedBook.equals(book);
    }
}
