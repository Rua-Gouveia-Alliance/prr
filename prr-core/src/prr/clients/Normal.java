package prr.clients;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    @Override
    public ClientType getPrevType() {
        return new Normal(client);
    }

    @Override
    public ClientType getNextType() {
        return new Gold(client);
    }

    @Override
    public boolean canDowngrade() {
        return false;
    }

    @Override
    public boolean canUpgrade() {
        return client.getBalance() > 500;
    }
}
