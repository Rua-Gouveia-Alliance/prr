package prr.app.clients;

import prr.Network;
import prr.clients.Client;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.visitors.Selector;
import prr.app.visitors.Renderer;

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

    DoShowAllClients(Network receiver) {
        super(Label.SHOW_ALL_CLIENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        Renderer renderer = new Renderer();
        Selector<Client> selector = new Selector<>() {
        };
        _receiver.acceptClientPrinter(selector, renderer);
        _display.addAll(renderer.render());
        _display.display();
    }
}
