package prr.visitors;

import prr.clients.Client;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.notifications.BusyToIdleNotification;
import prr.notifications.SilentToIdleNotification;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;

public interface Visitor {

    public String visit(Client client);

    public String visit(FancyTerminal terminal);

    public String visit(BasicTerminal terminal);

    public String visit(TextCommunication communication);

    public String visit(VoiceCommunication communication);

    public String visit(VideoCommunication communication);

    public String visit(OffToIdleNotification notification);

    public String visit(OffToSilentNotification notification);

    public String visit(BusyToIdleNotification notification);

    public String visit(SilentToIdleNotification notification);
}