
package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab5.*;

public class TestBookFactory {

    // Verify PaperBookFactory creates PaperBook
    @Test
    void testPaperBookFactory() {
        BookFactory factory = new PaperBookFactory();

        Book book = factory.createBook("Dune");

        assertNotNull(book);
        assertInstanceOf(PaperBook.class, book);
        assertEquals("Dune", book.getTitle());
    }

    // Verify EBookFactory creates EBook
    @Test
    void testEBookFactory() {
        BookFactory factory = new EBookFactory();

        Book book = factory.createBook("Java Programming");

        assertNotNull(book);
        assertInstanceOf(EBook.class, book);
        assertEquals("Java Programming", book.getTitle());
    }

    // Verify AudioBookFactory creates AudioBook
    @Test
    void testAudioBookFactory() {
        BookFactory factory = new AudioBookFactory();

        Book book = factory.createBook("The Hobbit");

        assertNotNull(book);
        assertInstanceOf(AudioBook.class, book);
        assertEquals("The Hobbit", book.getTitle());
    }

    // Verify the factory creates different types
    @Test
    void testDifferentBookTypes() {
        Book paper = new PaperBookFactory().createBook("Book 1");
        Book ebook = new EBookFactory().createBook("Book 2");
        Book audio = new AudioBookFactory().createBook("Book 3");

        assertNotEquals(paper.getClass(), ebook.getClass());
        assertNotEquals(ebook.getClass(), audio.getClass());
        assertNotEquals(paper.getClass(), audio.getClass());
    }

    // Verify null factory is rejected by controller
    @Test
    void testUnsupportedFactory() {
        LibrarianController controller = new LibrarianController();

        assertThrows(IllegalArgumentException.class, () -> {
            controller.addBook(null, "Unknown Book");
        });
    }
}
