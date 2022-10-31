package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.notifications.BusyToIdleNotification;
import prr.terminals.Terminal;

public class Busy extends TerminalState {

    public Busy(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() throws BusyTerminalException {
        throw new BusyTerminalException();
    }

    @Override
    public void toOn() throws BusyTerminalException {
        terminal.notifyObservers(new BusyToIdleNotification(terminal.getKey()));
        throw new BusyTerminalException();
    }

    @Override
    public void startCommunication() throws BusyTerminalException {
        throw new BusyTerminalException();
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException {
        throw new BusyTerminalException();
    }

    @Override
    public void endCommunication() {
        terminal.setState(terminal.getSavedState());
    }

    @Override
    public String getLabel() {
        return "BUSY";
    }

}
