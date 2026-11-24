package lab5;

/**
 * Abstract factory for creating Book objects.
 * Each subclass must implement createBook().
 */
public abstract class BookFactory {

    // Factory method that returns a Book object
    public abstract Book createBook(String title);
}
