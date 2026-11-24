package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab5.Book;
import lab5.BorrowingService;
import lab5.EBook;
import lab5.AudioBook;
import lab5.Library;
import lab5.Member;

public class TestOtherBookTypes {

    private Library library;
    private Member member;
    private Book ebook;
    private Book audiobook;

    @BeforeEach
    void setUp() {
        library = new Library();
        member = new Member("Alice", BorrowingService.getInstance());
        ebook = new EBook("Java Programming");
        audiobook = new AudioBook("The Hobbit");

        library.addMember(member);
    }

    // Test borrowing an electronic book
    @Test
    void testBorrowEBook() {
        member.borrowBook(ebook);

        assertFalse(ebook.getIsAvailable());
        assertEquals(1, member.borrowedBooksCount());
    }

    // Test returning an electronic book
    @Test
    void testReturnEBook() {
        member.borrowBook(ebook);
        member.returnBook(ebook);

        assertTrue(ebook.getIsAvailable());
        assertEquals(0, member.borrowedBooksCount());
    }

    // Test borrowing an audio book
    @Test
    void testBorrowAudioBook() {
        member.borrowBook(audiobook);

        assertFalse(audiobook.getIsAvailable());
        assertEquals(1, member.borrowedBooksCount());
    }

    // Test returning an audio book
    @Test
    void testReturnAudioBook() {
        member.borrowBook(audiobook);
        member.returnBook(audiobook);

        assertTrue(audiobook.getIsAvailable());
        assertEquals(0, member.borrowedBooksCount());
    }

    // Test adding different book types
    @Test
    void testAddBookTypes() {
        library.addBook(ebook);
        library.addBook(audiobook);

        assertEquals(2, library.booksCount());
        assertSame(ebook,
            library.findBookByTitle("Java Programming"));
        assertSame(audiobook,
            library.findBookByTitle("The Hobbit"));
    }

    // Test removing different book types
    @Test
    void testRemoveBookTypes() {
        library.addBook(ebook);
        library.addBook(audiobook);

        library.removeBook(ebook);
        library.removeBook(audiobook);

        assertEquals(0, library.booksCount());
        assertNull(library.findBookByTitle("Java Programming"));
        assertNull(library.findBookByTitle("The Hobbit"));
    }
}