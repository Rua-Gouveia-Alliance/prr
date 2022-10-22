package prr.communications;

import prr.terminals.Terminal;

public class InteractiveCommunication extends Communication {

    private final CommunicationType type;
    private CommunicationStatus status = CommunicationStatus.ONGOING;
    private int duration = 0;

    public InteractiveCommunication(int key, Terminal sender, Terminal receiver, CommunicationType type) {
        super(key, sender, receiver);
        this.type = type;
    }

    public CommunicationType getType() {
        return type;
    }

    public CommunicationStatus getStatus() {
        return status;
    }

    public int getDuration() {
        return duration;
    }

    public void endCommunication(int duration, long price) {
        this.status = CommunicationStatus.FINISHED;
        this.duration = duration;
        this.price = price;
    }
}
