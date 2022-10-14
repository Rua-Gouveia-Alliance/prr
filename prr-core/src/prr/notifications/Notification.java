package prr.notifications;

import java.io.Serializable;

public class Notification implements Serializable {
    private NotificationType type;
    private int terminalId;

    public Notification(NotificationType type, int terminalId) {
        this.type = type;
        this.terminalId = terminalId;
    }
    
    public NotificationType getType() {
        return type;
    }

    public int getTerminalId() {
        return terminalId;
    }

}
