package lab5;

/**
 * Factory for creating PaperBook objects.
 */
public class PaperBookFactory extends BookFactory {

    @Override
    public Book createBook(String title) {
        return new PaperBook(title);
    }
}
