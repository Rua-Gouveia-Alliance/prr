package prr.exceptions;

/**
 * Exception thrown when trying to register a client with
 * a key that already exists.
 */
public class ClientExistsException extends KeyRelatedException {
    /** @param key the duplicated key */
    public ClientExistsException(String key) {
        super(key);
    }
}
