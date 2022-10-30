package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

    DoTurnOnTerminal(Network context, Terminal terminal) {
        super(Label.POWER_ON, context, terminal);
    }

    @Override
    protected final void execute() throws CommandException {
        // TODO: IMPLEMENT toIDLE (turn on terminal)
        try {
            _receiver.toIdle();
        } catch ( e) {
            _display.popup(Message.alreadyOn());
        } catch ( e) {
            // TODO: do nothing right?
        }
    }
}
