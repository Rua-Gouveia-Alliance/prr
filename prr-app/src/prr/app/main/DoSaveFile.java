package prr.app.main;

import prr.NetworkManager;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed
import java.io.IOException;
import prr.exceptions.MissingFileAssociationException;

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

    DoSaveFile(NetworkManager receiver) {
        super(Label.SAVE_FILE, receiver);
    }

    @Override
    protected final void execute() {
        try {
            try {
                _receiver.save();
            } catch (MissingFileAssociationException e1) {
                try {
                    _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
                } catch (IOException | MissingFileAssociationException e2) {
                    // Será que é para fazer isto???????????????????:
                    // throw new FileOpenFailedException(e2);
                }
            }
        } catch (IOException e) {
            // Será que é para fazer isto???????????????????:
            // throw new FileOpenFailedException(e);
        }
    }
}
