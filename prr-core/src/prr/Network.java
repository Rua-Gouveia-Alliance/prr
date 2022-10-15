package prr;

import java.io.Serializable;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;
import prr.util.NaturalLanguageTextComparator;
import prr.exceptions.UnrecognizedEntryException;
import prr.terminals.FancyTerminal;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.ClientExistsException;
import prr.exceptions.TerminalExistsException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** Clients list, sorted by key */
    private final Map<String, Client> clients = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Terminals list, sorted by key */
    private final Map<String, Client> terminals = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Communications counter, used for generating communication keys */
    private int communicationKey = 0;

    /**
     * Get and increment the communication key tracker
     */
    public int getCommunicationKey() {
        return communicationKey++;
    }

    /**
     * Register a new client in the network
     * 
     * @param name new client's name
     * @param key  new client's key
     * @param nif  new client's tax id
     * @throws ClientExistsException if the given key is already in use
     */
    public void registerClient(String key, String name, String nif) throws ClientExistsException {
        if (clients.containsKey(key))
            throw new ClientExistsException(key);
        clients.put(key, new Client(name, key, nif));
    }

    /**
     * Get all clients registered in the network
     * 
     * @return A {@link Collection} of clients, sorted by their key
     */
    public Collection<Client> getAllClients() {
        return clients.values();
    }

    /**
     * Get all clients registered in the network
     * 
     * @return A {@link Collection} of clients, sorted by their key
     */
    public Client getClients(String key) throws ClientDoesntExistException {
        if (!clients.containsKey(key))
            throw new ClientDoesntExistException(key);
        return clients.get(key);
    }

    /**
     * Get all terminals registered in the network
     * 
     * @return A {@link Collection} of terminals, sorted by their key
     */
    public Collection<Terminal> getAllTerminals() {
        return terminals.values();
    }

    public Terminal getTerminalObj(String key) {
        return terminals.get(key);
    }

    /**
     * Register new terminal
     * 
     * @return False if key already registered (failed), otherwise true
     */
    public void registerTerminal(String key, String type, String client)
            throws TerminalExistsException, ClientDoesntExistException {
        if (terminals.get(key) != null)
            throw new TerminalExistsException(key);

        if (clients.get(client) == null)
            throw new ClientDoesntExistException(client);

        Terminal newTerminal;
        if (type.equals("FANCY")) {
            newTerminal = new FancyTerminal(key, clients.get(client));
        } else if (type.equals("BASIC")) {
            newTerminal = new BasicTerminal(key, clients.get(client));
        }

        terminals.put(key, newTerminal);

    }

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
}