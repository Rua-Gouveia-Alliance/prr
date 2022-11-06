package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import prr.clients.Client;

class DoShowClientsWithLowestPayments extends Command<Network> {

    DoShowClientsWithLowestPayments(Network receiver) {
        super(Label.SHOW_CLIENTS_WITH_LOWEST_PAYMENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Client> selector = new Selector<>() {
            @Override
            public boolean ok(Client client) {
                return client.getPaid() == _receiver.getLowestTotalPayments();
            }
        };
        _receiver.acceptSimpleClientPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
