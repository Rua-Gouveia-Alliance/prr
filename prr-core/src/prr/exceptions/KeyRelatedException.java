package prr.exceptions;

/**
 * Basic exception class for client key related issues.
 * Mustn't be instaciated.
 */
public abstract class KeyException extends Exception {

    private final String key;

    /** @param key */
    public KeyException(String key) {
        this.key = key;
    }

    /** @return The key that caused the error */
    public String getKey() {
        return this.key;
    }
}
