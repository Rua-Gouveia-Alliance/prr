package prr.clients;

import java.io.Serializable;

import prr.communications.Communication;
import prr.communications.InteractiveCommunication;
import prr.communications.Text;

public abstract class ClientPlan implements Serializable {

    protected final Client client;

    public ClientPlan(Client client) {
        this.client = client;
    }

    protected abstract long getTextPrice(Text communication);

    protected abstract long getInteractiveCommunicationPrice(InteractiveCommunication communication);

    public long getPrice(Communication communication) {
        if (communication instanceof Text)
            return getTextPrice((Text) communication);
        return getInteractiveCommunicationPrice((InteractiveCommunication) communication);
    }
}
