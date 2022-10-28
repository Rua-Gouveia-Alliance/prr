package prr.clients;

import java.io.Serial;

import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.Text;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.CommunicationType;
import prr.communications.InteractiveCommunication;

public class BasePlan extends ClientPlan {

    @Serial
    private static final long serialVersionUID = 202222102220L;

    private long getCallPrice(InteractiveCommunication communication, Normal type) {
        return 20;
    }

    private long getCallPrice(InteractiveCommunication communication, Gold type) {
        return 10;
    }

    private long getCallPrice(InteractiveCommunication communication, Platinum type) {
        return 10;
    }

    private long getVideoPrice(InteractiveCommunication communication, Normal type) {
        return 30;
    }

    private long getVideoPrice(InteractiveCommunication communication, Gold type) {
        return 20;
    }

    private long getVideoPrice(InteractiveCommunication communication, Platinum type) {
        return 10;
    }

    @Override
    protected long getInteractiveCommunicationPrice(VoiceCommunication communication, ClientType type) {
        return getCallPrice(communication, type);
    }

    @Override
    protected long getInteractiveCommunicationPrice(VideoCommunication communication, ClientType type) {
        return getVideoPrice(communication, type);
    }

    @Override
    protected long getTextPrice(Text text, Normal type) {
        if (text.getLength() < 50)
            return 10;
        if (text.getLength() < 100)
            return 16;
        return 2 * text.getUnits();
    }

    @Override
    protected long getTextPrice(Text text, Gold type) {
        if (text.getLength() < 100)
            return 10;
        return 2 * text.getLength();
    }

    @Override
    protected long getTextPrice(Text text, Platinum type) {
        if (text.getLength() < 50)
            return 0;
        return 4;
    }

}