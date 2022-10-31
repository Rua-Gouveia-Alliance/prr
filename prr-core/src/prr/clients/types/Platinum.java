package prr.clients.types;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Platinum extends ClientType {
    public Platinum(Client client) {
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
        // empty
    }

    @Override
    protected void downgrade() {
        if (client.getBalance() < 0)
            client.setType(client.getNormal());
        else
            client.setType(client.getGold());
    }

    @Override
    protected boolean canDowngrade() {
        return (client.getBalance() >= 0 && client.getTextCount() == 2)
                || client.getBalance() < 0;
    }

    @Override
    protected boolean canUpgrade() {
        return false;
    }

    @Override
    public String getLabel() {
        return "PLATINUM";
    }
}
