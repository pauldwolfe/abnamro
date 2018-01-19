package abn.smarttrade.fix.client;

import quickfix.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    private static final String PATH = "/Users/pwolfe/Code/abnamro/java/src/main/resources/";

    public static void main(String[] args) {
        try {

            SessionSettings smartTradeSettings = new SessionSettings(
                    new FileInputStream(PATH + "smarttrade.properties"));
            MessageStoreFactory storeFactory = new FileStoreFactory(smartTradeSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            startTitan(storeFactory, messageFactory);

            while (true){
                Thread.sleep(1000);
            }

        } catch (Exception configError) {
            configError.printStackTrace();
        }
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
