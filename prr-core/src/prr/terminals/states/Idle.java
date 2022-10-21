package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;

public class Idle extends TerminalState {

    public Idle(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "IDLE";
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() {
        terminal.setState(terminal.getOffState());
    }

    @Override
    public void startCommunication() {
        // TODO Auto-generated method stub

    }

    @Override
    public void receiveCommunication() throws BusyTerminalException, OffTerminalException, SilencedTerminalException {
        // TODO Auto-generated method stub

    }

    @Override
    public void endCommunication() {
        // TODO Auto-generated method stub
        //
    }
}
