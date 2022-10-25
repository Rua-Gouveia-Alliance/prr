package prr.communications;

import prr.terminals.Terminal;

public class Text extends Communication {

    private final int length;

    public Text(int key, Terminal sender, Terminal receiver, int length) {
        super(key, sender, receiver, CommunicationType.TEXT);
        this.length = length;
    }

    // TODO: necessary?
    public int getLength() {
        return length;
    }

    @Override
    public int getUnits() {
        return this.getLength();
    }

}
