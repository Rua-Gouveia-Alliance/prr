package prr.clients;

import java.io.Serializable;

import prr.clients.types.ClientType;
import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.communications.InteractiveCommunication;

public abstract class ClientPlan implements Serializable {

    protected abstract long getTextPrice(Text communication, ClientType type);

    protected abstract long getInteractiveCommunicationPrice(InteractiveCommunication communication, ClientType type);

    public long getPrice(TextCommunication communication, ClientType type) {
        return getTextPrice(communication, type);
    }

    public long getPrice(InteractiveCommunication communication, ClientType type) {
        return getInteractiveCommunicationPrice(communication, type);
    }
}
