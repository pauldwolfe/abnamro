package abn.prs;

public class ConsolePlayer implements Player {

    private final UserInput userInput;

    ConsolePlayer(UserInput userInput) {
        this.userInput = userInput;
    }

    @Override
    public Move move() {
        System.out.println("Please enter a input Rock(r), Paper(p), Scissors(s), or Quit(q) to exit");
        while (true) {
            String input = userInput.input();
            switch (input.toLowerCase()) {
                case "r":
                case "rock":
                    return Move.ROCK;
                case "p":
                case "paper":
                    return Move.PAPER;
                case "s":
                case "scissors":
                    return Move.SCISSORS;
                case "q":
                case "quit":
                    return Move.QUIT;
                default:
                    System.out.println(String.format("%s is not a valid move, try again", input));
            }
        }
    }
}
