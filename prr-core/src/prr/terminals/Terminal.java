package prr.terminals;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import prr.Network;
import prr.clients.Client;
import prr.notifications.Notification;
import prr.communications.Communication;
import prr.communications.InteractiveCommunication;
import prr.communications.TextCommunication;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.FailedContactException;
import prr.exceptions.IdleTerminalException;
import prr.exceptions.InvalidCommunicationException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.exceptions.TerminalDoesntExistException;
import prr.terminals.states.*;
import prr.visitors.Printable;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Printable {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    private String key;
    private Client owner;
    private TreeMap<Integer, Communication> receivedCommunications = new TreeMap<>();
    private TreeMap<Integer, Communication> madeCommunications = new TreeMap<>();
    private ArrayList<Client> failedContacts = new ArrayList<>();
    private ArrayList<String> friends = new ArrayList<>();
    private InteractiveCommunication currentCommunication;

    private final TerminalState busyState = new Busy(this);
    private final TerminalState idleState = new Idle(this);
    private final TerminalState offState = new Off(this);
    private final TerminalState silenceState = new Silence(this);
    private TerminalState savedState;
    private TerminalState state;

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

    public Client getOwner() {
        return this.owner;
    }

    public String getOwnerKey() {
        return this.owner.getKey();
    }

    public TerminalState getState() {
        return this.state;
    }

    public String getStateLabel() {
        return this.state.getLabel();
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
        return receivedCommunications.values();
    }

    public Collection<Communication> getMadeCommunications() {
        return madeCommunications.values();
    }

    public void toSilence() throws SilencedTerminalException {
        state.toSilence();
    }

    public void toOff() throws BusyTerminalException, OffTerminalException {
        state.toOff();
    }

    public void toOn() throws BusyTerminalException, SilencedTerminalException, IdleTerminalException {
        state.toOn();
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

    public void addFriend(String friend, Network network) throws TerminalDoesntExistException {
        network.checkTerminal(friend);
        if (!friends.contains(friend))
            friends.add(friend);
    }

    public void removeFriend(String friend, Network network) throws TerminalDoesntExistException {
        network.checkTerminal(friend);
        if (friends.contains(friend))
            friends.remove(friend);
    }

    public void sendText(String receiverKey, String text, Network network) throws TerminalDoesntExistException, FailedContactException {
        Terminal receiver = network.getTerminal(receiverKey);
        TextCommunication communication = network.newTextCommunication(this, receiver, text);
        receiver.receiveText(communication);
        madeCommunications.put(communication.getKey(), communication);
    }

    public void receiveText(TextCommunication text) throws FailedContactException {
        if (isOff()) {
            failedContacts.add((text.getSender()).getOwner());
            throw new FailedContactException();
        }
        receivedCommunications.put(text.getKey(), text);
    }

    public void notifyFailedContacts(Notification notification) {
        for (Client client : failedContacts)
            client.deliver(notification);
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive
     *         communication) and
     *         it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        return isBusy() && madeCommunications.keySet().contains(currentCommunication.getKey());
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        return !isBusy() && !isOff();
    }

    public long endCommunication(int units) {
        return currentCommunication.endCommunication(units);
    }

    public void startCommunication(InteractiveCommunication com) {
        // TODO: implement
    }

    public InteractiveCommunication getCurrentCommunication() {
        return this.currentCommunication;
    }

    public long getBalance() {
        return getPaid() - getDebt();
    }

    public long getDebt() {
        long total = 0;
        for (Communication c : madeCommunications.values()) {
            if (!c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public long getPaid() {
        long total = 0;
        for (Communication c : madeCommunications.values()) {
            if (c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public void payCommunication(int key) throws InvalidCommunicationException {
        Communication communication = madeCommunications.get(key);

        if (communication == null || !communication.isFinished() || communication.isPaid())
            throw new InvalidCommunicationException();

        communication.payCommunication();
    }

    public int getCommunicationCount() {
        return receivedCommunications.size() + madeCommunications.size();
    }

}
