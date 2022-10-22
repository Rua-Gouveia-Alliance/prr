package prr.clients;

import java.io.Serial;

import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.Text;
import prr.communications.CommunicationType;
import prr.communications.InteractiveCommunication;

public class BasePlan extends ClientPlan {

    @Serial
    private static final long serialVersionUID = 202222102220L;

    public BasePlan(Client client) {
        super(client);
    }

    private long getCallPrice(InteractiveCommunication communication) {
        return (client.getType() instanceof Normal) ? 20 : 10;
    }

    private long getVideoPrice(InteractiveCommunication communication) {
        if (client.getType() instanceof Normal)
            return 30;
        if (client.getType() instanceof Gold)
            return 20;
        return 10;
    }

    @Override
    protected long getInteractiveCommunicationPrice(InteractiveCommunication communication) {
        if (communication.getType() == CommunicationType.CALL)
            return getCallPrice(communication);
        return getVideoPrice(communication);
    }

    @Override
    protected long getTextPrice(Text text) {
        if (text.getLength() < 50)
            return (client.getType() instanceof Platinum) ? 0 : 10;
        if (text.getLength() < 100) {
            if (client.getType() instanceof Gold)
                return 10;
            if (client.getType() instanceof Normal)
                return 16;
            return 4;
        }
        return (client.getType() instanceof Platinum) ? 4 : 2 * text.getLength();
    }

}