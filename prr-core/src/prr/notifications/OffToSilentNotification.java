package prr.notifications;

import prr.visitors.Printer;

public class OffToSilentNotification extends Notification {
    public OffToSilentNotification(int terminalId) {
        super(terminalId);
    }
    
    @Override
    public String accept(Printer visitor) {
        return visitor.visit(this);
    }
}
