package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.terminals.Terminal;

public class Off extends TerminalState {

    public Off(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() {
        terminal.notifyObservers(new OffToSilentNotification(terminal.getKey()));
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() throws OffTerminalException {
        throw new OffTerminalException();
    }

    @Override
    public void toOn() {
        terminal.notifyObservers(new OffToIdleNotification(terminal.getKey()));
        terminal.setState(terminal.getIdleState());
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException, OffTerminalException, SilencedTerminalException {
        // TODO Auto-generated method stub

    }

    @Override
    public void startCommunication() throws OffTerminalException {
        throw new OffTerminalException();
    }

    @Override
    public void endCommunication() {
        // TODO Auto-generated method stub
    }

    @Override
    public String getLabel() {
        return "OFF";
    }

}
