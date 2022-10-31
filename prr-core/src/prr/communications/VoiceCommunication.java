package prr.communications;

import prr.terminals.Terminal;
import prr.visitors.Printer;

public class VoiceCommunication extends InteractiveCommunication {
    public VoiceCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

    @Override
    public void updatePrice(boolean isFriend) {
        setPrice(getSender().getOwner().getPrice(this, isFriend));
    }
}
