package prr.notifications;

import prr.visitors.Printer;

public class OffToIdleNotification extends Notification {
    public OffToIdleNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
