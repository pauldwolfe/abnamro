package abn.prs;

public class GameSummary {

    private int wins;
    private int losses;
    private int ties;

    void update(Result result){
        switch (result){
            case WIN:
                wins++;
                break;
            case LOSE:
                losses++;
                break;
            case TIE:
                ties++;
                break;
        }
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    @Override
    public String toString() {
        return "GameSummary{" +
                "wins=" + wins +
                ", losses=" + losses +
                ", ties=" + ties +
                '}';
    }
}
