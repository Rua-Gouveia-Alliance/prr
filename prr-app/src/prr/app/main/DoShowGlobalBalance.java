package prr.app.main;

import javax.sound.midi.Receiver;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

    DoShowGlobalBalance(Network receiver) {
        super(Label.SHOW_GLOBAL_BALANCE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        _display.popup(Message.globalPaymentsAndDebts(_receiver.getGlobalPayments(), _receiver.getGlobalDebts()));
    }
}
