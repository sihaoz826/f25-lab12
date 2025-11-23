package edu.cmu.Library;

public class LibraryAccount {
    private LibraryService libraryService;
    
    /**
     * Constructs a new LibraryAccount with the specified LibraryService.
     *
     * @param libraryService the service to use for retrieving books
     */
    public LibraryAccount(LibraryService libraryService) {
        if (libraryService == null) {
            throw new IllegalArgumentException("LibraryService cannot be null");
        }
        this.libraryService = libraryService;
    }
 
    /**
     * Retrieves an array of checked out books associated with the specified user ID. If the user
     * has no books checked out, the returned list will be empty. Since multiple households may
     * share a single account, the user ID is of the form "libraryID:userName".
     * e.g., "12345:John Doe"
     *
     * @param userId the ID of the user whose books are to be retrieved
     * @return an array of Book objects the user has checked out
     * @throws InvalidUserIdException if the userId format is invalid
     */
    public Book[] getBooks(String userId) throws InvalidUserIdException {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidUserIdException("UserId cannot be null or empty");
        }
        
        String[] parts = userId.split(":");
        if (parts.length != 2) {
            throw new InvalidUserIdException("UserId must be in the format 'libraryID:userName', but got: " + userId);
        }
        
        String name = parts[0];
        String id = parts[1];
        
        if (name.isEmpty() || id.isEmpty()) {
            throw new InvalidUserIdException("UserId components cannot be empty. Expected format 'libraryID:userName', but got: " + userId);
        }
        
        return libraryService.getBooks(name, id);        
    }
}
