package prr.clients;
import java.util.ArrayList;

public class Client {
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
    public long getOwed() {
        long owed = 0;
        for (Terminal terminal : terminals)
            balance += terminal.getOwed();
        return owed;
    }
    public long getPaid() {
        long paid = 0;
        for (Terminal terminal : terminals)
            balance += terminal.getPaid();
        return paid;
    }
    public long getBalance() {
        return getPaid() - getOwed();
    }
    public long calcPrice(Communication communication) {
        return plan.calcPrice();
    }
}
