package prr.communications;

import prr.clients.ClientType;

public class Communication {
    private CommunicationStatus status;
    private CommunicationType type;
    private ClientTypeEnum clientType;
    private long price;
    private int duration;
    private int id;
    private boolean paid = false;
    private Terminal sender;
    private Terminal receiver;
}
