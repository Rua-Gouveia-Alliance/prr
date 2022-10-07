package prr.clients;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    public ClientType getPrevType() {
        return new Normal(client);
    }

    public ClientType getNextType() {
        return new Gold(client);
    }

    public boolean canDowngrade() {
        return false;
    }

    public boolean canUpgrade() {
        return client.getBalance() > 500;
    }
}
