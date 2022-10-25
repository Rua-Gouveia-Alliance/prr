package prr.notifications;

import java.io.Serializable;

public abstract class Notification implements Serializable {

    private final int terminalId;

    public Notification(int terminalId) {
        this.terminalId = terminalId;
    }
    
    public int getId() {
        return terminalId;
    }
}
