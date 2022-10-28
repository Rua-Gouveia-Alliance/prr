package prr.terminals;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;

import prr.clients.Client;
import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.FailedContactException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.states.*;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    // attributes
    private String key;
    private Client owner;
    private ArrayList<Communication> receivedCommunications = new ArrayList<>();
    private ArrayList<Communication> madeCommunications = new ArrayList<>();
    private ArrayList<Client> failedContacts = new ArrayList<>();
    private ArrayList<String> friends = new ArrayList<>();

    // Terminal State
    private final TerminalState busyState = new Busy(this);
    private final TerminalState idleState = new Idle(this);
    private final TerminalState offState = new Off(this);
    private final TerminalState silenceState = new Silence(this);
    private TerminalState savedState;
    private TerminalState state;

    // contructor(s)
    public Terminal(String key, Client owner) {
        this.key = key;
        this.owner = owner;

        // Default state
        this.state = idleState;
    }

    public String getKey() {
        return this.key;
    }

    public void setState(TerminalState state) {
        this.state = state;
    }

    public void saveState(TerminalState state) {
        savedState = state;
    }

    public TerminalState getSavedState() {
        return savedState;
    }

    public String getOwnerKey() {
        return this.owner.getKey();
    }

    public TerminalState getState() {
        return this.state;
    }

    public TerminalState getBusyState() {
        return this.busyState;
    }

    public TerminalState getIdleState() {
        return this.idleState;
    }

    public TerminalState getOffState() {
        return this.offState;
    }

    public TerminalState getSilenceState() {
        return this.silenceState;
    }

    public Collection<Communication> getReceivedCommunications() {
        return receivedCommunications;
    }

    public Collection<Communication> getMadeCommunications() {
        return madeCommunications;
    }

    public void toSilence() throws SilencedTerminalException {
        state.toSilence();
    }

    public void toOff() throws BusyTerminalException, OffTerminalException {
        state.toOff();
    }

    public boolean isSilenced() {
        return getState().equals(getSilenceState());
    }

    public boolean isOff() {
        return getState().equals(getOffState());
    }

    public boolean isBusy() {
        return getState().equals(getBusyState());
    }

    public boolean isIdle() {
        return getState().equals(getIdleState());
    }

    public void addFriend(String friend) {
        if (!friends.contains(friend))
            friends.add(friend);
    }

    public void sendText(TextCommunication text) {
        madeCommunications.add(text);
    }

    public void receiveText(Client sender, TextCommunication text) throws FailedContactException {
        if (isOff()) {
            failedContacts.add(sender);
            throw new FailedContactException();
        }
        receivedCommunications.add(text);
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive
     *         communication) and
     *         it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        return isBusy(); // TODO && it was the originator of this communication.
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        return !isBusy() && !isOff();
    }

    public long getBalance() {
        return getPaid() - getDebt();
    }

    public long getDebt() {
        long total = 0;
        for (Communication c : madeCommunications) {
            if (!c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public long getPaid() {
        long total = 0;
        for (Communication c : madeCommunications) {
            if (c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public int getCommunicationCount() {
        return receivedCommunications.size() + madeCommunications.size();
    }

}
