package prr.clients;

import java.io.Serializable;

import prr.communications.Communication;
import prr.communications.CommunicationType;

public abstract class ClientPlan implements Serializable {

    private static final long serialVersionUID = 202217101700L;

    public abstract long calcTextPrice(Communication communication);

    public abstract long calcCallPrice(Communication communication);

    public abstract long calcVideoPrice(Communication communication);

    public long calcPrice(Communication communication) {
        CommunicationType type = communication.getType();
        if (type == CommunicationType.TEXT)
            return calcTextPrice(communication);
        if (type == CommunicationType.CALL)
            return calcCallPrice(communication);
        return calcVideoPrice(communication);
    }
}
