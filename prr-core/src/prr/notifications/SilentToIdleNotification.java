package prr.notifications;

import prr.visitors.Printer;

public class SilentToIdleNotification extends Notification {
    public SilentToIdleNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
