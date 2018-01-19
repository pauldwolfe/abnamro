package abn.prs;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsolePlayerTest {

    private ConsolePlayer victim;
    private UserInput input;

    @Before
    public void setUp() throws Exception {
        input = mock(UserInput.class);
        victim = new ConsolePlayer(input);
    }

    @Test
    public void inputRreturnsRockMove() {
        when(input.input()).thenReturn("r");
        assertEquals(Move.ROCK, victim.move());
    }

    @Test
    public void inputRockreturnsRockMove() {
        when(input.input()).thenReturn("rock");
        assertEquals(Move.ROCK, victim.move());
    }

    @Test
    public void inputSreturnsScissorsMove() {
        when(input.input()).thenReturn("s");
        assertEquals(Move.SCISSORS, victim.move());
    }

    @Test
    public void inputScissorsreturnsScissorsMove() {
        when(input.input()).thenReturn("scissors");
        assertEquals(Move.SCISSORS, victim.move());
    }

    @Test
    public void inputPreturnsPaperMove() {
        when(input.input()).thenReturn("p");
        assertEquals(Move.PAPER, victim.move());
    }

    @Test
    public void inputPaperreturnsPaperMove() {
        when(input.input()).thenReturn("paper");
        assertEquals(Move.PAPER, victim.move());
    }

    @Test
    public void inputQreturnsQuitMove() {
        when(input.input()).thenReturn("q");
        assertEquals(Move.QUIT, victim.move());
    }

    @Test
    public void inputQuitreturnsQuitMove() {
        when(input.input()).thenReturn("quit");
        assertEquals(Move.QUIT, victim.move());
    }
}