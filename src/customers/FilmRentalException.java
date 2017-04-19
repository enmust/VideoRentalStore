package customers;

class FilmRentalException extends RuntimeException {

    /**
     * Exception to be thrown when film rental status
     * does not match with the requirements.
     * @param message to be shown when exception is thrown.
     */
    FilmRentalException(String message) {
        super(message);
    }

}
