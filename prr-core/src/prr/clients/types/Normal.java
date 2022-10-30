package prr.clients.types;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    @Override
    public long getPrice(TextCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    public long getPrice(VoiceCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    public long getPrice(VideoCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    protected void upgrade() {
        client.setType(client.getGold());
    }

    @Override
    protected void downgrade() {
        // empty
    }

    @Override
    protected boolean canDowngrade() {
        return false;
    }

    @Override
    protected boolean canUpgrade() {
        return client.getBalance() > 500;
    }

    @Override
    public String getLabel() {
        return "NORMAL";
    }
}
