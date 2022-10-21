package prr.notifications;

import java.io.Serial;
import java.io.Serializable;

public record Notification(NotificationType type, int terminalId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 202217101700L;
}
