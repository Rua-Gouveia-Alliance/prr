package prr.terminals;

import java.io.Serializable;
import java.util.ArrayList;

import prr.clients.Client;
import prr.terminals.states.TerminalState;
import prr.communications.Communication;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> /* FIXME maybe ad more interfaces */ {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private String key;
    Client owner;
    private TerminalState state;
    private ArrayList<Communication> receivedComms = new ArrayList<>();
    private ArrayList<Communication> madeComms = new ArrayList<>();
    private ArrayList<Terminal> friends = new ArrayList<>();

    // FIXME define contructor(s)
    // FIXME define methods

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive
     *         communication) and
     *         it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        // FIXME add implementation code
        return false;
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        // FIXME add implementation code
        return false;
    }

    public long getBalance() {
        return 0;
    }

    public long getOwed() {
        return 0;
    }

    public long getPaid() {
        return 0;
    }

    @Override
    public int compareTo(Terminal o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
