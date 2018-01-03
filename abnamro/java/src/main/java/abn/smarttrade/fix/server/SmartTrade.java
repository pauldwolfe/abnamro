package abn.smarttrade.fix.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;
import quickfix.field.*;

public class SmartTrade extends MessageCracker implements Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartTrade.class);

    public void onCreate(SessionID sessionID) {

    }

    public void onLogon(SessionID sessionID) {
        LOGGER.info("Logon event on the smartTrade side, sending execution to client", sessionID);

        try {
            quickfix.fix44.ExecutionReport message = new quickfix.fix44.ExecutionReport(new OrderID("1"),
                    new ExecID("1"),
                    new ExecType(ExecType.FILL),
                    new OrdStatus(OrdStatus.FILLED),
                    new Side(Side.BUY),
                    new LeavesQty(0.0),
                    new CumQty(1.0),
                    new AvgPx(1.0));
            message.set(new Symbol("EUR/USD"));
            message.set(new Text("Dummy trade"));

            Session.sendToTarget(message, "SMARTTRADE", "TITAN");
        } catch (SessionNotFound sessionNotFound) {
            LOGGER.error("Could not find session", sessionNotFound);
        }
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

    }
}
