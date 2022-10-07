package prr.clients;

import prr.Communications.Communication;
import prr.Communications.CommunicationType;

public class BasePlan implements ClientPlan {
    @Override
    public long calcTextPrice(Communication communication) {
        if (communication.getLength() < 50)
            return (communication.getClientType() == ClientTypeEnum.PLATINUM) ? 0 : 10;
        if (communication.getLength() < 100) {
            if (communication.getClientType() == ClientTypeEnum.NORMAL)
                return 16;
            if (communication.getClientType() == ClientTypeEnum.GOLD)
                return 10;
            return 4;
        }
        return (communication.getClientType() == ClientTypeEnum.PLATINUM) ? 4 : 2 * communication.duration;
    }

    @Override
    public long calcVoicePrice(Communication communication) {
        return (communication.getClientType() == ClientTypeEnum.NORMAL) ? 20 : 10;
    }

    @Override
    public long calcVideoPrice(Communication communication) {
        if (communication.getClientType() == ClientTypeEnum.NORMAL)
            return 30;
        if (communication.getClientType() == ClientTypeEnum.GOLD)
            return 20;
        return 10;
    }

    @Override
    public long calcPrice(Communication communication) {
        switch (communication.getType()) {
            case CommunicationType.TEXT:
                return calcTextPrice(communication);
            case CommunicationType.VOICE:
                return calcVoicePrice(communication);
            default:
                return calcVideoPrice(communication);
        }
    }
}