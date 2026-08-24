# BookNest — Android Notifications (Experiment 5)

## 1. Experiment Title
**Experiment 5 — Android Notifications**

## 2. Objective
To demonstrate the implementation of a real Android notification system in a mobile application. This includes creating notification channels, building notifications with various styles, handling runtime permissions (API 33+), and managing notification tap behaviors.

## 3. Scenario
**BookNest** is a premium free-resource book discovery application.
- **Login Success**: When a user (e.g., Sandy, USN: 25MCAR0133) logs in successfully, the app generates an Android system notification.
- **Continue Reading**: A notification reminds the user of their progress in a specific book (e.g., The Hobbit).
- **Reading Reminder**: A periodic reminder to encourage the user to continue their reading journey.

## 4. Technologies
- **Kotlin**: Primary programming language.
- **Material 3**: For modern UI components.
- **NotificationManagerCompat**: To ensure backward compatibility.
- **Coil**: For efficient image loading (book covers).
- **Glassmorphism**: UI design style using translucency and rounded depth.

## 5. Notification Concepts
- **Notification Channel**: Required for Android 8.0+. We use three channels: Login, Reminders, and Progress.
- **Importance Levels**: `IMPORTANCE_HIGH` for logins and `IMPORTANCE_DEFAULT` for others.
- **Notification Builder**: Used to set icons, titles, content, and big text styles.
- **Runtime Permissions**: Handling `POST_NOTIFICATIONS` for Android 13+.
- **Pending Intents**: Allowing users to navigate back to the app by tapping the notification.

## 6. Application Features
- **Modern Login**: Glassmorphism design with Name and USN input.
- **Dashboard**: "Continue Reading" card and genre exploration.
- **Genre Theming**: Distinct visual adaptions for Fantasy, Sci-Fi, etc.
- **Legitimate Resources**: Direct links to public-domain books on Project Gutenberg.
- **Local Search**: Filter books by title or author.

## 7. Folder Structure
```text
app/
 ├── manifests/
 │    └── AndroidManifest.xml (Permissions & Activities)
 ├── java/com.example.exp5notification/
 │    ├── data/
 │    │    ├── Book.kt (Data Model)
 │    │    └── BookRepository.kt (Dataset of ~50 books)
 │    ├── notifications/
 │    │    └── NotificationHelper.kt (Notification logic)
 │    └── ui/
 │         ├── LoginActivity.kt (Launcher & Login trigger)
 │         ├── HomeFragment.kt (Main dashboard)
 │         ├── GenreDetailsActivity.kt (Genre browsing)
 │         └── BookDetailsActivity.kt (Book info & Read Free)
 └── res/
      ├── drawable/ (Glass backgrounds, icons, gradients)
      ├── layout/ (XML layouts for all screens)
      └── values/ (Themes, colors, strings)
```

## 8. Notification Test Cases

### Test Case 1: Login Success
- **Trigger**: Click "Login" in LoginActivity.
- **Content**: 
  - Title: BookNest
  - Text: Sandy logged in successfully!
  - USN: 25MCAR0133
  - Icon: 📚

### Test Case 2: Continue Reading
- **Trigger**: Land on the Home screen after successful login (Automatic).
- **Content**: 
  - Title: BookNest — Continue Reading
  - Text: Continue reading "The Hobbit" (Chapter 7)

### Test Case 3: Reading Reminder
- **Trigger**: Click "＋ Add to Library" in any book's details.
- **Content**: 
  - Title: BookNest — Reading Reminder
  - Text: Time to continue your book! (The Hobbit — 62%)

## 9. Result
The Android notification system was successfully implemented and verified across all three test scenarios. The application adheres to modern Android design principles and provides a premium user experience.

## 10. Screenshots

![Login screen](./Screenshot%202026-08-24%20203553.png)
![Home screen](./Screenshot%202026-08-24%20203604.png)
![Genre details](./Screenshot%202026-08-24%20203622.png)
![Book details](./Screenshot%202026-08-24%20203651.png)

---
**Developed by: Sandy**
**USN: 25MCAR0133**
