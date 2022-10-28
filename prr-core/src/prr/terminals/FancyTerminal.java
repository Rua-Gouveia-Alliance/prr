package prr.terminals;

import prr.clients.Client;
import prr.visitors.Printer;

public class FancyTerminal extends Terminal {

    public FancyTerminal(String key, Client owner) {
        super(key, owner);
    }

    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
