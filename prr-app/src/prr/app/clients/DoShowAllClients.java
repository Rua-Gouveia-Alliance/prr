package prr.app.clients;

import java.util.stream.Collectors;

import prr.Network;
import prr.clients.Client;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

    DoShowAllClients(Network receiver) {
        super(Label.SHOW_ALL_CLIENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Collection<Client> clients = _receiver.getAllClients();
        for (Client c : clients)
            _display.popup(c.toString());
    }
}
