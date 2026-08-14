# Minesweeper — Java Swing Edition

A desktop Minesweeper game built with **Java** and **Java Swing**, featuring classic single-player gameplay, **local turn-based multiplayer for 2–8 players on the same computer**, multiple difficulty levels, scoring, sound, save/load support, and autosave.

> **Legacy project note:** This project was originally developed during my early university years and was uploaded to GitHub later. The repository commit date therefore does not represent the original development date.

---

## Highlights

- Classic Minesweeper gameplay
- Java Swing desktop interface
- Object-oriented game logic
- **Local multiplayer for 2–8 players**
  - Players share the same computer and take turns
  - Each player has a name, color, score, and turn state
  - The UI displays the current player
- Multiple difficulty levels
- Per-player scoring system
- Right-click flagging and standard Minesweeper interactions
- Save and load support
- Autosave when closing the game
- Continue previous game
- Pause/resume support
- Sound effects and configurable audio settings
- Timer-based gameplay
- Custom UI components and game screens

---

## Local Multiplayer

The multiplayer mode is intentionally **local / hot-seat multiplayer**, not online networking.

Players use the same computer and mouse. At the start of the game:

1. Select **Multiplayer**.
2. Choose between **2 and 8 players**.
3. Enter each player's name.
4. Each player receives a distinct color.
5. Players take turns interacting with the same Minesweeper board.
6. The game tracks the current player and each player's score.

This was designed as a social same-device variation of the traditional single-player Minesweeper experience.

---

## Project Structure

```text
src/
├── FileHandling/   # Save, load, autosave, serialized game data
├── Game_logic/     # Grid, cells, players, rules, scoring and turn handling
├── Images/         # Runtime image assets
├── Screen/         # Swing windows, menus, settings and game screens
├── Sound/          # Sound effects and audio handling
├── Timer/          # Gameplay timer
├── buttons/        # Custom Swing button components
└── Net/            # Old experimental networking code
```

The main implementation is separated into game logic, GUI, persistence, sound, timing, and reusable UI components.

---

## Core Classes

### Game Logic
- `Cell` — stores cell state and value
- `Grid` — manages the Minesweeper board
- `Player` — stores player name, color, score, and turn state
- `Rules` — game rules, scoring, player switching, and win/loss behavior

### User Interface
- `Home` — main menu and player setup
- `DIfficulty` — difficulty selection
- `game` — main gameplay window
- `Settings` — game/audio settings
- `SaveScreen` / `LoadGameGui` — persistence UI

### Persistence
- `SaveGame`
- `LoadGame`
- `AutoSave`
- `GameData`

---

## Technical Notes

The project demonstrates practical use of:

- Java OOP
- Java Swing
- Event-driven programming
- Mouse event handling
- Serialization
- File-based persistence
- Game-state management
- Turn-based multiplayer logic
- Custom scoring
- Timers and sound
- Separation of logic and UI into packages

Because this is an older learning project, the code also reflects an earlier stage of my software-development journey. I am keeping it public as part of my portfolio to show that progression.

---

## Screenshots

> Screenshots will be added after a clean local run of the current repository version.

Recommended captures:

- Main menu
- Single-player board
- Multiplayer player-selection screen
- Multiplayer gameplay showing the current player
- Difficulty selection
- Settings
- Save/load interface

---

## Running the Project

The repository was originally developed as an IntelliJ IDEA Java project.

### Requirements

- Java JDK
- IntelliJ IDEA or another Java IDE

### Recommended setup

1. Clone the repository.
2. Open it as a Java project.
3. Mark `src` as the source root if required by your IDE.
4. Ensure the image and sound resources are available on the runtime classpath.
5. Run the project's main entry point.

> A cleaner build/run workflow can be added later using Maven or Gradle.

---

## Repository Cleanup

The current public repository was uploaded after the original development period. As part of a portfolio cleanup, generated IDE/build files are being removed while preserving the original source code and behavior.

---

## Possible Future Improvements

If I revisit the project, the highest-value improvements would be:

- Refactor large Swing classes into smaller components
- Introduce Maven or Gradle
- Add automated tests for grid/rules/scoring
- Improve resource organization
- Remove or complete the old experimental networking package
- Add optional LAN/online multiplayer as a **separate feature branch**
- Refresh the visual design while preserving the original game logic

---

## Author

**Fouad Dalloul**

Information Engineering student — Artificial Intelligence specialization  
Backend development, software engineering, AI, and applied systems.
