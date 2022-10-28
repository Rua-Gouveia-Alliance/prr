package prr.app.lookups;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.exceptions.ClientDoesntExistException;
import prr.clients.Client;
import java.util.Collection;
import prr.visitors.Collector;
import prr.visitors.Selector;
import prr.communications.Communication;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

    DoShowCommunicationsFromClient(Network receiver) {
        super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
        addStringField("client", Prompt.clientKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String key = stringField("key");
            Collector<Collection<Communication>> paymentsCollector = new Collector<>() {
                @Override
                public void visit(Client client) {
                    setInfo(client.getPaid());
                }
            };
            Selector<Client> selector = new Selector<>() {
                @Override
                public boolean ok(Client c) {
                    return (c.getKey()).equals(key);
                };
            };
            _receiver.checkClient(key);
            _receiver.acceptCommunicationsCollector(selector, paymentsCollector);
            _display.popup(_receiver.getCommunicationsFromClient(stringField("client")));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}
