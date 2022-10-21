package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;

public class Busy extends TerminalState {

    public Busy(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "BUSY";
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
    public void startCommunication() throws BusyTerminalException {
        throw new BusyTerminalException();
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException, OffTerminalException, SilencedTerminalException {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void endCommunication() {
        // TODO Auto-generated method stub
    }
}
