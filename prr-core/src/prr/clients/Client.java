package prr.clients;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Client implements Serializables {
    String name;
    String key;
    int nif;
    ClientPlan plan = new BasePlan();
    ArrayList<Terminal> terminals = new ArrayList<>();
    ClientType type = new Normal(this);
    boolean notifications = false;

    public Client(String name, String key, int nif) {
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

    public long calcPrice(Communication communication) {
        return this.plan.calcPrice();
    }

    @Override
    public String toString() {
        return new StringJoiner("|")
                .add("CLIENT")
                .add(this.key)
                .add(this.name)
                .add(this.taxId)
                .add(this.type)
                .add(this.notifications ? "YES" : "NO")
                .add(this.terminals.getLength())
                .add(this.getPaid())
                .add(this.getOwed());

    }
}
