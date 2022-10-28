package prr.notifications;

import prr.visitors.Printer;

public class OffToIdleNotification extends Notification {
    public OffToIdleNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }
}
