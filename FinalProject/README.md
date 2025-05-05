# MySocialProfile Console Application

A simple console-based social networking profile manager for a single user.  
Stores personal info, events (heap-ordered), timeline posts, and friends to `mysocialprofile.txt`.

## Prerequisites
- Java
- Shell/terminal

## Files
- `SNode.java`  
- `SinglyLinkedList.java`  
- `Event.java`  
- `ArrayPriorityQueue.java`  
- `MySocialProfile.java`  
- `SocialNetworkApp.java`

## Compilation
```bash
javac SNode.java SinglyLinkedList.java Event.java ArrayPriorityQueue.java MySocialProfile.java SocialNetworkApp.java
```
Or, simply:
```bash
javac *.java
```

## Running
```bash
java SocialNetworkApp
```

## Main Menu (See Example .txt File for Clarification)
1. Create Profile
  a. Full Name must satisfy the following:
    i. String of length > 0
   ii. Two words (space in between)
  b. Email must satisfy the following:
    i. String that follows the basic regex (user@domain.tld)
  c. Password must satisfy the following:
    i. String of length >= 8
  d. Class Year must satisfy the following:
    i. Integer +/- 100 years from today
2. Load Profile -> Reads `mysocialprofile.txt` if present.
  a. All the above conditions must be met, plus:
  b. Events must satisfy the following:
    i. Each event surrounded by `"` and separated by `,`
   ii. Each event contains String with 6 space-separated parts
  iii. First 5 parts resemble ``MM dd yyyy HH mm``
  c. Timeline must satisfy the following:
    i. Each post surrounded by `"` and separated by `,`
   ii. Each post is String
  d. Friends must satisfy the following:
    i. Each friend surrounded by `"` and separated by `,`
   ii. Each friend contains String different from user's email
3. Quit App

## Home Screen
- Upcoming Events: Next Date-ordered Event
- Recent Posts: 3 Latest Timeline Posts
- All Events: Full Chronological List of Events

# Options
1. Post to Timeline: Entered text must adhere to above conditions and mustn't include `","`
2. Add Event: Entered texts must adhere to above conditions and mustn't include data later than the current date
3. View Friends: List friends by email
4. Add/Remove Friend: Toggle either by entering valid email (must adhere to above conditions)
5. Log OUt: Save session information to `mysocialprofile.txt`

## Data Storage (mysocialprofile.txt)
1. Full Name
2. Email
3. Password
4. Class Year
5. Events (CSV-style, `MM dd yyyy HH mm description`)
6. Timeline (CSV-style)
7. Friends (CSV-style)

## Limitations
- Since this was worked on by multiple people, there are inconsistencies in coding styles and commenting styles, but we tried our best to make the output look as nice as possible altogether.
