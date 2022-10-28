package prr.communications;

import prr.terminals.Terminal;
import prr.visitors.Printer;

public class VoiceCommunication extends InteractiveCommunication {
    public VoiceCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }

    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
