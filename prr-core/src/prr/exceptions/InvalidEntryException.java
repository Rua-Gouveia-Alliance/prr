package prr.exceptions;

/**
 * Exception for invalid import file entries.
 */
public class InvalidEntryException extends Exception {

    /** Class serial number. */
    private static final long serialVersionUID = 202208091753L;

    /** Invalid entry specification. */
    private String entrySpecification;

    /**
     * @param entrySpecification
     */
    public InvalidEntryException(String entrySpecification) {
        _entrySpecification = entrySpecification;
    }

    /**
     * @param entrySpecification
     * @param cause
     */
    public InvalidEntryException(String entrySpecification, Exception cause) {
        super(cause);
        _entrySpecification = entrySpecification;
    }

    /**
     * @return the invalid entry specification.
     */
    public String getEntrySpecification() {
        return entrySpecification;
    }

}
