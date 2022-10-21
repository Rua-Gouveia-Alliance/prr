package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;

public class Off extends TerminalState {

    public Off(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "OFF";
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() throws OffTerminalException {
        throw new OffTerminalException();
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
}
