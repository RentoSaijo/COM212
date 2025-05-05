import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class MySocialProfile {
    final static String FORMATBAR = "------------------------------";
    final static String stricterEmail =
        "^(?=.{1,64}@)"                   // local‐part ≤64 chars
      + "[A-Za-z0-9]"                     // must start with alnum
      + "(?:[A-Za-z0-9._%+-]{0,62}[A-Za-z0-9])?" // optional middle/end
      + "@"                                // the at‐sign
      + "(?:(?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+" // one or more valid domain labels + dot
      + "[A-Za-z]{2,}$";
    private String fullName;
    private String email;
    private String password;
    private int classYear;
    private ArrayPriorityQueue events;
    private SinglyLinkedList timeline;
    private SinglyLinkedList friends;
    private static final String FILENAME = "mysocialprofile.txt";
    private static final int EVENT_CAPACITY = 100;

    public MySocialProfile() {
        this.events = new ArrayPriorityQueue(EVENT_CAPACITY);
        this.timeline = new SinglyLinkedList();
        this.friends = new SinglyLinkedList();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public void addPost(String post) {
        this.timeline.addFirst(post);
    }

    public void addEvent(Event event) {
        this.events.insert(event);
    }

    public void viewFriends() {
        System.out.println("-           FRIEND           -\n" + FORMATBAR);
        if (this.friends.isEmpty()) {
            System.out.println("Go make some friends!");
        }
        int count = 1;
        SNode currNode = this.friends.getHead();
        while (currNode != null) {
            System.out.println(count + ". " + currNode.getElement());
            currNode = currNode.getNext();
            count++;
        }
    }

    public void addOrRemoveFriend(String email) {
        if (this.friends.delete(email) != null) {
            System.out.println(email + " removed from friend list.");
        } else {
            this.friends.addLast(email);
            System.out.println(email + " added to friend list.");
        }
    }

    public static MySocialProfile loadFromFile() {
        File file = new File(FILENAME);
        if (!file.exists()){
            System.out.println(FORMATBAR + "\nMissing \'mysocialprofile.txt\'.");
            return null;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e){
            return null;
        }
        MySocialProfile profile = new MySocialProfile();

        try {
            String fullName = reader.readLine();
            if (fullName.matches("^(\\S+)\\s+(\\S+.*)$")) {
                profile.fullName = fullName;
            } else {
                throw new IllegalArgumentException();
            }
        }  catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid full name. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            String email = reader.readLine();
            if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                profile.email = email;
            } else {
                throw new IllegalArgumentException();
            }
        }  catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid email. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            String password = reader.readLine();
            if (password.length() >= 8) {
                profile.password = password;
            } else {
                throw new IllegalArgumentException();
            }
        }  catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid password. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            int classYear = Integer.parseInt(reader.readLine());
            if (classYear >= LocalDateTime.now().getYear()-100 && classYear <= LocalDateTime.now().getYear()+100) {
                profile.classYear = classYear;
            } else {
                throw new IllegalArgumentException();
            }
        }  catch (IOException | IllegalArgumentException e) {
            System.out.println(FORMATBAR + "\nInvalid class year. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            String eventsLine = reader.readLine();
            if (eventsLine != null && !eventsLine.isEmpty()) {
                String[] evts = eventsLine.split("\",\"");
                for (String e : evts) {
                    profile.events.insert(Event.parse(e.replaceAll("^\"|\"$", "")));
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println(FORMATBAR + "\nInvalid events. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            String timelineLine = reader.readLine();
            if (timelineLine != null && !timelineLine.isEmpty()) {
                String[] posts = timelineLine.split("\",\"");
                for (String p : posts) {
                    profile.timeline.addLast(p.replaceAll("^\"|\"$", ""));
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println(FORMATBAR + "\nInvalid timeline. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            String friendsLine = reader.readLine();
            if (friendsLine != null && !friendsLine.isEmpty()) {
                String[] frs = friendsLine.split("\",\"");
                for (String f : frs) {
                    f = f.replaceAll("^\"|\"$", "");
                    if (f.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                        profile.friends.addLast(f);
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println(FORMATBAR + "\nInvalid friends. Fix \'mysocialprofile.txt\'.");
            return null;
        }

        try {
            reader.close();
        } catch (IOException e) {
            return null;
        }

        return profile;
    }

    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
        writer.write(fullName);
        writer.newLine();
        writer.write(email);
        writer.newLine();
        writer.write(password);
        writer.newLine();
        writer.write(Integer.toString(classYear));
        writer.newLine();
        int size = events.size();
        Event[] temp = new Event[size];
        for (int i = 0; i < size; i++) {
            temp[i] = events.extractMin();
        }
        for (int i = 0; i < temp.length; i++) {
            writer.write("\"" + temp[i].toFileString() + "\"");
            if (i < temp.length - 1) writer.write(",");
            events.insert(temp[i]);
        }
        writer.newLine();
        writer.write(timeline.toString());
        writer.newLine();
        writer.write(friends.toString());
        writer.newLine();
        writer.close();
    }

    public void purgePastEvents() {
        while (!this.events.isEmpty() && this.events.getMin().getDateTime().isBefore(LocalDateTime.now())) {
            this.events.extractMin();
        }
    }

    public void displayHomeScreen() {
        System.out.println(FORMATBAR + "\n-            HOME            -\n" + FORMATBAR);

        System.out.println("UPCOMING EVENTS");
        Event next = events.getMin();
        if (next != null) {
            System.out.println("  " + next);
        } else {
            System.out.println("    No Upcoming Events");
        }

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
        System.out.println("ALL EVENTS");
        count = events.size();
        if (count == 0) {
            System.out.println("    No Scheduled Events");
        } else {
            Event[] allEvents = new Event[count];
            for (int i = 0; i < count; i++) {
                allEvents[i] = events.extractMin();
            }
            for (Event evt : allEvents) {
                if (evt != null) {
                    System.out.println("  " + evt);
                    events.insert(evt);
                }
            }
        }
    }
}