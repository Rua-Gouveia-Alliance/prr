package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import prr.terminals.Terminal;

class DoShowTerminalsWithHighestPayments extends Command<Network> {

    DoShowTerminalsWithHighestPayments(Network receiver) {
        super(Label.SHOW_TERMINALS_WITH_HIGHEST_PAYMENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Terminal> selector = new Selector<>() {
            @Override
            public boolean ok(Terminal terminal) {
                return terminal.getPaid() == _receiver.getHighestTotalPayments();
            }
        };
        _receiver.acceptTerminalPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
