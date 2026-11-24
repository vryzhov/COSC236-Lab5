package lab5;

/**
 * Factory for creating EBook objects.
 */
public class EBookFactory extends BookFactory {

    @Override
    public Book createBook(String title) {
        return new EBook(title);
    }
}
