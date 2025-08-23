import java.util.Scanner;

public class TsundereChan {
    protected static final String LINE = "***************************************";
    private final Task[] list = new Task[100];
    private int pointer = 0;

    public void run() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? If you ask nicely, I just MIGHT help you...\n"
                + LINE);
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            try {
                command = sc.nextLine();
                if (command.equals("bye")) {
                    break;
                }
                this.action(command);
            } catch (InsufficientInformationException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you idiot!\n" +
                "It's not l-like I enjoyed talking to you or anything...\n"
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
                if (!sc.hasNextInt()) {
                    throw new IllegalArgumentException(LINE + "\nYou must have a task number after mark!\n" + LINE);
                }
                index = sc.nextInt();
                this.mark(index);
                break;
            case "unmark":
                if (!sc.hasNextInt()) {
                    throw new IllegalArgumentException(LINE + "\nYou must have a task number after unmark!\n" + LINE);
                }
                index = sc.nextInt();
                this.unmark(index);
                break;
            default:
                this.addToList(string);
        }
        sc.close();
    }

    public void addToList(String command) {
        Scanner sc = new Scanner(command);
        String task = sc.next();
        String str;
        switch (task) {
            case "todo":
                if (!sc.hasNextLine()) {
                    throw new InsufficientInformationException("You gotta tell me the description of the todo" +
                            " at least, or I can't help you, doofus!");
                }
                str = sc.nextLine().trim();
                list[pointer] = new Todo(str);
                break;
            case "deadline":
                if (!sc.hasNextLine()) {
                    throw new InsufficientInformationException("You gotta tell me the description of the deadline" +
                            " at least, or I can't help you, doofus!");
                }
                str = sc.nextLine().trim();
                String[] deadline = str.split("/by", 2);
                if (deadline.length < 2) {
                    throw new InsufficientInformationException("You need to include /by, dummy!");
                }
                list[pointer] = new Deadline(deadline[0].trim(), deadline[1].trim());
                break;
            case "event":
                if (!sc.hasNextLine()) {
                    throw new InsufficientInformationException("You gotta tell me the description of the event" +
                            " at least, or I can't help you, doofus!");
                }
                str = sc.nextLine().trim();
                String[] event = str.split("/from|/to", 3);
                if (event.length < 3) {
                    throw new InsufficientInformationException("You need to include both /from and /to, IN THAT ORDER, dummy!");
                }
                list[pointer] = new Event(event[0].trim(), event[1].trim(), event[2].trim());
                break;
            default:
                throw new InsufficientInformationException("You need to include a keyword, " +
                        "or I have no idea what you're talking about, doofus!");
        }
        sc.close();
        pointer++;
        System.out.println(LINE + "\nI'll do you a favour and add this task for you, but you better not expect anything more!");
        System.out.println("    " + list[pointer-1]);
        System.out.println("Now you have " + pointer + " tasks in your list. Better get to work!");
        System.out.println(LINE);
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
            throw new IllegalArgumentException(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
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
            throw new IllegalArgumentException(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
        }
        Task task = list[index-1];
        if (!task.isDone) {
            System.out.println(LINE + "\nIt's not even done yet, geeez!\n" + LINE);
            return;
        }
        task.unmark();
        System.out.println(LINE);
        System.out.println("And here I was, expecting something from you... Why do I feel disappointed?");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
