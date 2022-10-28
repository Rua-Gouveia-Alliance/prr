package prr.notifications;

import prr.visitors.Printer;

public class BusyToIdleNotification extends Notification {
    public BusyToIdleNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
