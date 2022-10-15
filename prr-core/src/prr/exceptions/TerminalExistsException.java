package prr.exceptions;

/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class TerminalExistsException extends KeyException {
    public TerminalExistsException(String key) {
        super(key);
    }
}
