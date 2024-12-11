package model;

public class LibraryRequest {
    private Book requestedBook;

    public LibraryRequest(Book requestedBook) {
        this.requestedBook = requestedBook;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    public boolean matchesBook(Book book) {
        return requestedBook.equals(book);
    }
}
