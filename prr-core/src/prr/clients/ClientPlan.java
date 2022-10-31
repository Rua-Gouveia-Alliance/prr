package prr.clients;

import java.io.Serializable;

import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;

public abstract class ClientPlan implements Serializable {

    public abstract double getPrice(TextCommunication text, Normal type, boolean isFriend);

    public abstract double getPrice(TextCommunication text, Gold type, boolean isFriend);

    public abstract double getPrice(TextCommunication text, Platinum type, boolean isFriend);

    public abstract double getPrice(VoiceCommunication communication, Normal type, boolean isFriend);

    public abstract double getPrice(VoiceCommunication communication, Gold type, boolean isFriend);

    public abstract double getPrice(VoiceCommunication communication, Platinum type, boolean isFriend);

    public abstract double getPrice(VideoCommunication communication, Normal type, boolean isFriend);

    public abstract double getPrice(VideoCommunication communication, Gold type, boolean isFriend);

    public abstract double getPrice(VideoCommunication communication, Platinum type, boolean isFriend);
}
