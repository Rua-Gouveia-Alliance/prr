package prr.clients;

import prr.comunications.CommunicationType;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }

    public ClientType getPrevType() {
        return new Normal(client);
    }

    public ClientType getNextType() {
        return new Platinum(client);
    }

    public boolean canDowngrade() {
        return client.getBalance() < 0;
    }

    public boolean canUpgrade() {
        return client.getBalance() >= 0 && client.straightCommunications(CommunicationType.VIDEO) == 5;
        // a 5a ainda tem que ser considerado gold
    }
}
