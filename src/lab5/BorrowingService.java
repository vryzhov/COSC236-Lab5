
package lab5;

/**
 * BorrowingService manages borrowing and returning books.
 *
 * This class implements the Singleton Design Pattern,
 * ensuring only one instance of BorrowingService exists.
 *
 * It also implements BorrowingServiceAPI to support
 * the Dependency Inversion Principle.
 */
public class BorrowingService implements BorrowingServiceAPI {

    // The single shared instance of BorrowingService
    private static BorrowingService instance;

    // Maximum number of books a member can borrow
    private final int borrowingLimit;

    /**
     * Private constructor prevents other classes
     * from creating BorrowingService objects directly.
     */
    public BorrowingService() {
        borrowingLimit = 3;
    }

    /**
     * Thread-safe Singleton method.
     * Returns the same BorrowingService instance
     * whenever it is called.
     */
    public static synchronized BorrowingService getInstance() {

        if (instance == null) {
            instance = new BorrowingService();
        }

        return instance;
    }

    /**
     * Borrows a book for a library member.
     * Returns a result containing success status
     * and an appropriate message.
     */
    @Override
    public BorrowingBookResult borrowBook(Member member, Book book) {

        // Check for invalid member or book
        if (member == null || book == null) {
            return new BorrowingBookResult(
                false, "Invalid member or book."
            );
        }

        // Prevent borrowing the same book twice
        if (member.getBorrowedBooks().contains(book)) {
            return new BorrowingBookResult(
                false, "Member has already borrowed this book."
            );
        }

        // Check whether the book is available
        if (!book.getIsAvailable()) {
            return new BorrowingBookResult(
                false, "Book is currently unavailable."
            );
        }

        // Enforce the maximum borrowing limit
        if (member.borrowedBooksCount() >= borrowingLimit) {
            return new BorrowingBookResult(
                false, "Borrowing limit of 3 books exceeded."
            );
        }

        // Add the book to the member's borrowed books
        member.getBorrowedBooks().add(book);

        // Mark the book as unavailable
        book.setIsAvailable(false);

        return new BorrowingBookResult(
            true, "Book borrowed successfully."
        );
    }

    /**
     * Returns a book borrowed by a library member.
     */
    @Override
    public BorrowingBookResult returnBook(Member member, Book book) {

        // Check for invalid member or book
        if (member == null || book == null) {
            return new BorrowingBookResult(
                false, "Invalid member or book."
            );
        }

        // Ensure the member actually borrowed this book
        if (!member.getBorrowedBooks().contains(book)) {
            return new BorrowingBookResult(
                false, "Member has not borrowed this book."
            );
        }

        // Remove the book from the member's borrowed list
        member.getBorrowedBooks().remove(book);

        // Make the book available again
        book.setIsAvailable(true);

        return new BorrowingBookResult(
            true, "Book returned successfully."
        );
    }
}
