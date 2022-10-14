package prr.clients;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializables {
    String name;
    String id;
    int nif;
    ClientPlan plan = new BasePlan();
    ArrayList<Terminal> terminals = new ArrayList<>();
    ClientType type = new Normal(this);
    boolean notifications = false;

    public Client(String name, String id, int nif) {
        this.name = name;
        this.id = id;
        this.nif = nif;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public ClientType getType() {
        return type;
    }

    public long getOwed() {
        long owed = 0;
        for (Terminal terminal : terminals)
            owed += terminal.getOwed();
        return owed;
    }

    public long getPaid() {
        long paid = 0;
        for (Terminal terminal : terminals)
            paid += terminal.getPaid();
        return paid;
    }

    public long getBalance() {
        long balance = 0;
        for (Terminal terminal : terminals)
            balance += terminal.getBalance();
        return balance;
    }

    public long calcPrice(Communication communication) {
        return plan.calcPrice();
    }
}
