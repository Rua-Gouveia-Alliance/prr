package prr.app.visitors;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.notifications.BusyToIdleNotification;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.notifications.SilentToIdleNotification;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;
import prr.visitors.Visitor;

public class Printer implements Visitor {

    // TODO fix client.getType().tostring()
    @Override
    public String visit(Client client) {
        return Message.client() + "|" + client.getKey() + "|" + client.getName() + "|" + client.getNif() + "|"
                + client.getType().toString() + "|"
                + (client.getActiveNotifications() ? Message.activeNotifications() : Message.inactiveNotifications())
                + "|" + client.getTerminalCount() + "|" + client.getPaid() + client.getDebt();
    }

    @Override
    public String visit(OffToSilentNotification notification) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(OffToIdleNotification notification) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(BusyToIdleNotification notification) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(SilentToIdleNotification notification) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FancyTerminal terminal) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(BasicTerminal communication) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(VideoCommunication communication) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(VoiceCommunication communication) {
        // TODO Auto-generated method stub
        return null;
    }

}
