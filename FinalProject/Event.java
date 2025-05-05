/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program represents a scheduled event with a date/time and description, used in the profile’s events priority queue.
*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Models an event occurring at a specific date and time, with a textual description.
 * Implements Comparable so events can be ordered chronologically in a priority queue.
 */
public class Event implements Comparable<Event> {
    // the date and time when this event takes place
    private LocalDateTime dateTime;
    // a brief description of the event
    private String description;
    // formatter for parsing and formatting date/time in "MM dd yyyy HH mm" pattern
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy HH mm");

    /**
     * Constructs a new Event with the given date/time and description.
     *
     * @param dateTime    the date and time of the event
     * @param description a textual description of the event
     */
    public Event(LocalDateTime dateTime, String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    /**
     * Returns this event’s date/time.
     *
     * @return the LocalDateTime of the event
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns this event’s description.
     *
     * @return the event description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Parses a string in the format "MM dd yyyy HH mm description" into an Event.
     *
     * @param s the input string to parse
     * @return a new Event instance
     * @throws IllegalArgumentException if the string does not contain at least 6 space-separated parts
     */
    public static Event parse(String s) {
        String[] parts = s.trim().split(" ", 6);
        if (parts.length < 6) {
            throw new IllegalArgumentException();
        }
        // reassemble the first five parts into a date/time string
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

    /**
     * Formats this event for storage in the text file.
     *
     * @return a string in "MM dd yyyy HH mm description" form
     */
    public String toFileString() {
        return formatter.format(this.dateTime) + " " + this.description;
    }

    /**
     * Returns a human-readable representation of this event.
     *
     * @return a string like "MM dd yyyy HH mm - description"
     */
    public String toString() {
        return this.dateTime.format(formatter) + " - " + this.description;
    }

    /**
     * Compares this event to another by date/time for chronological ordering.
     *
     * @param that the other Event to compare against
     * @return a negative number if this is before that, zero if equal, positive if after
     */
    public int compareTo(Event that) {
        return this.dateTime.compareTo(that.getDateTime());
    }
}