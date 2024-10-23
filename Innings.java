import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Innings {
    private Team battingTeam;
    private Team bowlingTeam;
    private ArrayList<Player> batters=new ArrayList<>();
    private ArrayList<Player> bowlers=new ArrayList<>();
    private int noOfOvers;
    private Map<Integer,ArrayList<String>> overMap=new HashMap<>();
    private int totalScore;
    private int target;
    private int maxOverLimit;
    private int extraOver;
    private Player currStriker;
    private Player currNonStriker;
    private Player currBowler;
    private int wickets;

    Innings(int noOfOvers)
    {
        this.noOfOvers=noOfOvers;
        maxOverLimit=noOfOvers/5;
        extraOver=noOfOvers%5;   
    }

    public Team getBattingTeam()
    {
        return battingTeam;
    }
    public Team getBowlingTeam()
    {
        return bowlingTeam;
    }
    public ArrayList<Player> getBatters()
    {
        return batters;
    }
    public ArrayList<Player> getBowlers()
    {
        return bowlers;
    }
    public void setBattingTeam(Team team)
    {
        battingTeam=team;
    }
    public void setBowlingTeam(Team team)
    {
        bowlingTeam=team;
    }
    public void startMatch()
    {
        ArrayList<Player> battingTeamPlayers=battingTeam.getPlayers();
        ArrayList<Player> bowlingTeamPlayers=bowlingTeam.getPlayers();
        System.out.println("Match Started...");
        System.out.println("Choose the Striker and Non Striker(openning Batsman) : ");
        System.out.printf("\n%-10s %-25s %-15s", "Jersey No","Player Name", "Batting Style");
        for(int i=0;i<11;i++)
        {
            Player player=battingTeamPlayers.get(i);
            if(player.isEligibleToBat())
            {
                System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBattingStyle());
            }
        }
        System.out.println("\nEnter striker jerseyNo :");
        int strikerJerseyNo=Input.getInteger();
        System.out.println("Enter non striker jerseyNo :");
        int nonStrikerJerseyNo=Input.getInteger();
        while(strikerJerseyNo==nonStrikerJerseyNo)
        {
            System.out.print("You can't choose same player both striker and non-striker");
            System.out.println("Enter striker jerseyNo :");
            strikerJerseyNo=Input.getInteger();
            System.out.println("Enter non striker jerseyNo :");
            nonStrikerJerseyNo=Input.getInteger();
        }
        currStriker=battingTeamPlayers.get(strikerJerseyNo-1);
        currStriker.setEligibleToBat(false);
        currNonStriker=battingTeamPlayers.get(nonStrikerJerseyNo-1);
        currNonStriker.setEligibleToBat(false);
        Main.clear();
        System.out.println("Choose Openning bowler :");
        System.out.printf("\n%-10s %-25s %-15s", "Jersey No","Player Name", "Bowling Style");
        for(int i=0;i<11;i++)
        {
            Player player=bowlingTeamPlayers.get(i);
            System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBowlingStyle());
        }
        System.out.println("\nEnter Bowler jerseyNo :");
        int bowlerJerseyNo=Input.getInteger();
        currBowler=bowlingTeamPlayers.get(bowlerJerseyNo-1);
        batters.add(currStriker);
        batters.add(currNonStriker);
        bowlers.add(currBowler);
        startScoring(); 
    }

    public void startScoring()
    {
        int overCount=0;
        int noOfBalls=0;
        int runningBetween=0;
        ArrayList<String> overInfo=new ArrayList<>();
        while(wickets!=10 || overCount!=noOfOvers)
        {
            Main.clear();
            System.out.println(battingTeam.getTeamName()+" - "+totalScore+"/"+wickets+"\n");
            System.out.printf("\n%-25s %-5s %-6s","Batsman","R","B"+"\n");
            System.out.printf("\n%-25s %-5s %-6s",currStriker.getPlayerName()+"*",currStriker.getBattingScore(),currStriker.getBallsFaced());
            System.out.printf("\n%-25s %-5s %-6s",currNonStriker.getPlayerName(),currNonStriker.getBattingScore(),currNonStriker.getBallsFaced());
            System.out.printf("\n%-25s %-3s %-4s %-3s","Bowler","O","R","W"+"\n");
            System.out.printf("\n%-25s %-3s %-4s %-3s",currBowler.getPlayerName(),currBowler.getNoOfOvers(),currBowler.getRunsGiven(),currBowler.getWicketsTaken());
            System.out.print("\nNo of Balls Bowled : "+noOfBalls);
            String currBall=getExtras();
            noOfBalls++;
            if(currBall.equals("NB") || currBall.equals("WD"))
            {    
                noOfBalls--;
                totalScore++;
                currBowler.setRunsGiven(currBowler.getRunsGiven()+1);
            }
            if(!currBall.equals("WD"))
                currStriker.setBallsFaced(currStriker.getBallsFaced()+1);
            currBall=currBall+getWicket(currBall);
            runningBetween=getRunningBetween();
            totalScore+=runningBetween;
            if(!currBall.contains("B"))
                currBowler.setRunsGiven(currBowler.getRunsGiven()+runningBetween);
            if(currBall.equals("") || currBall.equals("w") || currBall.equals("NB") || currBall.equals("NBw"))
            {
                currStriker.setBattingScore(currStriker.getBattingScore()+runningBetween);
            }
            currBall=currBall+String.valueOf(runningBetween);
            overInfo.add(currBall);
            if(currBall.contains("w"))
            {
                wickets++;
                currBowler.setWicketsTaken(currBowler.getWicketsTaken()+1);
                System.out.println("Who got out?\n1.striker\n2.non-striker");
                int opt=Input.getInteger();
                if(opt==1)
                    currStriker=getNewPlayer();
                else
                    currNonStriker=getNewPlayer();
            }
            if(wickets!=10 && runningBetween%2==1 || noOfBalls==6)
            {
                rotateStrike();
                if(noOfBalls==6)
                {
                    noOfBalls=0;
                    overCount++;
                    overMap.put(1,overInfo);
                    overInfo=new ArrayList<>();
                    if(overCount!=noOfOvers)
                        endOver();
                }
            }
        }
        battingTeam.setScore(totalScore);
    }
    public void rotateStrike()
    {
        Player player=currStriker;
        currStriker=currNonStriker;
        currNonStriker=player;
    }
    public void endOver()
    {
        currBowler.setNoOfOvers(currBowler.getNoOfOvers()+1);
        if(currBowler.getNoOfOvers()==maxOverLimit)
            currBowler.setEligibleToBowl(false);
        else if(currBowler.getNoOfOvers()>=maxOverLimit)
        {
            extraOver--;
            currBowler.setEligibleToBowlExtraOver(false);
        }
        ArrayList<Player> bowlingTeamPlayers=bowlingTeam.getPlayers();
        System.out.printf("\n%-10s %-25s %-15s","JerseyNo","Bowler name","Bowling style");
        for(int i=0;i<11;i++)
        {
            Player player=bowlingTeamPlayers.get(i);
            if(player.isEligibleToBowl() || extraOver!=0 && player.isEligibleToBowlExtraOver())
            {
                System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBowlingStyle());
            }
        }
        int jerseyNo=Input.getInteger();
        while(!bowlingTeamPlayers.get(jerseyNo-1).isEligibleToBowl() && !(extraOver!=0 && bowlingTeamPlayers.get(jerseyNo-1).isEligibleToBowlExtraOver()))
        {
            System.out.println("Enter only available player :");
            jerseyNo=Input.getInteger();
        }
        currBowler=bowlingTeamPlayers.get(jerseyNo-1);
        if(!bowlers.contains(currBowler))
            bowlers.add(currBowler);
    }
    public Player getNewPlayer()
    {
        ArrayList<Player> battingTeamPlayers=battingTeam.getPlayers();
        System.out.printf("\n%-10s %-25s %-15s","Jersey No","Batsman name","Batting style");
        for(int i=0;i<11;i++)
        {
            Player player=battingTeamPlayers.get(i);
            if(player.isEligibleToBat())
            {
                System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBattingStyle());
            }
        }
        int jerseyNo=Input.getInteger();
        while(batters.contains(battingTeamPlayers.get(jerseyNo-1)))
        {
            System.out.println("Enter only available player :");
            jerseyNo=Input.getInteger();
        }
        battingTeamPlayers.get(jerseyNo-1).setEligibleToBat(false);
        return battingTeamPlayers.get(jerseyNo-1);
    }
    public int getRunningBetween()
    {
        System.out.println("Enter the runs in running btwn : ");
        return Input.getInteger()%7;
    }
    public String getWicket(String currBall)
    {
        System.out.println("\ngFor Wicket : \n1.Wicket\n2.No Wicket\nEnter the option");
        int opt=Input.getInteger();
        if(opt==1)
            return "w";
        else
            return "";
    }
    public String getExtras()
    {
        System.out.println("\n\nFor Extras :\n1.No Ball\n2.Wide\n3.Leg byes\n4.Byes\nEnter what happened...");
        int opt=Input.getInteger();
        switch(opt)
        {
            case 1:
                return "NB";
            case 2:
                return "WD";
            case 3:
                return "LB";
            case 4:
                return "B";
            default :
                return "";
        }
    }
}