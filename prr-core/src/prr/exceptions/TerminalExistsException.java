package prr.exceptions;

/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class TerminalExistsException extends KeyRelatedException {
    
    private static final long serialVersionUID = 202217101700L;
    
    public TerminalExistsException(String key) {
        super(key);
    }
}
