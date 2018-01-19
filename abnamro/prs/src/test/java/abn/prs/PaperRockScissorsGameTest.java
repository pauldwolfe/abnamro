package abn.prs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaperRockScissorsGameTest {

    private Player human;
    private Player computer;
    private PaperRockScissorsGame victim;

    @Before
    public void setUp() throws Exception {
        human = mock(Player.class);
        computer = mock(Player.class);
        victim = new PaperRockScissorsGame(human, computer);
    }

    @Test
    public void humanAndComputerBothPlayRockIsTie() {
        moves(Move.ROCK, Move.ROCK);
        assertEquals(Result.TIE, victim.play());
    }

    @Test
    public void humanAndComputerBothPlayPaperIsTie() {
        moves(Move.PAPER, Move.PAPER);
        assertEquals(Result.TIE, victim.play());
    }

    @Test
    public void humanAndComputerBothPlayScissorsIsTie() {
        moves(Move.SCISSORS, Move.SCISSORS);
        assertEquals(Result.TIE, victim.play());
    }

    @Test
    public void humanRockComputerScissorsHumanWins() {
        moves(Move.ROCK, Move.SCISSORS);
        assertEquals(Result.WIN, victim.play());
    }

    @Test
    public void humanScissorsComputerPaperHumanWins() {
        moves(Move.SCISSORS, Move.PAPER);
        assertEquals(Result.WIN, victim.play());
    }

    @Test
    public void humanPaperComputerRockHumanWins() {
        moves(Move.PAPER, Move.ROCK);
        assertEquals(Result.WIN, victim.play());
    }

    @Test
    public void humanPaperComputerScissorsHumanLoses() {
        moves(Move.PAPER, Move.SCISSORS);
        assertEquals(Result.LOSE, victim.play());
    }

    @Test
    public void humanScissorsComputerRockHumanLoses() {
        moves(Move.SCISSORS, Move.ROCK);
        assertEquals(Result.LOSE, victim.play());
    }

    @Test
    public void humanRockComputerPaperHumanLoses() {
        moves(Move.ROCK, Move.PAPER);
        assertEquals(Result.LOSE, victim.play());
    }

    private void moves(Move rock, Move scissors) {
        when(human.move()).thenReturn(rock);
        when(computer.move()).thenReturn(scissors);
    }
}