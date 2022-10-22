package prr.communications;

import java.io.Serial;
import java.io.Serializable;

import prr.terminals.Terminal;

public class Communication implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    private final int key;
    private final Terminal sender;
    private final Terminal receiver;
    private boolean paid = false;
    protected long price = 0;
    
    public Communication(int key, Terminal sender, Terminal receiver) {
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
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

    public void payCommunication() {
        this.paid = true;
    }
}