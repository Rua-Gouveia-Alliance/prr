package prr.clients.types;

import java.io.Serial;
import java.io.Serializable;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;

public abstract class ClientType implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    protected final Client client;

    public ClientType(Client client) {
        this.client = client;
    }

    abstract public double getPrice(TextCommunication communication, boolean isFriend);

    abstract public double getPrice(VoiceCommunication communication, boolean isFriend);

    abstract public double getPrice(VideoCommunication communication, boolean isFriend);

    abstract public String getLabel();

    abstract protected void upgrade();

    abstract protected void downgrade();

    abstract protected boolean canDowngrade();

    abstract protected boolean canUpgrade();

    public void update() {
        if (canUpgrade())
            upgrade();
        else if (canDowngrade())
            downgrade();
    }
}
