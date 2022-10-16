package prr;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;
import prr.util.NaturalLanguageTextComparator;
import prr.terminals.FancyTerminal;
import prr.terminals.BasicTerminal;
import prr.terminals.Terminal;
import prr.terminals.states.Idle;
import prr.terminals.states.Off;
import prr.terminals.states.Silence;
import prr.terminals.states.TerminalState;
import prr.exceptions.UnrecognizedEntryException;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.ClientExistsException;
import prr.exceptions.IncorrectTerminalKeyException;
import prr.exceptions.InvalidEntryException;
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
    private final Map<String, Terminal> terminals = new TreeMap<>(new NaturalLanguageTextComparator());

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
     * Get a client
     * 
     * @param key the key that identifies the client
     * @return The {@link Client} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Client getClient(String key) throws ClientDoesntExistException {
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

    /**
     * Get a terminal
     * 
     * @param key the key that identifies the terminal
     * @return The {@link Terminal} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Terminal getTerminal(String key) {
        return terminals.get(key);
    }

    /**
     * Get all terminals with no communication history
     * 
     * @return A {@link Collection} of terminals, sorted by their key
     */
    public Collection<Terminal> getUnusedTerminals() {
        ArrayList<Terminal> unused = new ArrayList<Terminal>();

        for (Terminal t : terminals.values()) {
            if (t.getCommCount() == 0)
                unused.add(t);
        }

        return unused;
    }

    /**
     * Register a new terminal in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoenstExistException if the given client doesnt exist
     */
    public void registerTerminal(String key, String type, String client)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException {
        registerTerminal(key, type, client, new Idle());
    }

    /**
     * Register a new terminal in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @param state  new terminal's initial state
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoenstExistException if the given client doesnt exist
     */
    public void registerTerminal(String key, String type, String client, TerminalState state)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException {
        if (terminals.containsKey(key))
            throw new TerminalExistsException(key);

        if (!clients.containsKey(client))
            throw new ClientDoesntExistException(client);

        if (!key.matches("[0-9]{6}"))
            throw new IncorrectTerminalKeyException(key);

        Terminal newTerminal;
        if (type.equals("FANCY")) {
            newTerminal = new FancyTerminal(key, clients.get(client));
        } else {
            newTerminal = new BasicTerminal(key, clients.get(client));
        }
        newTerminal.setState(state);
        terminals.put(key, newTerminal);

    }

    /**
     * Loads a client onto the network from an array of fields
     * 
     * @param line the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importClient(String[] line) {
        if (line.length != 4)
            throw new UnrecognizedEntryException(String.join("|", line));
        try {
            registerClient(line[1], line[2], line[3]);
        } catch (DuplicateClientKeyException e) {
            throw new InvalidEntryException(String.join("|", line), e);
        }
    }

    /**
     * Loads a terminal onto the network from an array of fields
     * 
     * @param line the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importTerminal(String[] line) throws UnrecognizedEntryException, InvalidEntryException {
        if (line.length != 4)
            throw new UnrecognizedEntryException(String.join("|", line));
        try {
            TerminalState state;
            switch (line[3]) {
                case "ON":
                    state = new Idle();
                case "OFF":
                    state = new Off();
                case "SILENCE":
                    state = new Silence();
                default:
                    throw new UnrecognizedEntryException(String.join("|", line));
            }
            registerTerminal(line[1], line[0], line[2], state);
        } catch (TerminalExistsException | IncorrectTerminalKeyException | ClientDoesntExistException e) {
            throw new InvalidEntryException(String.join("|", line), e);
        }
    }

    /**
     * Read the first field of a line and check what object is supposed to be
     * imported
     * 
     * @param line the line fields
     * @throws UnrecognizedEntryException if the first field isn't recognized
     */
    private void importObject(String[] line) throws UnrecognizedEntryException {
        switch (line[0]) {
            case "CLIENT":
                importClient(line);
            case "BASIC|FANCY":
                importTerminal(line);
            case "FRIENDS":
                importFriends(line);
            default:
                throw new UnrecognizedEntryException(String.join("|", line));
        }

    }

    /**
     * Read text input file and parse each line
     * 
     * @param filename name of the text input file
     * @return an {@link ArrayList} in wich each element represents the fields of a
     *         line
     * @throws IOException if there is an IO error while processing
     *                     the text file
     */
    public ArrayList<String[]> readFile(String filename) throws IOException {
        ArrayList<String[]> file = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (line = reader.readLine() != null)
                file.add(line.split("\\|"));
        }
        return file;
    }

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     * @throws IOException                if there is an IO error while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, InvalidEntryException, IOException {
        ArrayList<String[]> file = readFile(filename);
        for (String[] line : file)
            importObject(line);
    }
}