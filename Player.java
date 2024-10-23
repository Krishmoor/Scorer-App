class Player {
    private String playerName;
    private int jerseyNo;
    private String battingStyle;
    private int battingScore;
    private int ballsFaced;
    private boolean isEligibleToBat;
    private String bowlingStyle;
    private int noOfOversBowled;
    private int runsGiven;
    private int wicketsTaken;
    private boolean isEligibleToBowl;
    private boolean isEligibleToBowlExtraOver;

    Player(int jerseyNo,String playerName,String battingStyle,String bowlingStyle)
    {
        this.jerseyNo=jerseyNo;
        this.playerName = playerName;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        this.isEligibleToBat=true;
        this.isEligibleToBowl=true;
        this.isEligibleToBowlExtraOver=true;
    }
    public int getJerseyNo() {
        return jerseyNo;
    }
    public int getBattingScore()
    {
        return battingScore;
    }
    public int getBallsFaced() {
        return ballsFaced;
    }
    public int getNoOfOvers() {
        return noOfOversBowled;
    }
    public int getWicketsTaken()
    {
        return wicketsTaken;
    }
    public int getRunsGiven()
    {
        return runsGiven;
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
    public boolean isEligibleToBat() {
        return isEligibleToBat;
    }
    public boolean isEligibleToBowl()
    {
        return isEligibleToBowl;
    }
    public boolean isEligibleToBowlExtraOver()
    {
        return isEligibleToBowlExtraOver;
    }
    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }
    public void setNoOfOvers(int noOfOvers) {
        this.noOfOversBowled = noOfOvers;
    }

    public void setBattingScore(int battingScore) {
        this.battingScore = battingScore;
    }
    public void setWicketsTaken(int wickets)
    {
        this.wicketsTaken=wickets;
    }
    public void setRunsGiven(int runs)
    {
        this.runsGiven=runs;
    }
    public void setEligibleToBat(boolean eligible) {
        this.isEligibleToBat = eligible;
    }

    public void setEligibleToBowl(boolean eligible)
    {
        isEligibleToBowl=eligible;
    }

    public void setEligibleToBowlExtraOver(boolean eligible)
    {
        isEligibleToBowlExtraOver=eligible;
    }
}
