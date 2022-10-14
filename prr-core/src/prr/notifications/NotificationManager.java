package prr.notifications;

import java.io.Serializable;
import java.util.ArrayList;

public class NotificationManager implements Serializable {
    private NotificationMethod method = new ViaApp();
    private ArrayList<Notification> notifications = new ArrayList<>();
    
    public NotificationManager(NotificationMethod method) {
        this.method = method;
    }

    public void clearNotifications() {

    }

    public void addNotification() {

    }

    public void deliver() {
        method.deliver(notifications);
    }
    
}