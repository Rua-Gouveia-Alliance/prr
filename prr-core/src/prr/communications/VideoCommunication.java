package prr.communications;

import prr.terminals.Terminal;

public class VideoCommunication extends InteractiveCommunication {
    public VideoCommunication(int key, Terminal sender, Terminal receiver) {
        super(key, sender, receiver);
    }
}
