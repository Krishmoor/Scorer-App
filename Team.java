import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> players;
    private int score;

    Team(String teamName, ArrayList<Player> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
