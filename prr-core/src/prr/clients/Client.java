package prr.clients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;

import prr.terminals.Terminal;
import prr.communications.Communication;
import prr.communications.CommunicationType;

public class Client implements Serializable {
    String name;
    String key;
    String nif;
    ClientPlan plan = new BasePlan(this);
    ArrayList<Terminal> terminals = new ArrayList<>();
    ClientType type = new Normal(this);
    boolean notifications = false;

    public Client(String name, String key, String nif) {
        this.name = name;
        this.key = key;
        this.nif = nif;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public ClientType getType() {
        return this.type;
    }

    public long getOwed() {
        long owed = 0;
        for (Terminal terminal : this.terminals)
            owed += terminal.getOwed();
        return owed;
    }

    public long getPaid() {
        long paid = 0;
        for (Terminal terminal : this.terminals)
            paid += terminal.getPaid();
        return paid;
    }

    public long getBalance() {
        long balance = 0;
        for (Terminal terminal : this.terminals)
            balance += terminal.getBalance();
        return balance;
    }

    public int straightCommunications(CommunicationType type) {
        return 0;
    }

    public long calcPrice(Communication communication) {
        return this.plan.calcPrice(communication);
    }

    @Override
    public String toString() {
        return new StringJoiner("|")
                .add("CLIENT")
                .add(this.key)
                .add(this.name)
                .add(this.nif)
                .add(this.type.toString())
                .add(this.notifications ? "YES" : "NO")
                .add(Integer.toString(this.terminals.size()))
                .add(Long.toString(this.getPaid()))
                .add(Long.toString(this.getOwed()))
                .toString();

    }
}
