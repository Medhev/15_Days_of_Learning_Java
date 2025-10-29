package org.dankme.library;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean borrowed;

    public Book(String isbn, String title, String author, boolean checkedOut) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.borrowed = checkedOut;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return !borrowed;
    }
    public boolean isBorrowed() {
        return borrowed;
    }

    public void checkOut() {
        if (borrowed) {
            throw new IllegalArgumentException("Already checked out");
        }
        borrowed = true;
    }

    public void checkIn(Book found) {
        if (!borrowed) {
            throw new IllegalArgumentException("Not currently checked out");
        }
        borrowed = false;
    }

    void setAvailable(boolean b) {
        this.borrowed = b;
    }
}
