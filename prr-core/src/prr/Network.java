package prr;

import java.io.Serializable;
import java.util.Map;
import java.io.IOException;
import prr.exceptions.UnrecognizedEntryException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException                if there is an IO erro while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        // FIXME implement method
    }
}