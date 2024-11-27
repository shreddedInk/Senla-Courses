public class LibraryRequest {
    private Book requestedBook;
    private PurchaseOrder associatedPurchaseOrder;

    public LibraryRequest(Book requestedBook, PurchaseOrder associatedPurchaseOrder) {
        this.requestedBook = requestedBook;
        this.associatedPurchaseOrder = associatedPurchaseOrder;
    }

    public Book getRequestedBook() {
        return requestedBook;
    }

    public PurchaseOrder getAssociatedOrder() {
        return associatedPurchaseOrder;
    }
}
