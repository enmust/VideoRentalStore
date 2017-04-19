package inventory;

public class FilmNotFoundInTheStoreException extends RuntimeException {

    /**
     * Exception to be thrown when someone tries
     * to work with the film which is not in the store.
     * @param message to be shown when exception is thrown.
     */
    public FilmNotFoundInTheStoreException(String message) {
        super(message);
    }
}
