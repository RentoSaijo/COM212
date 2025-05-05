/*
Rento Saijo
Adam Teryek
Hannah Merritt
William Tarimo
4 May 2025
Final Project

This program defines the main application for a simple social network console program;
it provides functionality to create or load a user profile, manage sessions,
post to timeline,add events, manage friends, and save profiles.
*/

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main application class for the social network.
 * Handles user interaction and menu navigation for profile management and sessions.
 */
public class SocialNetworkApp {
    // decorative divider for console output
    static final String FORMATBAR = "------------------------------";
    // single Scanner instance for reading console input
    private static final Scanner sc = new Scanner(System.in);
    // formatter for parsing user-entered date/time strings
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM dd yyyy HH mm");

    /**
     * Entry point of the application. Shows the main menu loop for creating, loading,
     * or quitting the application, and runs user sessions.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MySocialProfile profile = null;
        // main application loop
        while (true) {
            // display the top-level menu
            System.out.println(FORMATBAR + "\n-            MENU            -\n" + FORMATBAR);
            System.out.print("""
                1. Create Profile
                2. Load Profile
                3. Quit App
                """);
            System.out.print("Select an option (1-3): ");
            String choice = sc.nextLine();
            // actions based on the above choice
            switch (choice) {
                case "1":
                    // create a new profile
                    profile = createProfile();
                    break;
                case "2":
                    // attempt to load an existing profile from file
                    try {
                        profile = MySocialProfile.loadFromFile();
                    } catch (IllegalArgumentException | DateTimeParseException |
                             ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Error loading profile.");
                    }
                    break;
                case "3":
                    // exit the application
                    System.out.println(FORMATBAR + "\nApp terminated.\n" + FORMATBAR);
                    System.exit(0);
                    break;
                default:
                    // handle invalid menu selections
                    System.out.println(FORMATBAR + "\nInvalid choice. Select (1-3).");
            }

            // if a profile was created or loaded, enter the user session
            if (profile != null) {
                runSession(profile);
                // after logout, clear the profile reference
                profile = null;
            }
        }
    }

    /**
     * Guides the user through profile creation by prompting for full name, email,
     * password, and class year with validation at each step.
     *
     * @return a new MySocialProfile populated with the user's inputs
     */
    private static MySocialProfile createProfile() {
        MySocialProfile profile = new MySocialProfile();

        // prompt until a valid full name (first + last) is entered
        while (true) {
            System.out.print(FORMATBAR + "\nEnter your full name: ");
            String fullName = sc.nextLine();
            if (fullName.matches("^(\\S+)\\s+(\\S+.*)$")) {
                profile.setFullName(fullName);
                break;
            }
            System.out.println("Invalid full name. Try again.");
        }

        // prompt until a valid email format is entered (compared against regex)
        while (true) {
            System.out.print(FORMATBAR + "\nEnter your email: ");
            String email = sc.nextLine();
            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                profile.setEmail(email);
                break;
            }
            System.out.println("Invalid email. Try again.");
        }

        // prompt until a password of at least 8 characters is entered
        while (true) {
            System.out.print(FORMATBAR + "\nEnter your password (>= 8 chars): ");
            String password = sc.nextLine();
            if (password.length() >= 8) {
                profile.setPassword(password);
                break;
            }
            System.out.println("Invalid password. Try again.");
        }

        // prompt until a reasonable class year is entered (within 100 years of now)
        while (true) {
            System.out.print(FORMATBAR + "\nEnter your class year: ");
            String classYearStr = sc.nextLine();
            try {
                int classYear = Integer.parseInt(classYearStr);
                int currentYear = LocalDateTime.now().getYear();
                if (classYear >= currentYear - 100 && classYear <= currentYear + 100) {
                    profile.setClassYear(classYear);
                    break;
                }
            } catch (NumberFormatException e) {
                // continue prompting if parsing fails
            }
            System.out.println("Invalid class year. Try again.");
        }

        return profile;
    }

    /**
     * Runs the session loop for a given user profile, allowing posting,
     * adding events, viewing/managing friends, and logging out.
     *
     * @param profile the MySocialProfile instance for the current session
     */
    private static void runSession(MySocialProfile profile) {
        // session loop continues until the user chooses to log out
        while (true) {
            // clean up any past events and display the home screen
            profile.purgePastEvents();
            profile.displayHomeScreen();
            System.out.print("""
                
                1. Post to Timeline
                2. Add Event
                3. View Friends
                4. Add/Remove Friend
                5. Log Out
                """);
            System.out.print("Select an option (1-5): ");
            String choice = sc.nextLine();
            System.out.println(FORMATBAR);
            // actions for each choice
            switch (choice) {
                case "1":
                    // create a new timeline post with validation
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
                    // add a new future event, ensuring correct date/time format
                    LocalDateTime dt;
                    while (true) {
                        System.out.print("Enter time for your event (MM dd yyyy HH mm): ");
                        String dtStr = sc.nextLine();
                        try {
                            dt = LocalDateTime.parse(dtStr, inputFormatter);
                            if (dt.isBefore(LocalDateTime.now())) {
                                throw new IllegalArgumentException();
                            }
                            break;
                        } catch (DateTimeParseException | IllegalArgumentException e) {
                            System.out.println("Invalid event time. Try again.\n" + FORMATBAR);
                        }
                    }

                    System.out.println(FORMATBAR);

                    // prompt for event description until valid
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
                    // display the current list of friends
                    profile.viewFriends();
                    break;

                case "4":
                    // add or remove a friend by email, with validation
                    while (true) {
                        System.out.print("Enter your friend's email: ");
                        String email = sc.nextLine();
                        if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")
                                && !email.equals(profile.getEmail())) {
                            profile.addOrRemoveFriend(email);
                            break;
                        }
                        System.out.println("Invalid email. Try again.\n" + FORMATBAR);
                    }
                    break;

                case "5":
                    // save the profile to file and end the session
                    try {
                        profile.saveToFile();
                    } catch (IOException | IllegalArgumentException | DateTimeParseException e) {
                        System.out.println("Error saving profile. Try again.\n" + FORMATBAR);
                    }
                    System.out.println("Profile saved to file.");
                    return;

                default:
                    // handle invalid menu selections
                    System.out.println("Invalid choice. Select (1-5).");
            }
        }
    }
}