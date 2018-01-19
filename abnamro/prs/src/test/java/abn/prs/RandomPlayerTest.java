package abn.prs;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomPlayerTest {

    private ThreadLocalRandom random;
    private RandomPlayer victim;

    @Before
    public void setUp() throws Exception {
        random = mock(ThreadLocalRandom.class);
        victim = new RandomPlayer(random);
    }

    @Test
    public void randomIsZeroReturnsPaper() {
        whenRandomIs(0);
        thenMoveIs(Move.PAPER);
    }

    @Test
    public void randomIsOneReturnsRock() {
        whenRandomIs(1);
        thenMoveIs(Move.ROCK);
    }

    @Test
    public void randomIsTwoReturnsScissors() {
        whenRandomIs(2);
        thenMoveIs(Move.SCISSORS);
    }

    private void whenRandomIs(int random) {
        when(this.random.nextInt(0, 3)).thenReturn(random);
    }

    private void thenMoveIs(Move paper) {
        assertEquals(paper, victim.move());
    }
}