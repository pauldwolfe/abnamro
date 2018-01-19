package abn.prs;

class PaperRockScissorsGame {

    private final Player human;
    private final Player computer;

    PaperRockScissorsGame(Player human, Player computer) {
        this.human = human;
        this.computer = computer;
    }

    Result play() {

        Move humanMove = human.move();
        Move computerMove = computer.move();
        System.out.println(String.format("You played [%s] and the computer played [%s]", humanMove, computerMove));
        if (humanMove == Move.QUIT) {
            return Result.END;
        }

        if (notTie(humanMove, computerMove)) {
            switch (humanMove) {
                case ROCK:
                    return humanWins(computerMove, Move.SCISSORS);
                case PAPER:
                    return humanWins(computerMove, Move.ROCK);
                case SCISSORS:
                    return humanWins(computerMove, Move.PAPER);
            }
        }
        return Result.TIE;
    }

    private static boolean notTie(Move humanMove, Move computerMove) {
        return humanMove != computerMove;
    }

    private static Result humanWins(Move computerMove, Move losingMove) {
        return computerMove == losingMove ? Result.WIN : Result.LOSE;
    }
}
