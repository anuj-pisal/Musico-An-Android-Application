
# 🎵 Musico

Musico is a beautifully designed Android music player application that allows users to play songs, create playlists, and manage their music collection with ease.

---

## 📱 Features

- 🎶 **Play Songs from Internal Storage**  
  Scan and play audio files directly from the device.

- 📂 **Create and Manage Playlists**  
  Users can create, rename, and delete custom playlists.

- 🖼️ **Display Album Art**  
  Songs with album art are displayed in the player and library.

- 🔍 **Search Songs**  
  Quickly search through your music library with an intuitive search UI.

- 🎛️ **Custom Player UI**  
  Includes play, pause, skip, and a waveform-style visualizer animation.

- 🧭 **Easy Navigation**  
  Bottom navigation bar with `Home`, `Search`, and `Library` sections.

---

## 🛠️ Tech Stack

- **Language:** Java  
- **IDE:** Android Studio  
- **Media Playback:** `MediaPlayer` API  
- **Database:** `SQLite` for playlist management  
- **UI Components:** RecyclerView, Custom Views, TabLayout, Fragments

---

## 📂 Folder Structure

```
Musico/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/musico/
│   │   │   │   ├── activities/       # MainActivity, SearchActivity, etc.
│   │   │   │   ├── adapters/         # RecyclerView Adapters
│   │   │   │   ├── fragments/        # Home, Library, Search, etc.
│   │   │   │   ├── models/           # Song, Playlist models
│   │   │   │   └── utils/            # Database helper, Music loader, etc.
│   │   │   └── res/                  # Layouts, Drawables, etc.
│   ├── AndroidManifest.xml
├── build.gradle
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Dolphin or higher  
- Android device/emulator with API level 23+

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/musico.git
```

2. Open the project in Android Studio.

3. Let Gradle sync and build the project.

4. Run the app on an emulator or connected Android device.

---

## 💡 Future Enhancements

- Add dark/light theme switch  
- Add support for online streaming  
- Add lyrics sync functionality  
- Improve search with filters and sorting  

---

## 👤 Author

**Anuj Pisal**  
🔗 GitHub: [@anuj-pisal](https://github.com/anuj-pisal)

---

## 📄 License

This project is licensed under an Open License. You are free to use, modify, and distribute the project under the terms mentioned in the license file.
