package prr.clients;

// Problema: temos que saber o tipo com que a comunicacao foi feita
// se ele mudar de tipo antes de pagar uma comm ja feita temos que cobrar
// de acordo com o tipo com que ela foi feita e nao com o tipo novo (acho eu)
public abstract class ClientType {
    private ClientPlan plan = new BasePlan(this);
    protected Client client;

    public ClientType(Client client) {
        this.client = client;
    }

    abstract public ClientType getPrevType();

    abstract public ClientType getNextType();

    abstract public boolean canDowngrade();

    abstract public boolean canUpgrade();

    public long calcPrice(Communication communication) {
        return plan.calcPrice(communication);
    }

    public void upgrade() {
        if (canUpgrade())
            client.setType(getNextType());
        else if (canDowngrade())
            client.setType(getPrevType());
    }
}
