package prr;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;

import prr.app.exceptions.DuplicateClientKeyException;
import prr.clients.Client;
import prr.exceptions.UnrecognizedEntryException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** Clients list, sorted by key */
    /** FIXME Add proper text comparator to TreeMap */
    private final Map<String, Client> clients = new TreeMap<>();

    /** Terminals list, sorted by key */
    /** FIXME Add proper text comparator to TreeMap */
    private final Map<String, Client> terminals = new TreeMap<>();

    /** Communications counter, used for communication IDs */
    private int communicationCount = 0;

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws IOException                if there is an IO error while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
        // FIXME implement method
    }

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param name new client's name
     * @param key  new client's key
     * @param nif  new client's tax id
     * @throws DuplicateClientKeyException if the given key is already in use
     */
    public void newClient(String name, String key, int nif) throws DuplicateClientKeyException {
        if (clients.containsKey(key))
            throw new DuplicateClientKeyException(key);
        clients.put(key, new Client(name, key, nif));
    }
}