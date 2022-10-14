package prr.communications;

import java.io.Serializable;

import prr.clients.ClientTypeEnum;
import prr.comunications.CommunicationStatus;
import prr.comunications.CommunicationType;
import prr.terminals.Terminal;

public class Communication implements Serializable {
    private int id;
    private Terminal sender;
    private Terminal receiver;
    private int length = 0;
    private long price = 0;
    private boolean paid = false;
    private ClientTypeEnum clientType;
    private CommunicationType type;
    private CommunicationStatus status = CommunicationStatus.ONGOING;

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

    public int getId() {
        return id;
    }

    public Terminal getSender() {
        return sender;
    }

    public Terminal getReceiver() {
        return receiver;
    }

    public int getLength() {
        return length;
    }

    public long getPrice() {
        return price;
    }

    public boolean getPaid() {
        return paid;
    }

    public ClientTypeEnum getClientType() {
        return clientType;
    }

    public CommunicationType getType() {
        return type;
    }

    public CommunicationStatus getStatus() {
        return status;
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