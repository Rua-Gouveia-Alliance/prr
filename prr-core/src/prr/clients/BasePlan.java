package prr.clients;

import java.io.Serial;

import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;

public class BasePlan extends ClientPlan {

    @Serial
    private static final long serialVersionUID = 202222102220L;

    @Override
    public long getPrice(TextCommunication text, Normal type, boolean isFriend) {
        if (text.getUnits() < 50)
            return 10;
        if (text.getUnits() < 100)
            return 16;
        return 2 * text.getUnits();
    }

    @Override
    public long getPrice(TextCommunication text, Gold type, boolean isFriend) {
        if (text.getUnits() < 100)
            return 10;
        return 2 * text.getUnits();
    }

    @Override
    public long getPrice(TextCommunication text, Platinum type, boolean isFriend) {
        if (text.getUnits() < 50)
            return 0;
        return 4;
    }

    @Override
    public long getPrice(VoiceCommunication communication, Normal type, boolean isFriend) {
        return 20;
    }

    @Override
    public long getPrice(VoiceCommunication communication, Gold type, boolean isFriend) {
        return 10;
    }

    @Override
    public long getPrice(VoiceCommunication communication, Platinum type, boolean isFriend) {
        return 10;
    }

    @Override
    public long getPrice(VideoCommunication communication, Normal type, boolean isFriend) {
        return 30;
    }

    @Override
    public long getPrice(VideoCommunication communication, Gold type, boolean isFriend) {
        return 20;
    }

    @Override
    public long getPrice(VideoCommunication communication, Platinum type, boolean isFriend) {
        return 10;
    }

}