import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime dateTimeFrom;
    protected LocalDateTime dateTimeTo;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            System.out.println("Hey, I'll only say this once so listen up! "
                    + "If you want the from and to sections to be understood as a date and time, "
                    + "you got to write it as yyyy-MM-dd HH:mm, got it?");
        }
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            System.out.println("Hey, I'll only say this once so listen up! "
                    + "If you want the from and to sections to be understood as a date and time, "
                    + "you got to write it as yyyy-MM-dd HH:mm, got it?");
        }
    }

    @Override
    public String getIcon() {
        return "E";
    }

    public String getTiming() {
        return " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
