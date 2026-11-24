package lab5;

/**
 * Defines the common behavior of books
 * available in the library.
 */
public interface Book {

    public String toString();

    public void setIsAvailable(boolean isAvailable);

    public boolean getIsAvailable();

    public String getTitle();
}