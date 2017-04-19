package inventory;

class FilmAlreadyInTheStoreException extends RuntimeException {

    /**
     * Exception to be thrown when trying to
     * add the same film to store inventory.
     * @param message to be shown when exception is thrown.
     */
    FilmAlreadyInTheStoreException(String message) {
        super(message);
    }

}
