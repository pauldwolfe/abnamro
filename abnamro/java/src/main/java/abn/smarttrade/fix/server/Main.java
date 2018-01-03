package abn.smarttrade.fix.server;

import abn.smarttrade.fix.client.Titan;
import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    private static final String PATH = "/Users/pwolfe/Code/abnamro/java/src/main/resources/";

    public static void main(String[] args) {
        try {

            Application application = new SmartTrade();

            SessionSettings smartTradeSettings = new SessionSettings(
                    new FileInputStream(PATH + "smarttrade.properties"));
            MessageStoreFactory storeFactory = new FileStoreFactory(smartTradeSettings);
            LogFactory logFactory = new FileLogFactory(smartTradeSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            startSmartTrade(application, smartTradeSettings, storeFactory, logFactory, messageFactory);

            startTitan(storeFactory, messageFactory);

        } catch (Exception configError) {
            configError.printStackTrace();
        }
    }

    private static void startSmartTrade(Application application, SessionSettings smartTradeSettings, MessageStoreFactory
            storeFactory, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError {
        Acceptor acceptor = new SocketAcceptor(application, storeFactory, smartTradeSettings, logFactory,
                messageFactory);
        acceptor.start();
    }

    private static void startTitan(MessageStoreFactory storeFactory, MessageFactory messageFactory) throws
            ConfigError, FileNotFoundException {
        SessionSettings titanSettings = new SessionSettings(
                new FileInputStream(PATH + "titan.properties"));
        Application titan = new Titan();
        Initiator initiator = new SocketInitiator(titan, storeFactory, titanSettings, messageFactory);
        initiator.start();
    }
}
