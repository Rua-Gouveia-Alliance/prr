package prr.clients.types;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }
    
    @Override
    public long getPrice(TextCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    public long getPrice(VideoCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    public long getPrice(VoiceCommunication communication) {
        return client.getPlan().getPrice(communication, this);
    }

    @Override
    protected void upgrade() {
        client.setType(client.getPlatinum());
    }

    @Override
    protected void downgrade() {
        client.setType(client.getNormal());
    }

    @Override
    protected boolean canDowngrade() {
        return client.getBalance() < 0;
    }

    @Override
    protected boolean canUpgrade() {
        return client.getBalance() >= 0 && client.getVideoCount() == 5;
    }

    @Override
    public String getLabel() {
        return "GOLD";
    }
}
