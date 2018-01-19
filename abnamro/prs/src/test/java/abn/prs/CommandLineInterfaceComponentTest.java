package abn.prs;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class CommandLineInterfaceComponentTest {

    @Test
    public void playAgainstRandomPlayer() {
        CommandLineInterface victim = new CommandLineInterface(new PaperRockScissorsGame(new StagedPlayer(), new
                RandomPlayer(ThreadLocalRandom.current())));
        victim.start();
        GameSummary summary = victim.getSummary();
        assertEquals(2, summary.getLosses() + summary.getWins() + summary.getTies());
    }

    static class StagedPlayer implements Player {
        int moveCount = 0;

        @Override
        public Move move() {
            Move move;
            if (moveCount == 0) {
                move = Move.ROCK;
            } else if (moveCount == 1) {
                move = Move.SCISSORS;
            } else {
                move = Move.QUIT;
            }
            moveCount++;
            return move;
        }
    }
}