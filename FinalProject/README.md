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
   1. Full Name must satisfy the following:
      1. String of length > 0
      2. Two words (space in between)
   2. Email must satisfy the following:
      1. String that follows the basic regex (user@domain.tld)
   3. Password must satisfy the following:
      1. String of length >= 8
   4. Class Year must satisfy the following:
      1. Integer +/- 100 years from today
2. Load Profile: Reads `mysocialprofile.txt`, if present
   1. All the above conditions must be met, plus:
   2. Events must satisfy the following:
      1. Each event surrounded by `"` and separated by `,`
      2. Each event contains String with 6 space-separated parts
      3. First 5 parts resemble ``MM dd yyyy HH mm``
   3. Timeline must satisfy the following:
      1. Each post surrounded by `"` and separated by `,`
      2. Each post is String
   4. Friends must satisfy the following:
      1. Each friend surrounded by `"` and separated by `,`
      2. Each friend contains String different from user's email
3. Quit App

## Home Screen
- Upcoming Events: Next Date-ordered Event
- Recent Posts: 3 Latest Timeline Posts
- All Events: Full Chronological List of Events

### Options
1. Post to Timeline: Entered text must adhere to above conditions and mustn't include `","`
2. Add Event: Entered texts must adhere to above conditions and mustn't include `","` nor date later than the current date
3. View Friends: List friends by email
4. Add/Remove Friend: Toggle either by entering valid email; must adhere to above conditions and mustn't include `","` nor be identical to the user's email
5. Log Out: Save session information to `mysocialprofile.txt`

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
- We could have definitely been more specific for each type of user error (input errors), but we thought it would be very cluttered to do that for each type of error as opposed to more generic ones such as "Invalid friends. Fix `mysocialprofile.txt`." Although, looking back at it, it may have helped the user experience a bit.

## Testing
You can comprehensively test the application by following the sequence below. Some are intentinoally "bad" inputs such that you can see how it would handle them; I will place a `#BAD` next to each of them:
```bash
4 #BAD
```
```bash
2
```
```bash
6 #BAD
```
```bash
1
```
```bash
Hello"," #BAD
```
```bash
Hello, world.
```
```bash
2
```
```bash
July 4th, 2025 10:00 #BAD
```
```bash
7 4 2025 10 00 #BAD
```
```bash
07 04 2024 10 00 #BAD
```
```bash
07 04 2025 10 00
```
```bash
Independence","Day Party #BAD
```
```bash
Independence Day Party
```
```bash
3
```
```bash
4
```
```bash
rsaijo@conncoll.edu #BAD
```
```bash
wtarimoconncoll.edu #BAD
```
```bash
wtarimo@conncoll.edu
```
```bash
4
```
```bash
ateryek@conncoll.edu
```
```bash
3
```
```bash
5
```
```bash
2
```
```bash
5
```
```bash
1
```
```bash
Adam #BAD
```
```bash
Adam Teryek
```
```bash
ateryek.edu #BAD
```
```bash
ateryek@conncoll #BAD
```
```bash
ateryek@conncoll.edu
```
```bash
pass123 #BAD
```
```bash
pass1234
```
```bash
1800 #BAD
```
```bash
2200 #BAD
```
```bash
Two-thousand Twenty-six #BAD
```
```bash
2026
```
```bash
1
```
```bash
Wooo, I just created my account!
```
```bash
1
```
```bash
Wait, how do I update my profile picture?
```
```bash
1
```
```bash
Oh, the devs haven't implemented that yet :(
```
```bash
2
```
```bash
08 13 2025 10 00
```
```bash
Back 2 School
```
```bash
2
```
```bash
05 09 2025 16 00
```
```bash
Last Day of Class
```
```bash
3
```
```bash
4
```
```bash
rsaijo@conncoll.edu
```
```bash
4
```
```bash
hmerritt@conncoll.edu
```
```bash
3
```
```bash
5
```
```bash
2
```
```bash
5
```
```bash
3
```
