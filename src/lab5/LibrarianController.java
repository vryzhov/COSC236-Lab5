package lab5;

public class LibrarianController { 
	private BorrowingServiceAPI borrowingService; 
	
	private BookFactory paperBookFactory;
	private BookFactory eBookFactory;
	private BookFactory audioBookFactory;
	
	Library library; // Library dependency
	

	public LibrarianController() {

		// Initialize library
		this.library = new Library();

		// Use shared Singleton borrowing service
		this.borrowingService = BorrowingService.getInstance();

		// Initialize book factories
		this.paperBookFactory = new PaperBookFactory();
		this.eBookFactory = new EBookFactory();
		this.audioBookFactory = new AudioBookFactory();
	}

	public Library getLibrary() {
		return this.library;
	}
	public void showBooks() {
		library.showBooks();
	}
	public void showMembers() {
		library.showMembers();
	}

	// Add a traditional paper book using its factory
	public void addPaperBook(String title) {
	    addBook(paperBookFactory, title);
	}

	// Add an electronic book using its factory
	public void addEBook(String title) {
	    addBook(eBookFactory, title);
	}

	// Add an audio book using its factory
	public void addAudioBook(String title) {
	    addBook(audioBookFactory, title);
	}

	/**
	 * General factory-based method.
	 * Creates the appropriate book type based on the factory.
	 */
	public void addBook(BookFactory factory, String title) {

		if (factory == null) {
			throw new IllegalArgumentException(
				"Unsupported book factory."
			);
		}
		
	    library.addBook(factory.createBook(title));
	}

	public void addMember(String name) {
		library.addMember(new Member(name, borrowingService)); // Member class constructor dependency
	}
	public void removeBook(String title) {
		library.removeBook(title); // remove 
	}
	public void removeMember(String name) {
		library.removeMember(name);
	}
	public void showMember(String name) {
		Member member = library.findMemberByName(name);
		if (member != null)
			System.out.println(member);
		else 
			System.out.println("Member " + name + " not found.");
	}
	public void showBook(String title) {
		Book book = library.findBookByTitle(title);
		if (book != null)
			System.out.println(book);
		else 
			System.out.println("Book " + title + " not found.");
	}
	public void showMemberBooks(String name) {
		Member member = library.findMemberByName(name);
		if (member != null)
			member.listBorrowedBooks();
		else 
			System.out.println("Member " + name + " not found.");
	}
	
	public void borrowBookByMember(String title, String name) {
		Member member = library.findMemberByName(name); // use library for search
		Book book = library.findBookByTitle(title);  // use library for search
		if (book != null && member != null)
			member.borrowBook(book); // member borrows a book, not library 
		else 	
			System.out.println("Either book " + title + " or member " + name + " not found.");
	}
	
	public void returnBookByMember(String title, String name) {
		Member member = library.findMemberByName(name); // use library for search
		Book book = library.findBookByTitle(title); // use library for search 
		if (book != null && member != null)
			member.returnBook(book); // members returns book. 
		else  	
			System.out.println("Either book " + title + " or member " + name + " not found.");
	}
}
