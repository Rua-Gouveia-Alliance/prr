package prr.communications;

import java.io.Serial;
import java.io.Serializable;

import prr.terminals.Terminal;
import java.util.StringJoiner;

public abstract class Communication implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    private final int key;
    private final Terminal sender;
    private final Terminal receiver;
    private boolean paid = false;
    private boolean finished = false;
    private int units;
    private long price = 0;

    public Communication(int key, Terminal sender, Terminal receiver, int units) {
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
        this.units = units;
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

    public boolean isFinished() {
        return finished;
    }

    public int getUnits() {
        return units;
    }
    
    public void setUnits(int units) {
        this.units = units;
    }

    public void finish() {
        finished = true;
    }

    public void payCommunication() {
        this.paid = true;
    }

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