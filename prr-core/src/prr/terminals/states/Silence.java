package prr.terminals.states;

import prr.exceptions.SilencedTerminalException;
import prr.notifications.SilentToIdleNotification;
import prr.terminals.Terminal;

public class Silence extends TerminalState {

    public Silence(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() throws SilencedTerminalException {
        throw new SilencedTerminalException(terminal.getKey());
    }

    @Override
    public void toOff() {
        terminal.setState(terminal.getOffState());
    }

    @Override
    public void toOn() throws SilencedTerminalException {
        terminal.notifyInteractiveCommunicationObservers(new SilentToIdleNotification(terminal.getKey()));
        throw new SilencedTerminalException(terminal.getKey());
    }

    @Override
    public void receiveCommunication() throws SilencedTerminalException {
        throw new SilencedTerminalException(terminal.getKey());
    }

    @Override
    public void startCommunication() {
        terminal.saveState(this);
        terminal.setState(terminal.getBusyState());
    }

    @Override
    public void endCommunication() {

    }

    @Override
    public String getLabel() {
        return "SILENCE";
    }

}
