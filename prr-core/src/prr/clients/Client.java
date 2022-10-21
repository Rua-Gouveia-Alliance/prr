package prr.clients;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;

import prr.terminals.Terminal;
import prr.clients.types.ClientType;
import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.Communication;
import prr.communications.CommunicationType;
import prr.notifications.Notification;
import prr.notifications.DeliveryMethod;
import prr.notifications.ViaApp;

public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    private String name;
    private final int nif;
    private final String key;
    private ClientPlan plan = new BasePlan(this);
    private final ArrayList<Notification> notifications = new ArrayList<>();
    private final ArrayList<Terminal> terminals = new ArrayList<>();
    private final ClientType normal = new Normal(this);
    private final ClientType gold = new Gold(this);
    private final ClientType platinum = new Platinum(this);
    private DeliveryMethod deliveryMethod = new ViaApp(this);
    private ClientType type = new Normal(this);
    private boolean activeNotifications = true;

    public Client(String name, String key, int nif) {
        this.name = name;
        this.key = key;
        this.nif = nif;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public void setActiveNotifications(boolean notifications) {
        this.activeNotifications = notifications;
    }

    public ClientType getGold() {
        return gold;
    }

    public ClientType getNormal() {
        return normal;
    }

    public ClientType getPlatinum() {
        return platinum;
    }

    public ClientType getType() {
        return this.type;
    }

    public String getKey() {
        return this.key;
    }

    public boolean getActiveNotifications() {
        return this.activeNotifications;
    }

    public long getDebt() {
        long debt = 0;
        for (Terminal terminal : this.terminals)
            debt += terminal.getDebt();
        return debt;
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

    public void addTerminal(Terminal terminal) {
        this.terminals.add(terminal);
    }

    public int straightCommunications(CommunicationType type) {
        // TODO implement
        return 0;
    }

    public long getPrice(Communication communication) {
        return this.plan.getPrice(communication);
    }
    
    public void queueNotification(Notification notification) {
        notifications.add(notification);
    }

    public void notify(Notification notification) {
        deliveryMethod.deliver(notification);
    }

    @Override
    public String toString() {
        return new StringJoiner("|")
                .add("CLIENT")
                .add(this.key)
                .add(this.name)
                .add(Integer.toString(this.nif))
                .add(this.type.toString())
                .add(this.activeNotifications ? "YES" : "NO")
                .add(Integer.toString(this.terminals.size()))
                .add(Long.toString(this.getPaid()))
                .add(Long.toString(this.getDebt()))
                .toString();
    }
}
