package prr.communications;

import prr.terminals.Terminal;
import prr.visitors.Printer;

public class VideoCommunication extends InteractiveCommunication {
    public VideoCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }

    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
