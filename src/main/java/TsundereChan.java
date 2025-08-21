import java.util.Scanner;

public class TsundereChan {
    public static void main(String[] args) {
        String horizontalLine = "_________________________________________";
        System.out.println(horizontalLine
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? No-Not like I want to help you or anything...\n"
                + horizontalLine);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command + "\n" + horizontalLine);
            command = sc.nextLine();
        }
        System.out.println(horizontalLine + "\nHmph, done already? D-don't talk to me anymore, you fool! It's not like I enjoyed that or anything...\n"
                + horizontalLine);
    }
}
