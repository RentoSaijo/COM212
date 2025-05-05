import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SocialNetworkApp {
    static final String FORMATBAR = "------------------------------";
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH mm");

    public static void main(String[] args) {
        MySocialProfile profile = null;
        while (true) {
            System.out.println(FORMATBAR + "\n-            MENU            -\n" + FORMATBAR);
            System.out.print("""
                1. Create Profile
                2. Load Profile
                3. Quit App
                """);
            System.out.print("Select an option (1-3): ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    profile = createProfile();
                    break;
                case "2":
                    try {
                        profile = MySocialProfile.loadFromFile();
                    } catch (IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Error loading profile.");
                    }
                    break;
                case "3":
                    System.out.println(FORMATBAR + "\nApp terminated.\n" + FORMATBAR);
                    System.exit(0);
                    break;
                default:
                    System.out.println(FORMATBAR + "\nInvalid choice. Select (1-3).");
            }
            if (profile != null) {
                runSession(profile);
                profile = null;
            }
        }
    }

    private static MySocialProfile createProfile() {
    MySocialProfile profile = new MySocialProfile();

    while (true) {
        System.out.print(FORMATBAR + "\nEnter your full name: ");
        String fullName = sc.nextLine();
        if (fullName.matches("^(\\S+)\\s+(\\S+.*)$")) {
            profile.setFullName(fullName);
            break;
        }
        System.out.println("Invalid full name. Try again.");
    }

    while (true) {
        System.out.print(FORMATBAR + "\nEnter your email: ");
        String email = sc.nextLine();
        if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            profile.setEmail(email);
            break;
        }
        System.out.println("Invalid email. Try again.");
    }

    while (true) {
        System.out.print(FORMATBAR + "\nEnter your password (>= 8 chars): ");
        String password = sc.nextLine();
        if (password.length() >= 8) {
            profile.setPassword(password);
            break;
        }
        System.out.println("Invalid password. Try again.");
    }

    while (true) {
        System.out.print(FORMATBAR + "\nEnter your class year: ");
        String classYearStr = sc.nextLine();
        try {
            int classYear = Integer.parseInt(classYearStr);
            if (classYear >= LocalDateTime.now().getYear()-100 && classYear <= LocalDateTime.now().getYear()+100) {
                profile.setClassYear(classYear);
                break;
            }
        } catch (NumberFormatException e) {
        }
        System.out.println("Invalid class year. Try again.");
    }

    return profile;
}

    private static void runSession(MySocialProfile profile) {
        while (true) {
            profile.purgePastEvents();
            profile.displayHomeScreen();
            System.out.print("""
                \n1. Post to Timeline
                2. Add Event
                3. View Friends
                4. Add/Remove Friend
                5. Log Out
                """);
            System.out.print("Select an option (1-5): ");
            String choice = sc.nextLine();
            System.out.println(FORMATBAR);
            switch (choice) {
                case "1":
                    while (true) {
                        System.out.print("Enter text for your post: ");
                        String post = sc.nextLine();
                        if (!post.isEmpty() && !post.contains("\",\"")) {
                            profile.addPost(post);
                            break;
                        }
                        System.out.println("Invalid post text. Try again.\n" + FORMATBAR);
                    }
                    break;
                case "2":
                    LocalDateTime dt;
                    while (true) {
                        System.out.print("Enter time for your event (MM dd yyyy HH mm): ");
                        String dtStr = sc.nextLine();
                        try {
                            dt = LocalDateTime.parse(dtStr, inputFormatter);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid event time. Try again.\n" + FORMATBAR);
                        }
                    }
                    System.out.println(FORMATBAR);
                    String desc;
                    while (true) {
                        System.out.print("Enter Event Description: ");
                        desc = sc.nextLine();
                        if (!desc.isEmpty() && !desc.contains("\",\"")) {
                            break;
                        }
                        System.out.println("Invalid event description. Try again.\n" + FORMATBAR);
                    }
                    profile.addEvent(new Event(dt, desc));
                    break;
                case "3":
                    profile.viewFriends();
                    break;
                case "4":
                    System.out.print("Enter friend's email: ");
                    while (true) {
                        System.out.print("Enter your friend's email: ");
                        String email = sc.nextLine();
                        if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                            profile.addOrRemoveFriend(email);
                            break;
                        }
                        System.out.println("Invalid email. Try again.\n" + FORMATBAR);
                    }
                    break;
                case "5":
                    try {
                        profile.saveToFile();
                    } catch (IOException | IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                    }
                    return;
                default:
                    System.out.println("Invalid choice. Select (1-5).");
            }
        }
    }
}