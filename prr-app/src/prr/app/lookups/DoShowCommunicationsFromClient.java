package prr.app.lookups;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.exceptions.ClientDoesntExistException;
import prr.app.exceptions.UnknownClientKeyException;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

    DoShowCommunicationsFromClient(Network receiver) {
        super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
        addStringField("client", Prompt.clientKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.getCommunicationsFromClient(stringField("client")));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}
