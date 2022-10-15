package prr.exceptions;

/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class ClientDoesntExistException extends KeyException {
    /** @param key the nonexistent key */
    public ClientDoesntExistException(String key) {
        super(key);
    }
}
