package abn.smarttrade.fix.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

public class Titan extends MessageCracker implements Application {

    private final Logger LOGGER = LoggerFactory.getLogger(Titan.class);

    public void onCreate(SessionID sessionID) {

    }

    public void onLogon(SessionID sessionID) {

    }

    public void onLogout(SessionID sessionID) {

    }

    public void toAdmin(Message message, SessionID sessionID) {

    }

    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat,
            IncorrectTagValue, RejectLogon {

    }

    public void toApp(Message message, SessionID sessionID) throws DoNotSend {

    }

    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat,
            IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID);
    }

    public void onMessage(quickfix.fix44.ExecutionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        LOGGER.info("Execution! {}", message);
    }

}
