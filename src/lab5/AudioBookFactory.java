package lab5;

/**
 * Factory for creating AudioBook objects.
 */
public class AudioBookFactory extends BookFactory {

    @Override
    public Book createBook(String title) {
        return new AudioBook(title);
    }
}
