package prr.clients;

import prr.Communications.Communication;
import prr.Communications.CommunicationType;

public class BasePlan implements ClientPlan {
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
        return (client.getType() instanceof Platinum) ? 4 : 2 * communication.GetDuration();
    }

    @Override
    public long calcVoicePrice(Communication communication) {
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

    @Override
    public long calcPrice(Communication communication) {
        switch (communication.getType()) {
            case CommunicationType.TEXT:
                return calcTextPrice(communication);
            case CommunicationType.VOICE:
                return calcVoicePrice(communication);
            default:
                return calcVideoPrice(communication);
        }
    }
}