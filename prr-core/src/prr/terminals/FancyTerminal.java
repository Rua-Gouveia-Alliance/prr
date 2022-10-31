package prr.terminals;

import prr.clients.Client;
import prr.visitors.Printer;
import prr.Network;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.InvalidDestinationException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.communications.VideoCommunication;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

    @Override
    protected void startVideoCommunication(Terminal receiver, Network network) throws InvalidDestinationException,
            BusyTerminalException, OffTerminalException, SilencedTerminalException {
        VideoCommunication communication = network.newVideoCommunication(this, receiver);
        getState().startCommunication();
        receiver.receiveVideoCommunication(communication);
        setCurrentCommunication(communication);
        registerMadeCommunication(communication);
        getOwner().increaseVideoCount();

    }

    @Override
    public void receiveVideoCommunication(VideoCommunication communication)
            throws OffTerminalException, SilencedTerminalException, BusyTerminalException {
        if (isOff()) {
            registerInteractiveCommunicationObserver((communication.getSender()).getOwner());
            throw new OffTerminalException(getKey());
        }
        if (isBusy()) {
            registerInteractiveCommunicationObserver((communication.getSender()).getOwner());
            throw new BusyTerminalException(getKey());
        }
        if (isSilenced()) {
            registerInteractiveCommunicationObserver((communication.getSender()).getOwner());
            throw new SilencedTerminalException(getKey());
        }
        getState().startCommunication();
        setCurrentCommunication(communication);
        registerReceivedCommunication(communication);
    }
}
