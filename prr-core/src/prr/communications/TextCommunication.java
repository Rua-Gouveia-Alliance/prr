package prr.communications;

import prr.terminals.Terminal;
import prr.visitors.Printer;

public class TextCommunication extends Communication {

    public TextCommunication(int key, Terminal sender, Terminal receiver, int length) {
        super(key, sender, receiver, length);
    }

    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
