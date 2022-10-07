package prr.communications;

import prr.clients.ClientTypeEnum;
import prr.comunications.CommunicationStatus;
import prr.comunications.CommunicationType;
import prr.terminals.Terminal;

public class Communication {
    private CommunicationStatus status = CommunicationStatus.ONGOING;
    private CommunicationType type;
    private ClientTypeEnum clientType;
    private long price = 0;
    private int length = 0;
    private int id;
    private boolean paid = false;
    private Terminal sender;
    private Terminal receiver;

    /* Video/Voice constructor */
    public Communication(CommunicationType type, ClientTypeEnum clientType, int id, Terminal sender,
            Terminal receiver) {
        this.type = type;
        this.clientType = clientType;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }

    /* Text constructor */
    public Communication(int length, ClientTypeEnum clientType, int id, Terminal sender, Terminal receiver) {
        this.length = length;
        this.type = CommunicationType.TEXT;
        this.clientType = clientType;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = CommunicationStatus.FINISHED;
    }

    public long getPrice() {
        return price;
    }

    public boolean getPaid() {
        return paid;
    }

    public void endCommunication(int length, long price) {
        this.status = CommunicationStatus.FINISHED;
        this.length = length;
        this.price = price;
    }

    public void payCommunication() {
        this.paid = true;
    }
}