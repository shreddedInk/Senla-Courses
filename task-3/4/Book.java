import java.util.Objects;

class Book {
    private String title;
    private String writer;
    private int yearPublished;
    private boolean isAvailable;

    public Book(String title, String writer, int yearPublished) {
        this.title = title;
        this.writer = writer;
        this.yearPublished = yearPublished;
        this.isAvailable = false;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public void changeAvailability(boolean status) {
        this.isAvailable = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book otherBook = (Book) obj;
        return yearPublished == otherBook.yearPublished &&
                Objects.equals(title, otherBook.title) &&
                Objects.equals(writer, otherBook.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, writer, yearPublished);
    }
}
