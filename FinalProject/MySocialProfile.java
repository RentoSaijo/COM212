/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program manages a single user's profile, including personal info, events, timeline posts, and friends;
it also persists data to “mysocialprofile.txt” and restores it on load.
*/

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a user's social network profile, with full name, email (ID), password, class year,
 * a min-heap of upcoming events, a timeline of posts, and a list of friends.
 * Provides methods for loading/saving from a text file, modifying data, and displaying the home screen.
 */
public class MySocialProfile {
    // decorative divider for console output
    final static String FORMATBAR = "------------------------------";
    // user’s full name
    private String fullName;
    // user’s email address (unique ID)
    private String email;
    // user’s password
    private String password;
    // user’s class year
    private int classYear;
    // heap of upcoming Event objects
    private ArrayPriorityQueue events;
    // timeline posts (most recent first)
    private SinglyLinkedList timeline;
    // friend list (by email)
    private SinglyLinkedList friends;
    // filename for persistence
    private static final String FILENAME = "mysocialprofile.txt";
    // initial capacity for event heap
    private static final int EVENT_CAPACITY = 100;

    /**
     * Constructs a new, empty profile.
     * Initializes the event heap, timeline list, and friends list.
     */
    public MySocialProfile() {
        // initialize event heap
        this.events = new ArrayPriorityQueue(EVENT_CAPACITY);
        // initialize timeline
        this.timeline = new SinglyLinkedList();
        // initialize friend list
        this.friends = new SinglyLinkedList();
    }

    /**
     * Returns the stored email (user ID).
     *
     * @return the email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the user's full name.
     *
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user's graduation class year.
     *
     * @param classYear the class year to set
     */
    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    /**
     * Adds a new post to the timeline (at front).
     *
     * @param post the text of the post
     */
    public void addPost(String post) {
        // add posts to the head node in timeline
        this.timeline.addFirst(post);
    }

    /**
     * Adds an event to the upcoming-events heap.
     *
     * @param event the Event to add
     */
    public void addEvent(Event event) {
        // insert into min-heap for it to be chronigically sorted
        this.events.insert(event);
    }

    /**
     * Displays the friend list to console.
     * If no friends, prompts the user to add some.
     */
    public void viewFriends() {
        System.out.println("-           FRIEND           -\n" + FORMATBAR);
        if (this.friends.isEmpty()) {
            // no friends case :(
            System.out.println("Go make some friends!");
        }
        // traverse friend nodes
        int count = 1;
        SNode currNode = this.friends.getHead();
        while (currNode != null) {
            System.out.println(count + ". " + currNode.getElement());
            currNode = currNode.getNext();
            count++;
        }
    }

    /**
     * Adds or removes a friend by email.
     * If the email already exists, removes it; otherwise adds it.
     *
     * @param email the friend's email to toggle
     */
    public void addOrRemoveFriend(String email) {
        // attempt to delete friend from list
        if (this.friends.delete(email) != null) {
            System.out.println(email + " removed from friend list.");
        } else {
            // add to end of list
            this.friends.addLast(email);
            System.out.println(email + " added to friend list.");
        }
    }

    /**
     * Loads a profile from the text file.
     * Validates each field, printing an error and returning null on invalid data.
     *
     * @return a MySocialProfile instance on success, or null on failure
     */
    public static MySocialProfile loadFromFile() {
        // find file
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println(FORMATBAR + "\nMissing 'mysocialprofile.txt'.");
            return null;
        }

        // declare and initialize reader
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return null;
        }

        // create profile
        MySocialProfile profile = new MySocialProfile();

        // full name validation: at least two parts
        try {
            String fullName = reader.readLine();
            if (fullName.matches("^(\\S+)\\s+(\\S+.*)$")) {
                profile.fullName = fullName;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid full name. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // email validation against simple regex
        try {
            String email = reader.readLine();
            if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                profile.email = email;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid email. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // password length check
        try {
            String password = reader.readLine();
            if (password.length() >= 8) {
                profile.password = password;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid password. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // class year: within reasonable range of current year (& obviously, integer)
        try {
            int classYear = Integer.parseInt(reader.readLine());
            int current = LocalDateTime.now().getYear();
            if (classYear >= current - 100 && classYear <= current + 100) {
                profile.classYear = classYear;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid class year. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // events: split on "\",\"" and parse each
        try {
            String eventsLine = reader.readLine();
            if (eventsLine != null && !eventsLine.isEmpty()) {
                String[] evts = eventsLine.split("\",\"");
                for (String e : evts) {
                    profile.events.insert(Event.parse(e.replaceAll("^\"|\"$", "")));
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException e) {
            System.out.println(FORMATBAR + "\nInvalid events. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // timeline posts: split and add in stored order (left corresponds to recent)
        try {
            String timelineLine = reader.readLine();
            if (timelineLine != null && !timelineLine.isEmpty()) {
                String[] posts = timelineLine.split("\",\"");
                for (String p : posts) {
                    profile.timeline.addLast(p.replaceAll("^\"|\"$", ""));
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException e) {
            System.out.println(FORMATBAR + "\nInvalid timeline. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // friends: validate each email against regex before adding
        try {
            String friendsLine = reader.readLine();
            if (friendsLine != null && !friendsLine.isEmpty()) {
                String[] frs = friendsLine.split("\",\"");
                for (String f : frs) {
                    f = f.replaceAll("^\"|\"$", "");
                    if (f.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                        profile.friends.addLast(f);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException e) {
            System.out.println(FORMATBAR + "\nInvalid friends. Fix 'mysocialprofile.txt'.");
            return null;
        }

        // close reader
        try {
            reader.close();
        } catch (IOException e) {
            return null;
        }

        return profile;
    }

    /**
     * Saves all profile data to the text file.
     * Extracts and reinserts events to preserve heap order.
     *
     * @throws IOException if file operations fail
     */
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
        // basic information
        writer.write(this.fullName);
        writer.newLine();
        writer.write(this.email);
        writer.newLine();
        writer.write(this.password);
        writer.newLine();
        writer.write(Integer.toString(this.classYear));
        writer.newLine();
        // number of events
        int size = this.events.size();
        Event[] temp = new Event[size];
        for (int i = 0; i < size; i++) {
            // remove each to temp
            temp[i] = this.events.extractMin();
        }
        for (int i = 0; i < temp.length; i++) {
            // write with quotes
            writer.write("\"" + temp[i].toFileString() + "\"");
            if (i < temp.length - 1) writer.write(",");
            // reinsert to restore heap
            this.events.insert(temp[i]);
        }
        writer.newLine();
        // write timeline CSV
        writer.write(this.timeline.toString());
        writer.newLine();
        // write friends CSV
        writer.write(this.friends.toString());
        writer.newLine();
        writer.close();
    }

    /**
     * Removes any past events from the heap (those before current time).
     */
    public void purgePastEvents() {
        while (!this.events.isEmpty() && this.events.getMin().getDateTime().isBefore(LocalDateTime.now())) {
            this.events.extractMin();
        }
    }

    /**
     * Displays the home screen: upcoming event, recent posts, and all scheduled events.
     */
    public void displayHomeScreen() {
        System.out.println(FORMATBAR + "\n-            HOME            -\n" + FORMATBAR);

        // UPCOMING EVENTS
        System.out.println("UPCOMING EVENTS");
        Event next = events.getMin();
        if (next != null) {
            System.out.println("  " + next);
        } else {
            System.out.println("    No Upcoming Events");
        }

        // RECENT POSTS
        System.out.println("RECENT POSTS");
        if (this.timeline.isEmpty()) {
            System.out.println("    No Recent Posts");
        }
        int count = 0;
        SNode currNode = this.timeline.getHead();
        while (currNode != null && count < 3) {
            System.out.println("  " + currNode.getElement());
            currNode = currNode.getNext();
            count++;
        }

        // ALL EVENTS
        System.out.println("ALL EVENTS");
        count = events.size();
        if (count == 0) {
            System.out.println("    No Scheduled Events");
        } else {
            Event[] allEvents = new Event[count];
            for (int i = 0; i < count; i++) {
                // extract in chronological order
                allEvents[i] = events.extractMin();
            }
            for (Event evt : allEvents) {
                if (evt != null) {
                    System.out.println("  " + evt);
                    // restore heap
                    events.insert(evt);
                }
            }
        }
    }
}