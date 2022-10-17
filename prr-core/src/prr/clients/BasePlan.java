package prr.clients;

import java.io.Serializable;

import prr.communications.Communication;

public class BasePlan extends ClientPlan {
    
    private Client client;

    public BasePlan(Client client) {
        this.client = client;
    }

    @Override
    public long calcTextPrice(Communication communication) {
        if (communication.getLength() < 50)
            return (client.getType() instanceof Platinum) ? 0 : 10;
        if (communication.getLength() < 100) {
            if (client.getType() instanceof Gold)
                return 10;
            if (client.getType() instanceof Normal)
                return 16;
            return 4;
        }
        return (client.getType() instanceof Platinum) ? 4 : 2 * communication.getLength();
    }

    @Override
    public long calcCallPrice(Communication communication) {
        return (client.getType() instanceof Normal) ? 20 : 10;
    }

    @Override
    public long calcVideoPrice(Communication communication) {
        if (client.getType() instanceof Normal)
            return 30;
        if (client.getType() instanceof Gold)
            return 20;
        return 10;
    }
}