package prr.notifications;

import java.io.Serializable;

import prr.visitors.Printable;

public abstract class Notification implements Serializable, Printable {

    private final int terminalId;

    public Notification(int terminalId) {
        this.terminalId = terminalId;
    }

    public int getId() {
        return terminalId;
    }
}
