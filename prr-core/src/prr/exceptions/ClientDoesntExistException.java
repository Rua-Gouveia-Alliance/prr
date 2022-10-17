package prr.exceptions;

/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class ClientDoesntExistException extends KeyRelatedException {
    
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the nonexistent key */
    public ClientDoesntExistException(String key) {
        super(key);
    }
}
