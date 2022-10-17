package prr.exceptions;

/**
 * Exception thrown when trying to register a terminal with
 * a key that doesnt't respect the rules established (string with six numerical
 * characters)
 */
public class IncorrectTerminalKeyException extends KeyRelatedException {
    
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the invalid key */
    public IncorrectTerminalKeyException(String key) {
        super(key);
    }
}
