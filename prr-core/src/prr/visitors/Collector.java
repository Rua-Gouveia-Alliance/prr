package prr.visitors;

import prr.clients.Client;

/**
 * Class used to collect information
 * 
 * @param <T> the collected information type.
 */
public abstract class Collector<T> {

    private T info;
    
    /**
     * @param client the client from which info is collected
     * @return true if selected, false otherwise.
     */
    public abstract void visit(Client client);
    
    public void setInfo(T info) {
        this.info = info;
    }
    
    public T collected() {
        return info;
    }

}
