package prr.clients;

import prr.comunications.CommunicationType;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }

    @Override
    public ClientType getPrevType() {
        return new Normal(client);
    }

    @Override
    public ClientType getNextType() {
        return new Platinum(client);
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
}
