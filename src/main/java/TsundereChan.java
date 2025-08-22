import java.util.Scanner;

public class TsundereChan {
    private final String LINE = "***************************************";
    public void run() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? N-Not like I want to help you or anything...\n"
                + LINE);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(LINE + "\n" + command + "\n" + LINE);
            command = sc.nextLine();
        }
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you fool!\n" +
                "It's not like I enjoy talking to you or anything...\n"
                + LINE);
    }
    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
