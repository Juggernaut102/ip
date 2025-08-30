import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTime = LocalDateTime.parse(by, inputFormatter);
            this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            System.out.println("Hey, I'll only say this once so listen up! "
                    + "If you want the by section to be understood as a date and time, "
                    + "you got to write it as yyyy-MM-dd HH:mm, got it?");
        }
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTime = LocalDateTime.parse(by, inputFormatter);
            this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            System.out.println("Hey, I'll only say this once so listen up! "
                    + "If you want the by section to be understood as a date and time, "
                    + "you got to write it as yyyy-MM-dd HH:mm, got it?");
        }
    }

    @Override
    public String getIcon() {
        return "D";
    }

    public String getTiming() {
        return " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
