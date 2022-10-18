package prr.terminals;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;

import prr.clients.Client;
import prr.communications.Communication;
import prr.terminals.states.*;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> /* FIXME maybe ad more interfaces */ {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    // attributes
    private String key;
    Client owner;
    private TerminalState state;
    private ArrayList<Communication> receivedComms = new ArrayList<>();
    private ArrayList<Communication> madeComms = new ArrayList<>();
    private ArrayList<String> friends = new ArrayList<>();

    // contructor(s)
    public Terminal(String key, Client owner, TerminalState state) {
        this.key = key;
        this.owner = owner;
        this.state = state;
    }

    public String getKey() {
        return this.key;
    }

    public void setState(TerminalState state) {
        this.state = state;
    }

    public void addFriend(String friend) {
        if (!friends.contains(friend))
            friends.add(friend);
    }

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
        return true;
    }

    public long getBalance() {
        return getPaid() - getOwed();
    }

    public long getOwed() {
        long total = 0;
        for (Communication c : madeComms) {
            if (!c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public long getPaid() {
        long total = 0;
        for (Communication c : madeComms) {
            if (c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public int getCommCount() {
        return receivedComms.size() + madeComms.size();
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
                .add(this.state.toString())
                .add(Long.toString(this.getPaid()))
                .add(Long.toString(this.getOwed()))
                .toString();

        if (friends.size() != 0) {
            s += "|";
            for (String f : friends)
                s += f + ",";

            // remove last comma
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }
}
