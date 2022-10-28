package prr.app.clients;

import prr.Network;

import prr.exceptions.ClientDoesntExistException;
import prr.visitors.Collector;
import prr.visitors.Selector;
import prr.clients.Client;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

    DoShowClientPaymentsAndDebts(Network receiver) {
        super(Label.SHOW_CLIENT_BALANCE, receiver);
        addStringField("key", Prompt.key());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String key = stringField("key");
            Collector<Long> paymentsCollector = new Collector<>() {
                @Override
                public void visit(Client client) {
                    setInfo(client.getPaid());
                }
            };
            Collector<Long> debtCollector = new Collector<>() {
                @Override
                public void visit(Client client) {
                    setInfo(client.getDebt());
                }
            };
            Selector<Client> selector = new Selector<>() {
                @Override
                public boolean ok(Client c) {
                    return (c.getKey()).equals(key);
                };
            };
            _receiver.checkClient(key);
            _receiver.acceptPaymentsAndDebtsCollector(selector, paymentsCollector);
            _receiver.acceptPaymentsAndDebtsCollector(selector, debtCollector);
            _display.popup(
                    Message.clientPaymentsAndDebts(key, paymentsCollector.collected(), debtCollector.collected()));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}
