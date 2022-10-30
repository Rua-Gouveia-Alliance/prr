package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.exceptions.TerminalDoesntExistException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

    DoRemoveFriend(Network context, Terminal terminal) {
        super(Label.REMOVE_FRIEND, context, terminal);
        addStringField("key", Prompt.clientKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.removeFriend(_network.getTerminal(stringField("key")));
        } catch (TerminalDoesntExistException e) {
            throw new UnknownTerminalKeyException(e.getKey());
        }
    }
}
