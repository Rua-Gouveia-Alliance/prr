package prr.terminals;

import prr.clients.Client;
import prr.terminals.states.TerminalState;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner, TerminalState state) {
        super(key, owner, state);
    }

    @Override
    public String toString() {
        return "FANCY|" + super.toString();
    }

}
