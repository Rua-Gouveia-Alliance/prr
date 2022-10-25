package prr.communications;

import prr.terminals.Terminal;

public class TextCommunication extends Communication {

    public TextCommunication(int key, Terminal sender, Terminal receiver, int length) {
        super(key, sender, receiver, length);
    }

}
