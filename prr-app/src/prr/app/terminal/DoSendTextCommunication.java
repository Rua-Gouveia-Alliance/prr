package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.exceptions.TerminalDoesntExistException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

    DoSendTextCommunication(Network context, Terminal terminal) {
        super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.sendText(Prompt.terminalKey(), Prompt.textMessage(), _network);
        } catch (TerminalDoesntExistException e) {
            throw new UnknownTerminalKeyException(e.getKey());
        }
    }
}
