package prr.clients;

import prr.communications.CommunicationType;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }

    @Override
    public void upgrade() {
        client.setType(client.getPlatinum());
    }

    @Override
    public void downgrade() {
        client.setType(client.getNormal());
    }

    @Override
    public boolean canDowngrade() {
        return client.getBalance() < 0;
    }

    @Override
    public boolean canUpgrade() {
        return client.getBalance() >= 0 && client.straightCommunications(CommunicationType.VIDEO) == 5;
        // a 5a ainda tem que ser considerado gold
    }

    @Override
    public String toString() {
        return "GOLD";
    }
}
