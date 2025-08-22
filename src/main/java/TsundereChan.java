import java.util.Scanner;

public class TsundereChan {
    private final String LINE = "***************************************";
    private Task[] list = new Task[100];
    private int pointer = 0;

    public void run() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? N-Not like I want to help you or anything...\n"
                + LINE);
        Scanner sc = new Scanner(System.in);
        String command;
        do {
            command = sc.nextLine();
            this.action(command);
        } while (!command.equals("bye"));
        sc.close();
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you idiot!\n" +
                "It's not like I enjoy talking to you or anything...\n"
                + LINE);
    }

    public void action(String string) {
        Scanner sc = new Scanner(string);
        String command = sc.next();
        int index;
        switch (command) {
            case "list":
                this.printList();
                break;
            case "bye":
                break;
            case "mark":
                index = sc.nextInt();
                this.mark(index);
                break;
            case "unmark":
                index = sc.nextInt();
                this.unmark(index);
                break;
            default:
                this.addToList(command);
        }
    }

    public void addToList(String item) {
        list[pointer] = new Task(item);
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
            String output = String.format("%d.%s", i+1, list[i]);
            System.out.println(output);
        }
        System.out.println(LINE);
    }

    public void mark(int index) {
        if (index < 1 ||  index > pointer) {
            System.out.println(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
            return;
        }
        Task task = list[index-1];
        if (task.isDone) {
            System.out.println(LINE + "\nYou've already asked me to mark it, geez!\n" + LINE);
            return;
        }
        task.mark();
        System.out.println(LINE);
        System.out.println("W-well, it seems even you can get something done, I guess... N-not like I'm impressed or anything!");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    public void unmark(int index) {
        if (index < 1 ||  index > pointer) {
            System.out.println(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
            return;
        }
        Task task = list[index-1];
        if (!task.isDone) {
            System.out.println(LINE + "\nIt's not even done yet, geeez!\n" + LINE);
            return;
        }
        task.unmark();
        System.out.println(LINE);
        System.out.println("And I thought you couldn't get any worse...");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
