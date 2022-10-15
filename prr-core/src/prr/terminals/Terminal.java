package prr.terminals;

import java.io.Serializable;
import java.util.ArrayList;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)
import prr.terminals.states.TerminalState;
import prr.communications.Communication;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> /* FIXME maybe ad more interfaces */ {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    // attributes
    private String key;
    Client owner;
    private TerminalState state;
    private ArrayList<Communication> receivedComms;
    private ArrayList<Communication> madeComms;
    private ArrayList<Terminal> friends;

    // contructor(s)
    public Terminal(String key, Client owner) {
        this.key = key;
        this.owner = owner;

        this.state = new Idle();
        this.receivedComms = new ArrayList<Communication>();
        this.madeComms = new ArrayList<Communication>();
        this.friends = new ArrayList<Terminal>();

    }

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

    @Override
    public int compareTo(Terminal o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        String s = new StringJoiner("|")
                .add(this.key)
                .add(this.owner.getKey())
                .add(this.state)
                .add(this.getPaid())
                .add(this.getOwed());

        if (friends.getLenght() != 0) {
            s += "|";
            for (Terminal f : friends) {
                s += f.getKey() + ",";
            }

            // remove last ,
            s = s.substring(0, s.legth - 1);
        }

        return s;
    }
}
