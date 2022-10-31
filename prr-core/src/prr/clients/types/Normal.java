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
    public double getPrice(TextCommunication communication, boolean isFriend) {
        return client.getPlan().getPrice(communication, this, isFriend);
    }

    @Override
    public double getPrice(VoiceCommunication communication, boolean isFriend) {
        return client.getPlan().getPrice(communication, this, isFriend);
    }

    @Override
    public double getPrice(VideoCommunication communication, boolean isFriend) {
        return client.getPlan().getPrice(communication, this, isFriend);
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
