package prr.terminals;

import prr.clients.Client;
import prr.terminals.states.TerminalState;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String key, Client owner, TerminalState state) {
        super(key, owner, state);
    }

    @Override
    public String toString() {
        return "BASIC|" + super.toString();
    }

}
