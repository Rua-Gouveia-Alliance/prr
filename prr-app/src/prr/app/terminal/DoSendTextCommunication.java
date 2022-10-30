package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.communications.TextCommunication;
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
            Terminal destination = _network.getTerminal(Form.requestString(Prompt.terminalKey()));
            TextCommunication comm = _network.newTextCommunication(_receiver, destination,
                    Form.requestString(Prompt.textMessage()));
            _receiver.sendText(comm);
        } catch (TerminalDoesntExistException e) {
            throw new UnknownTerminalKeyException(e.getKey());
        }
    }
}
