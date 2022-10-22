package prr.communications;

import prr.terminals.Terminal;

public class Text extends Communication {

    private final int length;

    public Text(int key, Terminal sender, Terminal receiver, int length) {
        super(key, sender, receiver);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
