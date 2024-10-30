import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    static List<Team> teams=new ArrayList<Team>();
    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------\n" +
                           "|                  Scorer Application                   |\n"+
                           "---------------------------------------------------------");
        Main main=new Main();
        initializeTeam();
        main.printScoreCard(teams.get(0));
        System.out.println("\n\nPress Any key and press Enter Key to Next Team Info...");
        Input.getString();
        clear();
        main.printScoreCard(teams.get(1));
        System.out.println("\n\nPress Any key and press Enter Key to start...");
        Input.getString();
        clear();
        System.out.println("Enter no of overs : ");
        int noOfOvers=Input.getInteger();
        Input.getString();
        clear();
        System.out.println("Its a Toss time...");
        //System.out.println("\nToss Won by : \n1.Zoho A\n2.Zoho B");
        byte teamNo=(byte)((Math.random()*2)+1);
        //System.out.println("Elected to : \n1.Bat\n2.Bowl");
        int elect=(byte)((Math.random()*2)+1);
        Innings innings1=new Innings(noOfOvers);
        System.out.println("Press Enter key to toss the coin...");
        Input.getString();
        clear();
        System.out.print("\nToss won by "+teams.get(teamNo-1).getTeamName());
        if(teamNo==1)
        {
            if(elect==1)
            {
                System.out.print(" and Elected to Bat first\n");
                innings1.setBattingTeam(teams.get(0));
                innings1.setBowlingTeam(teams.get(1));
            }
            else
            {
                System.out.print(" and Elected to Bowl first\n");
                innings1.setBattingTeam(teams.get(1));
                innings1.setBowlingTeam(teams.get(0));
            }
        }
        else
        {
            if(elect==1)
            {
                System.out.print(" and Elected to Bat first\n");
                innings1.setBattingTeam(teams.get(1));
                innings1.setBowlingTeam(teams.get(0));
            }
            else
            {
                System.out.print(" and Elected to Bowl first\n");
                innings1.setBattingTeam(teams.get(0));
                innings1.setBowlingTeam(teams.get(1));
            }
        }
        System.out.println("\n\nEnter any key and press Enter to start match...");
        Input.getString();
        clear();
        innings1.startMatch();
        clear();
        Innings innings2=new Innings(noOfOvers,innings1.getTotalScore()+1);
        innings2.setBattingTeam(innings1.getBowlingTeam());
        innings2.setBowlingTeam(innings1.getBattingTeam());
        System.out.println("\n\npress Enter to start 2 innings...");
        Input.getString();
        clear();
        innings2.startMatch();
//        System.out.println("Enter team1 Details...");
//        System.out.println("Enter team1 Name : ");
//        String team1Name=Input.getString();
//        ArrayList<Player> players=getPlayerList();
//        Team team1=new Team(team1Name,players);
//        System.out.println("Enter team2 Details...");
//        System.out.println("Enter team2 Name : ");
//        String team2Name=Input.getString();
//        ArrayList<Player> players2=getPlayerList();
//        Team team2=new Team(team2Name,players2);
//        System.out.println(team1Name + " vs " + team2Name);

    }
    public void printScoreCard(Team team) {
        System.out.println("Team :  "+team.getTeamName()+"\n\n");
        System.out.printf("%-10s %-25s %-15s %-15s%n", "Jersey No","Player Name", "Batting Style", "Bowling Style");
        System.out.println("---------------------------------------------------------------------------------");
        for(Player player:team.getPlayers()) {
            System.out.printf("%-10s %-25s %-15s %-15s%n",player.getJerseyNo(),player.getPlayerName(),player.getBattingStyle(),player.getBowlingStyle()+"\n");
        }
    }
    public static void initializeTeam()
    {
        int no=1;
        Player p1=new Player(no++,"Krishnamoorthy","Right","Medium Fast");
        Player p2=new Player(no++,"Sriram","Right","Fast");
        Player p3=new Player(no++,"Nagarajan","Right","Spin");
        Player p4=new Player(no++,"RishiKesh","Left","spin");
        Player p5=new Player(no++,"Gowtham","Right","Fast");
        Player p6=new Player(no++,"Hari","Left","Spin");
        Player p7=new Player(no++,"Sukumar","Right","spin");
        Player p8=new Player(no++,"karthi","Right","Medium Fast");
        Player p9=new Player(no++,"SJ","Right","Spin");
        Player p10=new Player(no++,"Harini","Left","Medium Fast");
        Player p11=new Player(no++,"Subitsha","Right","Spin");

        ArrayList<Player> players=new ArrayList<>(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
        Team team1=new Team("Zoho A",players);

        no=1;
        Player p21=new Player(no++,"Thiru","Right","Medium Fast");
        Player p22=new Player(no++,"Prathosh","Left","Fast");
        Player p23=new Player(no++,"Santhosh","Right","Spin");
        Player p24=new Player(no++,"Surya","Left","spin");
        Player p25=new Player(no++,"Suresh","Left","Fast");
        Player p26=new Player(no++,"Sundar","Right","Medium fast");
        Player p27=new Player(no++,"Dhanush","Right","spin");
        Player p28=new Player(no++,"Muthu","Left","Medium Fast");
        Player p29=new Player(no++,"Natarajan","Right","Spin");
        Player p210=new Player(no++,"Pravin","Left","Medium Fast");
        Player p211=new Player(no++,"Stalin","Right","Spin");

        ArrayList<Player> players1=new ArrayList<>(Arrays.asList(p21,p22,p23,p24,p25,p26,p27,p28,p29,p210,p211));
        Team team2=new Team("Zoho B",players1);

        teams.add(team1);
        teams.add(team2);
    }
    static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*private static ArrayList<Player> getPlayerList() {
        ArrayList<Player> players=new ArrayList<>();
        System.out.println("Enter 11 players Details...");
        for(int person=1;person<=11;person++) {
            System.out.println("Enter Player "+person+" Details : : ");
            System.out.println("Name : ");
            String playerName=Input.getString();
            System.out.println("Batting Style : ");
            String battingStyle=Input.getString();
            System.out.println("Bowling Style : ");
            String bowlingStyle=Input.getString();
            Player player=new Player(person,playerName,battingStyle,bowlingStyle);
            players.add(player);
        }
        return players;
    }*/

}