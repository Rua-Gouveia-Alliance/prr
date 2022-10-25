package prr.communications;

import prr.terminals.Terminal;

public class InteractiveCommunication extends Communication {

    private int duration = 0;

    public InteractiveCommunication(int key, Terminal sender, Terminal receiver, CommunicationType type) {
        super(key, sender, receiver, type);
    }

    // TODO: necessary?
    public int getDuration() {
        return duration;
    }

    public void endCommunication(int duration, long price) {
        this.setStatus(CommunicationStatus.FINISHED);
        this.duration = duration;
        this.price = price;
    }

    @Override
    public int getUnits() {
        return this.getDuration();
    }
}
