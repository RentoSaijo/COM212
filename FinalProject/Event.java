import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event> {
    private LocalDateTime dateTime;
    private String description;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy HH mm");

    public Event(LocalDateTime dateTime, String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getDescription() {
        return this.description;
    }

    public static Event parse(String s) {
        String[] parts = s.trim().split(" ", 6);
        if (parts.length < 6) {
            throw new IllegalArgumentException("Dates must be in {MM dd yyyy HH mm}: " + s);
        }
        String datePart = String.join(
            " ",
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4]
        );
        LocalDateTime dt = LocalDateTime.parse(datePart, formatter);
        String desc = parts[5];
        return new Event(dt, desc);
    }

    public String toFileString() {
        return formatter.format(this.dateTime) + " " + this.description;
    }

    public String toString() {
        return this.dateTime.format(formatter) + " - " + this.description;
    }

    public int compareTo(Event that) {
        return this.dateTime.compareTo(that.dateTime);
    }
}