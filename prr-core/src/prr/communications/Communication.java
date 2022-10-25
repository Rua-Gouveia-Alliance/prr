package prr.communications;

import java.io.Serial;
import java.io.Serializable;

import prr.terminals.Terminal;
import java.util.StringJoiner;

public abstract class Communication implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    private final CommunicationType type;
    private final int key;
    private final Terminal sender;
    private final Terminal receiver;
    private boolean paid = false;
    protected long price = 0;
    private CommunicationStatus status = CommunicationStatus.ONGOING;

    public Communication(int key, Terminal sender, Terminal receiver, CommunicationType type) {
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
    }

    public int getKey() {
        return key;
    }

    public Terminal getSender() {
        return sender;
    }

    public Terminal getReceiver() {
        return receiver;
    }

    public long getPrice() {
        return price;
    }

    public boolean isPaid() {
        return paid;
    }

    public CommunicationType getType() {
        return type;
    }

    public CommunicationStatus getStatus() {
        return status;
    }

    public void setStatus(CommunicationStatus status) {
        this.status = status;
    }

    public void payCommunication() {
        this.paid = true;
    }

    public abstract int getUnits();

    @Override
    public String toString() {
        String s = new StringJoiner("|")
                .add(this.type.toString())
                .add(Integer.toString(this.key))
                .add(this.sender.getKey())
                .add(this.receiver.toString())
                .add(Long.toString(this.getUnits()))
                .add(Long.toString(this.price))
                .add(this.status.toString())
                .toString();

        return s;
    }
}