package prr.observers;

import prr.notifications.Notification;

public interface Subject {

    public void register(Observer observer);

    public void notifyObservers(Notification notification);
}
