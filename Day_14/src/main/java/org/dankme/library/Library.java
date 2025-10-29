package org.dankme.library;

import java.util.*;

public class Library {
    private String memberId;
    private String name;
    private final Map<String,Book> books = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();

    public Library(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public Library() {}

    public String getMemberId() {
        return memberId;
    }

//    public void setMemberId(String memberId) {
//        this.memberId = memberId;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public Map<String,Book> getBorrowedBooks() {
        return books;
    }

//    public void setBorrowedBooks(List<Book> borrowedBooks) {
//        this.borrowedBooks = borrowedBooks;
//    }

    public Map<String, Member> getMembers() {
        return members;
    }

//    public void setMembers(Map<String, Member> members) {
//        this.members = members;
//    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void checkOutBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not registered: " + memberId);
        }
        Book found = null;
        for (Book book : books.values()) {
            if (Objects.equals(book.getIsbn(), isbn) && book.isAvailable()) {
                found = book;
                break;
            }
        }
        if (found == null) {
            throw new IllegalArgumentException("Book not in library: " + isbn);
        }
        found.checkOut();
        member.borrowBook(found);
    }

    public void returnBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not registered: " + memberId);
        }
        Book found = null;
        for (Book book : books.values()) {
            if (Objects.equals(book.getIsbn(), isbn)) {
                found = book;
                break;
            }
        }
        if (found == null) {
            throw new IllegalArgumentException("Book not in library: " + isbn);
        }
        member.returnBook(found);
        found.checkIn(found);
    }
}