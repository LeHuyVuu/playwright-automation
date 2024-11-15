#  Automation with Playwright 🎮🚀

This project demonstrates automation of various tasks using the **Playwright** library, including:
1. **YouTube Auto-Skip**: Plays a YouTube video and skips ads automatically.
2. **Dino Game Automation**: Plays the Chrome Dino game by detecting obstacles and jumping over them.
3. **Auto Screenshot Capture**: Scrolls through a webpage and captures screenshots.
4. **Login Demo Test Automation**: Automates login on a sample website.

## Features 📋

The project includes the following automation features:

1. **YouTube Auto-Skip**
   - **Function**: `playYouTubeVideoWithAutoSkip(Page page)`
   - **Description**: Navigates to a YouTube video, auto-clicks the "Skip Ads" button if an ad appears, and simulates fast-forwarding through the video.
   - **Usage**: Run within a loop to continuously play and skip ads on YouTube videos.

2. **Dino Game Automation**
   - **Function**: `dinoGame(Page page)`
   - **Description**: Automates the Chrome Dino game by detecting obstacles and performing jumps to avoid them.
   - **Usage**: Useful for testing or entertainment by letting Playwright play the Dino game.

3. **Auto Screenshot Capture**
   - **Function**: `AutoCapture(Page page, Playwright playwright, Browser browser)`
   - **Description**: Scrolls through a webpage and takes screenshots at each scroll position. Screenshots are saved to a folder.
   - **Usage**: Useful for capturing long pages or documenting webpage content visually.

4. **Login Demo Test Automation**
   - **Function**: `loginDemo(Page page)`
   - **Description**: Demonstrates automated login by filling out a sample login form with test credentials.
   - **Usage**: Adaptable to any website's login flow by modifying locators to match target elements.

## Project Structure 📂
```plaintext
com.huyvuu/
│
├── Main.java            # Main program with different automation methods.
├── README.md            # Project documentation.
└── screenshots/         # Folder to store auto-captured screenshots.
