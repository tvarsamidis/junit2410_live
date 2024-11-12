package gr.codehub.mockito.p05_exception_handling;

public class DatabaseWriteException extends RuntimeException {
    public DatabaseWriteException(String message) {
        super(message);
    }
}
