import java.util.Scanner;

public class Input
{
    private static Scanner sc = new Scanner(System.in);
    public static int getInteger() {
        return sc.nextInt();
    }
    public static String getString(){
        return sc.next();
    }
}
