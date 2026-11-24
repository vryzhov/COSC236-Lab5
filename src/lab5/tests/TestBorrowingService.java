package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

import lab5.*;

public class TestBorrowingService {

    private BorrowingServiceAPI service;
    private Member alice;
    private Member bob;
    private Book book;

    @BeforeEach
    void setUp() {
        service = BorrowingService.getInstance();
        alice = new Member(
        	   "Alice", BorrowingService.getInstance()
        );
        bob = new Member("Bob", BorrowingService.getInstance());
        book = new PaperBook("Dune");
    }

    @Test
    void testSuccessfulBorrow() {
        assertTrue(service.borrowBook(alice, book).isSuccess());
        assertFalse(book.getIsAvailable());
        assertEquals(1, alice.borrowedBooksCount());
    }

    @Test
    void testSuccessfulReturn() {
        service.borrowBook(alice, book);

        assertTrue(service.returnBook(alice, book).isSuccess());
        assertTrue(book.getIsAvailable());
        assertEquals(0, alice.borrowedBooksCount());
    }

    @Test
    void testDuplicateBorrowing() {
        service.borrowBook(alice, book);

        assertFalse(service.borrowBook(alice, book).isSuccess());
        assertEquals(1, alice.borrowedBooksCount());
    }

    @Test
    void testBorrowUnavailableBook() {
        service.borrowBook(alice, book);

        assertFalse(service.borrowBook(bob, book).isSuccess());
        assertEquals(0, bob.borrowedBooksCount());
    }

    @Test
    void testReturnUnborrowedBook() {
        service.borrowBook(alice, book);

        assertFalse(service.returnBook(bob, book).isSuccess());
        assertFalse(book.getIsAvailable());
    }

    @Test
    void testReturnBookTwice() {
        service.borrowBook(alice, book);

        assertTrue(service.returnBook(alice, book).isSuccess());
        assertFalse(service.returnBook(alice, book).isSuccess());
    }

    @Test
    void testNullBook() {
        assertFalse(service.borrowBook(alice, null).isSuccess());
        assertFalse(service.returnBook(alice, null).isSuccess());
    }
    @Test
    void testBorrowingLimit() {
        Book b1 = new PaperBook("Book 1");
        Book b2 = new PaperBook("Book 2");
        Book b3 = new PaperBook("Book 3");
        Book b4 = new PaperBook("Book 4");

        assertTrue(service.borrowBook(alice, b1).isSuccess());
        assertTrue(service.borrowBook(alice, b2).isSuccess());
        assertTrue(service.borrowBook(alice, b3).isSuccess());

        BorrowingBookResult result = service.borrowBook(alice, b4);

        assertFalse(result.isSuccess());
        assertEquals(3, alice.borrowedBooksCount());
        assertTrue(b4.getIsAvailable());
    }

    @Test
    void testBorrowingResultMessage() {
        BorrowingBookResult result = service.borrowBook(alice, book);

        assertTrue(result.isSuccess());
        assertEquals("Book borrowed successfully.", result.getMessage());
    }

    @Test
    void testInvalidReturnMessage() {
        BorrowingBookResult result = service.returnBook(alice, book);

        assertFalse(result.isSuccess());
        assertEquals("Member has not borrowed this book.",
                     result.getMessage());
    }
}