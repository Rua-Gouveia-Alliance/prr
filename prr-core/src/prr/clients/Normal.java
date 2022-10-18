package prr.clients;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    @Override
    public void upgrade() {
        client.setType(client.getGold());
    }

    @Override
    public void downgrade() {
        // empty
    }

    @Override
    public boolean canDowngrade() {
        return false;
    }

    @Override
    public boolean canUpgrade() {
        return client.getBalance() > 500;
    }

    @Override
    public String toString() {
        return "NORMAL";
    }
}
