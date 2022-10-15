package prr.exceptions;

/**
 * Exception thrown when trying to register a client with
 * a key that already exists.
 * Used to pass the duplicate key to the app.
 */
public class ClientExistsException extends Exception {

    private final String key;

    /** @param key the duplicated key */
    public ClientExistsException(String key) {
        this.key = key;
    }

    /** @return The key that caused the error */
    public String getKey() {
        return this.key;
    }
}
