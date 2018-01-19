package abn.prs;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer implements Player {

    private final ThreadLocalRandom localRandom;

    RandomPlayer(ThreadLocalRandom localRandom) {
        this.localRandom = localRandom;
    }

    @Override
    public Move move() {
        int random = localRandom.nextInt(0, 3);
        switch (random) {
            case 0:
                return Move.PAPER;
            case 1:
                return Move.ROCK;
            default:
                return Move.SCISSORS;
        }
    }
}
