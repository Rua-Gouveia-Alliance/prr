package prr.communications;

import prr.terminals.Terminal;

public abstract class InteractiveCommunication extends Communication {

    public InteractiveCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver, 0);
    }

    public long endCommunication(int duration) {
        this.finish();
        this.setUnits(duration);
        this.updatePrice();
        return getPrice();
    }

}
