package prr.communications;

import prr.terminals.Terminal;

public class VoiceCommunication extends InteractiveCommunication {
    public VoiceCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }
}
