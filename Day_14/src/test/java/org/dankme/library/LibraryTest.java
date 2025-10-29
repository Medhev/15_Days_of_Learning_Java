package org.dankme.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {
    Library library;
    Member member;
    Book book;

    @BeforeEach
    void setup(){
        library = new Library();
        member = new Member("M1", "Alice");
        book = new Book("ISBN123", "Java 101", "Bob", false);

        library.registerMember(member);
        library.addBook(book);
    }

    @Test
    void successfulCheckOutAndReturn(){
        library.checkOutBook("M1","ISBN123");
//      assertTrue(member.getBorrowedBooks().contains(book));
        assertFalse(book.isAvailable());
        library.returnBook("M1","ISBN123");
        assertTrue(book.isAvailable());
        assertTrue(member.getBorrowedBooks().isEmpty());
    }
    @Test
    void testDoubleCheckOutFails(){
        library.checkOutBook("M1","ISBN123");
        boolean second = member.borrowBook(book);
        assertFalse(second);
    }
    @Test
    void testInvalidInputs(){
        library.checkOutBook("M1","ISBN123");
        assertTrue(member.getBorrowedBooks().isEmpty());
    }
}

