import java.util.Scanner;

public class TsundereChan {
    private final String LINE = "***************************************";
    private String[] list = new String[100];
    private int pointer = 0;

    public void run() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? N-Not like I want to help you or anything...\n"
                + LINE);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                this.printList();
            } else {
                this.addToList(command);
            }
            command = sc.nextLine();
        }
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you idiot!\n" +
                "It's not like I enjoy talking to you or anything...\n"
                + LINE);
    }

    public void addToList(String item) {
        list[pointer] = item;
        pointer++;
        System.out.println(LINE + "\nadded: " + item + "\n" + LINE);
    }

    public void printList() {
        if (pointer == 0) {
            System.out.println(LINE + "\nWhat?? There's nothing to list, you idiot!\n" + LINE);
            return;
        }
        System.out.println(LINE);
        for (int i = 0; i < pointer; i++) {
            String output = String.format("%d. %s", i+1, list[i]);
            System.out.println(output);
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
