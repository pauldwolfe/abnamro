package abn.prs;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CommandLineInterface {

    private final PaperRockScissorsGame game;
    private final GameSummary summary;

    CommandLineInterface(PaperRockScissorsGame game) {
        this.game = game;
        this.summary = new GameSummary();
    }

    public GameSummary getSummary() {
        return summary;
    }

    void start() {
        Result result = null;
        while (notEnded(result)) {
            result = game.play();
            if (notEnded(result)) {
                System.out.println(String.format("You %s!", result.toString().toLowerCase()));
            }
            summary.update(result);
            System.out.println(summary.toString());
        }
    }

    private static boolean notEnded(Result result) {
        return result != Result.END;
    }

    public static void main(String[] args) {
        new CommandLineInterface(new PaperRockScissorsGame(
                new ConsolePlayer(new ScannerUserInput(new Scanner(System.in))),
                new RandomPlayer(ThreadLocalRandom.current()))).start();
    }
}
