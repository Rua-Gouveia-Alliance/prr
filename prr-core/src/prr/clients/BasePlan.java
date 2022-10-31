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

    private final double NORMAL_VOICE = 20;
    private final double GOLD_VOICE = 10;
    private final double PLATINUM_VOICE = 10;

    private final double NORMAL_VIDEO = 30;
    private final double GOLD_VIDEO = 20;
    private final double PLATINUM_VIDEO = 10;

    @Override
    public double getPrice(TextCommunication text, Normal type, boolean isFriend) {
        if (text.getUnits() < 50)
            return 10;
        if (text.getUnits() < 100)
            return 16;
        return 2 * text.getUnits();
    }

    @Override
    public double getPrice(TextCommunication text, Gold type, boolean isFriend) {
        if (text.getUnits() < 100)
            return 10;
        return 2 * text.getUnits();
    }

    @Override
    public double getPrice(TextCommunication text, Platinum type, boolean isFriend) {
        if (text.getUnits() < 50)
            return 0;
        return 4;
    }

    @Override
    public double getPrice(VoiceCommunication communication, Normal type, boolean isFriend) {
        if (isFriend)
            return NORMAL_VOICE * 0.5;
        return NORMAL_VOICE;
    }

    @Override
    public double getPrice(VoiceCommunication communication, Gold type, boolean isFriend) {
        if (isFriend)
            return GOLD_VOICE * 0.5;
        return GOLD_VOICE;
    }

    @Override
    public double getPrice(VoiceCommunication communication, Platinum type, boolean isFriend) {
        if (isFriend)
            return PLATINUM_VOICE * 0.5;
        return PLATINUM_VOICE;
    }

    @Override
    public double getPrice(VideoCommunication communication, Normal type, boolean isFriend) {
        if (isFriend)
            return NORMAL_VIDEO * 0.5;
        return NORMAL_VIDEO;
    }

    @Override
    public double getPrice(VideoCommunication communication, Gold type, boolean isFriend) {
        if (isFriend)
            return GOLD_VIDEO * 0.5;
        return GOLD_VIDEO;
    }

    @Override
    public double getPrice(VideoCommunication communication, Platinum type, boolean isFriend) {
        if (isFriend)
            return PLATINUM_VIDEO * 0.5;
        return PLATINUM_VIDEO;
    }

}