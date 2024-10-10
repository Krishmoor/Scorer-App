class Player {
    private String playerName;
    private int battingScore;
    private String battingStyle;
    private String bowlingStyle;
    private int ballsFaced;
    private int noOfOvers;
    private boolean isEligible;

    Player(String playerName,String battingStyle,String bowlingStyle)
    {
        this.playerName = playerName;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        this.isEligible=true;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }
    public int getNoOfOvers() {
        return noOfOvers;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getBowlingStyle() {
        return bowlingStyle;
    }
    public String getBattingStyle() {
        return battingStyle;
    }
    public boolean isEligible() {
        return isEligible;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }
    public void setNoOfOvers(int noOfOvers) {
        this.noOfOvers = noOfOvers;
    }

    public void setBattingScore(int battingScore) {
        this.battingScore = battingScore;
    }

    public void setEligible(boolean eligible) {
        this.isEligible = eligible;
    }
}
