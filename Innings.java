import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Innings {
    private final byte inningsNo;
    private Team battingTeam;
    private Team bowlingTeam;
    private ArrayList<Player> batters=new ArrayList<>();
    private ArrayList<Player> bowlers=new ArrayList<>();
    private final int noOfOvers;
    private Map<Integer,ArrayList<String>> overMap=new HashMap<>();
    private int totalScore;
    private int target;
    private final int maxOverLimit;
    private int extraOver;
    private Player currStriker;
    private Player currNonStriker;
    private Player currBowler;
    private int wickets;

    Innings(int totalOvers)
    {
        inningsNo=1;
        noOfOvers=totalOvers;
        maxOverLimit=noOfOvers/5;
        extraOver=noOfOvers%5;
    }
    Innings(int totalOvers,int target)
    {
        inningsNo=2;
        this.target=target;
        noOfOvers=totalOvers;
        maxOverLimit=noOfOvers/5;
        extraOver=noOfOvers%5;
    }
    public byte getInningsNo()
    {
        return inningsNo;
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
    public int getTotalScore()
    {
        return totalScore;
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
        System.out.print("\n\nOpenning Batsman \n---------------- ");
        //System.out.printf("\n%-10s %-25s %-15s", "Jersey No","Player Name", "Batting Style");
        /*for(int i=0;i<11;i++)
        {
            Player player=battingTeamPlayers.get(i);
            if(player.isEligibleToBat())
            {
                System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBattingStyle());
            }
        }*/
        byte strikerJerseyNo=(byte)((Math.random()*11)+1);
        //System.out.println("Enter non striker jerseyNo :");
        int nonStrikerJerseyNo=(byte)((Math.random()*11)+1);
        while(strikerJerseyNo==nonStrikerJerseyNo)
        {
            //System.out.print("You can't choose same player both striker and non-striker");
            //System.out.println("Enter striker jerseyNo :");
            //strikerJerseyNo=Input.getInteger();
            //System.out.println("Non-striker:");
            nonStrikerJerseyNo=(byte)((Math.random()*11)+1);
        }
        currStriker=battingTeamPlayers.get(strikerJerseyNo-1);
        currStriker.setEligibleToBat(false);
        System.out.print("\nStriker : "+currStriker.getPlayerName());
        currNonStriker=battingTeamPlayers.get(nonStrikerJerseyNo-1);
        currNonStriker.setEligibleToBat(false);
        System.out.print("\nNon-Striker : "+currNonStriker.getPlayerName());
        System.out.print("\n\nOpenning bowler\n----------------");
        /*System.out.printf("\n%-10s %-25s %-15s", "Jersey No","Player Name", "Bowling Style");
        for(int i=0;i<11;i++)
        {
            Player player=bowlingTeamPlayers.get(i);
            System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBowlingStyle());
        }*/
        byte bowlerJerseyNo=(byte)((Math.random()*11)+1);
        currBowler=bowlingTeamPlayers.get(bowlerJerseyNo-1);
        if(maxOverLimit==0)
            currBowler.setEligibleToBowl(false);
        batters.add(currStriker);
        batters.add(currNonStriker);
        bowlers.add(currBowler);
        System.out.print("\nBowler : "+currBowler.getPlayerName());
        System.out.print("\n\nPress Enter Key to start...");
        Input.getString();
        startScoring();
    }

    public void startScoring()
    {
        int overCount=0;
        byte noOfBalls=0;
        byte runningBetween=0;
        ArrayList<String> overInfo=new ArrayList<>();
        while(wickets!=10 && overCount!=noOfOvers)
        {
            Main.clear();
            System.out.println(battingTeam.getTeamName()+" - "+totalScore+"/"+wickets+"("+overCount+"."+noOfBalls+")"+"\n");
            System.out.printf("\n%-25s %-5s %-6s","Batsman","R","B"+"\n");
            System.out.printf("\n%-25s %-5s %-6s",currStriker.getPlayerName()+"*",currStriker.getBattingScore(),currStriker.getBallsFaced());
            System.out.printf("\n%-25s %-5s %-6s",currNonStriker.getPlayerName(),currNonStriker.getBattingScore(),currNonStriker.getBallsFaced());
            System.out.printf("\n\n\n%-25s %-3s %-4s %-3s","Bowler","O","R","W"+"\n");
            System.out.printf("\n%-25s %-3s %-4s %-3s",currBowler.getPlayerName(),currBowler.getNoOfOvers(),currBowler.getRunsGiven(),currBowler.getWicketsTaken());
            System.out.println("\n\nNo of Balls Bowled : "+noOfBalls);
            System.out.println("\nThis over : "+overInfo+"\n\nPress enter to bowl...");
            if(inningsNo==2)
                System.out.println("\nNeed "+(target-totalScore)+" off "+((noOfOvers*6)-(overCount*6+noOfBalls)));
            Input.getString();
            String currBall=getExtras();
            noOfBalls++;
            if(currBall.equals("Nb") || currBall.equals("WD"))
            {
                noOfBalls--;
                totalScore++;
                currBowler.setRunsGiven(currBowler.getRunsGiven()+1);
            }
            if(!currBall.equals("WD"))
                currStriker.setBallsFaced(currStriker.getBallsFaced()+1);
            currBall=currBall+getWicket(currBall);
            runningBetween=getRunningBetween(currBall);
            totalScore+=runningBetween;
            if(!currBall.contains("B"))
                currBowler.setRunsGiven(currBowler.getRunsGiven()+runningBetween);
            if(currBall.equals("") || currBall.equals("Nb"))
            {
                currStriker.setBattingScore(currStriker.getBattingScore()+runningBetween);
            }
            currBall=currBall+String.valueOf(runningBetween);
            overInfo.add(currBall);
            if(currBall.contains("w"))
            {
                wickets++;
                currBowler.setWicketsTaken(currBowler.getWicketsTaken()+1);
                System.out.println("\nIts a Wicket!!! "+currStriker.getPlayerName()+" got out :(");
                currStriker = getNewPlayer();
                System.out.println(currStriker.getPlayerName()+" coming to crease...");
                batters.add(currStriker);
                /*System.out.println("Who got out?\n1.striker\n2.non-striker");
                int opt=Input.getInteger();
                if(opt==1) {
                    currStriker = getNewPlayer();
                    batters.add(currStriker);
                }
                else {
                    currNonStriker = getNewPlayer();
                    batters.add(currNonStriker);
                }*/
            }
            else
            {
                switch(currBall.charAt(0))
                {
                    case 'N':
                        System.out.print("\nIts a No Ball!!! "+currStriker.getPlayerName());
                        break;
                    case 'W':
                        System.out.print("\nIts a Wide Ball!!! team");
                        break;
                    case 'B':
                        System.out.print("\nIts a Byes!!! team");
                        break;
                    default :
                        System.out.print("\n"+currStriker.getPlayerName());
                        break;
                }
                System.out.print(" scored "+runningBetween+" runs");
            }
            Input.getString();
            if(wickets!=10)
            {
                if(runningBetween%2==1)
                    rotateStrike();
                if(noOfBalls==6)
                {
                    rotateStrike();
                    noOfBalls=0;
                    overCount++;
                    overMap.put(1,overInfo);
                    overInfo=new ArrayList<>();
                    currBowler.setNoOfOvers(currBowler.getNoOfOvers()+1);
                    if(overCount!=noOfOvers)
                        endOver();
                }
            }
            if(inningsNo==2 && target-totalScore<=0)
                break;
        }
        Main.clear();
        battingTeam.setScore(totalScore);
        printScorecard();
        if(inningsNo==2)
        {
            if(target-totalScore<=0)
                System.out.println("\n*****  "+battingTeam.getTeamName()+" won by "+(10-wickets)+"  *****");
            else if(target-totalScore==1)
                System.out.println("\n*****  Match draw  *****");
            else
                System.out.println("\n*****  "+bowlingTeam.getTeamName()+" won by "+(target-1-totalScore)+"  *****");
        }
        System.out.println("\nPress Enter to continue...");
        Input.getString();
    }
    public void rotateStrike()
    {
        Player player=currStriker;
        currStriker=currNonStriker;
        currNonStriker=player;
    }
    public void printScorecard() {
        System.out.println("Innings Summary...\n");
        System.out.println(battingTeam.getTeamName() + " - " + totalScore + "/" + wickets + "\n");
        System.out.printf("\n%-25s %-5s %-6s", "Batsman", "R", "B");
        System.out.print("\n------------------------------------");
        for (Player player : batters) {
            System.out.printf("\n%-25s %-5s %-6s", player.getPlayerName(), player.getBattingScore(), player.getBallsFaced());
        }
        System.out.printf("\n\n%-25s %-3s %-4s %-3s", "Bowler", "O", "R", "W");
        System.out.print("\n------------------------------------");
        for (Player player : bowlers) {
            System.out.printf("\n%-25s %-3s %-4s %-3s", player.getPlayerName(), player.getNoOfOvers(), player.getRunsGiven(), player.getWicketsTaken());
        }
    }
    public void endOver()
    {
        Main.clear();
        System.out.println("End Of Over...");
        if(currBowler.getNoOfOvers()==maxOverLimit)
            currBowler.setEligibleToBowl(false);
        if(currBowler.getNoOfOvers()>maxOverLimit)
        {
            extraOver--;
            currBowler.setEligibleToBowlExtraOver(false);
        }
        ArrayList<Player> bowlingTeamPlayers=bowlingTeam.getPlayers();
        /*System.out.printf("\n%-10s %-25s %-15s","JerseyNo","Bowler name","Bowling style");
        for(int i=0;i<11;i++)
        {
            Player player=bowlingTeamPlayers.get(i);
            if(player!=currBowler && (player.isEligibleToBowl() || extraOver!=0 && player.isEligibleToBowlExtraOver()))
            {
                System.out.printf("\n%-10s %-25s %-15s",player.getJerseyNo(),player.getPlayerName(),player.getBowlingStyle());
            }
        }*/
        int jerseyNo=(int)(Math.random()*11);
        while(!bowlingTeamPlayers.get(jerseyNo).isEligibleToBowl() && !(extraOver!=0 && bowlingTeamPlayers.get(jerseyNo).isEligibleToBowlExtraOver()))
        {
            //System.out.println("Enter only available player :");
            jerseyNo=(int)(Math.random()*11);
        }
        currBowler=bowlingTeamPlayers.get(jerseyNo);
        System.out.println(currBowler.getPlayerName()+" Coming to the crease to bowl...\n\nPress enter to continue...");
        Input.getString();
        if(!bowlers.contains(currBowler))
            bowlers.add(currBowler);
    }
    public Player getNewPlayer()
    {
        if(wickets!=10) {
            ArrayList<Player> battingTeamPlayers = battingTeam.getPlayers();
            /*System.out.printf("\n%-10s %-25s %-15s", "Jersey No", "Batsman name", "Batting style");
            for (int i = 0; i < 11; i++) {
                Player player = battingTeamPlayers.get(i);
                if (player.isEligibleToBat()) {
                    System.out.printf("\n%-10s %-25s %-15s", player.getJerseyNo(), player.getPlayerName(), player.getBattingStyle());
                }
            }*/
            int jerseyNo = (int)((Math.random()*11));
            while (batters.contains(battingTeamPlayers.get(jerseyNo))) {
                //System.out.println("Enter only available player :");
                jerseyNo = (int)((Math.random()*11));
            }
            battingTeamPlayers.get(jerseyNo).setEligibleToBat(false);
            return battingTeamPlayers.get(jerseyNo);
        }
        return null;
    }
    public byte getRunningBetween(String currBall)
    {
        if(currBall.contains("w"))
            return 0;
        //System.out.println("Enter the runs in running btwn : ");
        return (byte)(Math.random()*7);
    }
    public String getWicket(String currBall)
    {
        if(!currBall.equals(""))
            return "";
        byte opt=(byte)(Math.random()*20);
        if(opt<=2)
            return "w";
        else
            return "";
    }
    public String getExtras()
    {
        //System.out.println("\n\nFor Extras :\n1.No Ball\n2.Wide\n3.Leg byes\n4.Byes\nEnter what happened...");
        //int opt=Input.getInteger();
        switch((byte)(Math.random()*30))
        {
            case 1:
                return "Nb";
            case 2:
                return "WD";
            case 3:
                return "B";
            default :
                return "";
        }
    }
}
