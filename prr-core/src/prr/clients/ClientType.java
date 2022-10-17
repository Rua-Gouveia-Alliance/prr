package prr.clients;

import java.io.Serializable;

public abstract class ClientType implements Serializable {

    private static final long serialVersionUID = 202217101700L;
    
    protected Client client;

    public ClientType(Client client) {
        this.client = client;
    }

    abstract public ClientType getPrevType();

    abstract public ClientType getNextType();

    abstract public boolean canDowngrade();

    abstract public boolean canUpgrade();

    public void upgrade() {
        if (canUpgrade())
            client.setType(getNextType());
        else if (canDowngrade())
            client.setType(getPrevType());
    }
}
