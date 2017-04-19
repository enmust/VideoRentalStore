package inventory;

public class UnknownFilmTypeException extends RuntimeException {

    /**
     * Exception to be thrown when someone uses
     * wrong film type when changing film type or
     * paying with bonus points.
     * @param message to be shown when exception is thrown.
     */
    public UnknownFilmTypeException(String message) {
        super(message);
    }
}
