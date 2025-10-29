package org.dankme.library;

import java.util.*;

public class Member {
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    private final String memberId;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public boolean borrowBook (Book book) {
        if(book.isAvailable()){
            book.checkOut();
            borrowedBooks.add(book);
            return true ;
        }
        return false;
    }

    public void returnBook(Book book){
        if (borrowedBooks.remove(book)){
            book.checkIn(book);
        }
    }
}
