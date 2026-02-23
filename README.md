# 📚 Bookpedia

A cross-platform book discovery app for **Android**, **iOS**, and **Desktop**, built with **Kotlin Multiplatform** and **Compose Multiplatform**.  
Search millions of books powered by the **Open Library API**, explore details, and save your favourites — all from a single shared codebase.

---

## 📋 Table of Contents

- Features
- Tech Stack
- Getting Started
- Project Structure
- License

---

## ✨ Features

- 🔍 **Search & browse books** — Find books instantly using the [Open Library API](https://openlibrary.org/developers/api)
- 📖 **View book details** — Explore in-depth information including title, author, description, and cover
- ❤️ **Save favourites** — Bookmark your favourite books for quick access anytime

---

## 🛠️ Tech Stack

| Technology                | Description                                      |
|---------------------------|--------------------------------------------------|
| Kotlin Multiplatform      | Shared business logic for Android, iOS & Desktop |
| Compose Multiplatform     | Shared UI across all platforms                   |
| Ktor                      | HTTP client for Open Library API calls           |
| Kotlinx Serialization     | JSON parsing                                     |
| Swift                     | iOS app entry point                              |
| Gradle (Kotlin DSL)       | Build system                                     |

---

## 🚀 Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Hedgehog or newer recommended)
- [Xcode](https://developer.apple.com/xcode/) 15+ _(for iOS builds — macOS only)_
- JDK 17+
- Kotlin Multiplatform plugin installed in Android Studio

### Clone the Repository

```bash
git clone https://github.com/M0bileDev/BookpediaKmpAppProject.git
cd BookpediaKmpAppProject
```

---

### 🤖 Android

#### Run from Android Studio

Open the project in Android Studio, select the `composeApp` run configuration from the toolbar, and hit **Run**.

#### Run from Terminal

```bash
# macOS / Linux
./gradlew :composeApp:assembleDebug

# Windows
.\gradlew.bat :composeApp:assembleDebug
```

Install on a connected device or emulator:

```bash
./gradlew :composeApp:installDebug
```

---

### 🍎 iOS

> Requires macOS with Xcode installed.

Open the `/iosApp` directory in Xcode:

```bash
open iosApp/iosApp.xcodeproj
```

Select your target device or simulator and press **Run** (`⌘R`).

---

### 🖥️ Desktop

Run the desktop app directly from the terminal:

```bash
# macOS / Linux
./gradlew :composeApp:run

# Windows
.\gradlew.bat :composeApp:run
```

---

## 📁 Project Structure

```
BookpediaKmpAppProject/
├── composeApp/
│   └── src/
│       ├── commonMain/         # Shared UI, business logic, API calls (KMP + Compose)
│       ├── androidMain/        # Android-specific implementations
│       ├── iosMain/            # iOS-specific implementations
│       └── desktopMain/        # Desktop (JVM)-specific implementations
├── iosApp/
│   └── iosApp/                 # iOS app entry point (Swift / SwiftUI)
├── gradle/                     # Gradle wrapper files
├── build.gradle.kts            # Root build configuration
├── settings.gradle.kts         # Project settings
└── gradle.properties           # Gradle properties
```

---

## 📄 License

This project is licensed under the [Apache 2.0 License](LICENSE).
