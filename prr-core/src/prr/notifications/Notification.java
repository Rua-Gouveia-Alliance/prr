package prr.notifications;

import java.io.Serializable;

public record Notification(NotificationType type, int terminalId) implements Serializable {
}
