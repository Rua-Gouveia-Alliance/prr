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
        return Message.offToSilent() + "|" + notification.getId();
    }

    @Override
    public String visit(OffToIdleNotification notification) {
        return Message.offToIdle() + "|" + notification.getId();
    }

    @Override
    public String visit(BusyToIdleNotification notification) {
        return Message.busyToIdle() + "|" + notification.getId();
    }

    @Override
    public String visit(SilentToIdleNotification notification) {
        return Message.silentToIdle() + "|" + notification.getId();
    }

    @Override
    public String visit(FancyTerminal terminal) {
        return Message.fancyTerminal() + "|" + terminal.getKey() + "|" + terminal.getOwnerKey() + "|" + "" + "|"
                + terminal.getPaid() + "|" + terminal.getDebt();
    }

    // TODO add state
    @Override
    public String visit(BasicTerminal terminal) {
        return Message.basicTerminal() + "|" + terminal.getKey() + "|" + terminal.getOwnerKey() + "|" + "" + "|"
                + terminal.getPaid() + "|" + terminal.getDebt();
    }

    @Override
    public String visit(TextCommunication communication) {
        return Message.textCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey() + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|" + communication.getPrice() + "|"
                + (communication.isFinished() ? Message.ongoingCommunication() : Message.finishedCommunication());
    }

    @Override
    public String visit(VideoCommunication communication) {
        return Message.videoCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey() + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|" + communication.getPrice() + "|"
                + (communication.isFinished() ? Message.ongoingCommunication() : Message.finishedCommunication());
    }

    @Override
    public String visit(VoiceCommunication communication) {
        return Message.voiceCommunication() + "|" + communication.getKey() + "|" + communication.getSenderKey() + "|"
                + communication.getReceiverKey() + "|" + communication.getUnits() + "|" + communication.getPrice() + "|"
                + (communication.isFinished() ? Message.ongoingCommunication() : Message.finishedCommunication());
    }

}
