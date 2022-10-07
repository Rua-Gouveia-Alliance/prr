package prr.clients;

import prr.comunications.CommunicationType;

public class Platinum extends ClientType {
    public Platinum(Client client) {
        super(client);
    }
    public ClientType getPrevType(){
        return (client.getBalance() < 0) ? new Normal(client) : new Gold(client);
    }
    public ClientType getNextType(){
        return new Platinum(client);
    }
    public boolean canDowngrade(){
        return (client.getBalance() >= 0 && client.straightCommunications(CommunicationType.TEXT) == 2) || client.getBalance() < 0;
        // a condicao pvv pode ser simplificada
        // a 2a ainda tem que ser considerado plat
    }
    public boolean canUpgrade(){
        return false;
    }
}
