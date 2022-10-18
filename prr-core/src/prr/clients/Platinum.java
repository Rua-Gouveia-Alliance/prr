package prr.clients;

import prr.communications.CommunicationType;

public class Platinum extends ClientType {
    public Platinum(Client client) {
        super(client);
    }

    @Override
    public void upgrade() {
        // empty
    }

    @Override
    public void downgrade() {
        if (client.getBalance() < 0)
            client.setType(client.getNormal());
        else
            client.setType(client.getGold());
    }

    @Override
    public boolean canDowngrade() {
        return (client.getBalance() >= 0 && client.straightCommunications(CommunicationType.TEXT) == 2)
                || client.getBalance() < 0;
        // a condicao pvv pode ser simplificada
        // a 2a ainda tem que ser considerado plat
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public String toString() {
        return "PLATINUM";
    }
}
