#  JavaFX Coding Game with Gemini API

## 🌟 Project Overview

This project is an interactive **Java coding game** built with **JavaFX**, leveraging the **Gemini API** to provide **questions, answers, and explanations** to enhance the learning experience.

![version](https://img.shields.io/badge/version-1.0-blue) ![Issues](https://img.shields.io/github/issues/ilagouilly/JavaFX-CodingQuiz-App) ![Pull Requests](https://img.shields.io/github/issues-pr/ilagouilly/JavaFX-CodingQuiz-App)

## 🛠 Tech Stack

![Java](https://img.shields.io/badge/Java-11-red?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-13-blue?style=for-the-badge)
![Gemini API](https://img.shields.io/badge/Gemini%20API-gray?style=for-the-badge&logo=google)

## 🔧 Key Technologies

- **Language**: Java 11
- **UI Framework**: JavaFX 13
- **AI Integration**: Google Gemini API
- **Environment Variables**: `.env` file for API key management

## 🚀 Getting Started

### Prerequisites

- Java 11 JDK
- Maven 3.8.0
- Internet connection (for Gemini API requests)

### Local Development Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO_NAME.git
   cd YOUR_REPO_NAME
   ```

2. Create an `.env` file at the project root and add your Gemini API key:
   ```
   API_KEY=XXX
   ```
   Get your API key from [Google AI Gemini API](https://ai.google.dev/gemini-api/docs/api-key).

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn javafx:run
   ```

## 🎮 How It Works

1. **User selects a coding challenge** from the game menu.
2. **Gemini API generates a question** and multiple-choice answers.
3. **User submits an answer** and receives **instant feedback & explanation** from the AI.
4. **Scoring system** tracks user progress and provides insights.

## 🔒 Security & API Key Management

- **DO NOT** expose your API key in version control.

- Add `.env` to your `.gitignore` file:
  ```
  echo .env >> .gitignore
  ```

- Use a library like `dotenv` in Java to securely load environment variables.

## 🤝 Contributing

![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen?style=for-the-badge&logo=github)

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/new-game-mode`).
3. Commit changes (`git commit -m 'Add new game mode'`).
4. Push to your branch (`git push origin feature/new-game-mode`).
5. Open a Pull Request.

## 📄 License

[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

Distributed under the MIT License. See `LICENSE` for details.

---

