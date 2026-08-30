# AI-Powered Repository Assistant

A full-stack web application that enables users to chat with their GitHub repositories using AI-powered insights. This project combines a Spring Boot backend with an AI integration and a modern Next.js frontend for a seamless user experience.

## 🎯 Project Overview

**AI Repository Assistant** is a comprehensive platform that allows developers to:
- Authenticate via GitHub OAuth2
- Upload and index their repositories
- Chat with their code using AI-powered analysis
- Get intelligent insights from embeddings stored in PostgreSQL
- View repository statistics and management dashboard

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Frontend (Next.js)                        │
│  - React 19 with TypeScript                                 │
│  - Tailwind CSS & shadcn/ui Components                      │
│  - React Query for Data Fetching                            │
└─────────────────────────────────────────────────────────────┘
                          ↕ (HTTP/REST)
┌─────────────────────────────────────────────────────────────┐
│                 Backend (Spring Boot 4.1.1)                  │
│  - Spring Data JPA for Database Operations                  │
│  - Spring Security with OAuth2 Client                       │
│  - Spring AI with OpenAI Integration                        │
│  - Vector Store with pgvector                               │
└─────────────────────────────────────────────────────────────┘
                          ↕ (PostgreSQL Driver)
┌─────────────────────────────────────────────────────────────┐
│      Database (PostgreSQL 16 with pgvector)                  │
│  - User Data & Session Management                           │
│  - Repository Metadata                                      │
│  - Vector Embeddings for Semantic Search                    │
└─────────────────────────────────────────────────────────────┘
```

## 📦 Tech Stack

### Backend
- **Framework**: Spring Boot 4.1.1 (Java 21)
- **ORM**: JPA with Hibernate
- **Database**: PostgreSQL 16 with pgvector
- **Authentication**: OAuth2 Client (GitHub)
- **AI Integration**: Spring AI + OpenAI/OpenRouter
- **Build Tool**: Maven
- **Security**: Spring Security

### Frontend
- **Framework**: Next.js 16.3.3
- **Language**: TypeScript 5
- **Styling**: Tailwind CSS 4
- **UI Components**: shadcn/ui & Base UI
- **State Management**: React Query (TanStack)
- **Charts**: Recharts
- **Icons**: Lucide React
- **Themes**: next-themes

### Infrastructure
- **Containerization**: Docker & Docker Compose
- **Database Container**: pgvector/pgvector:pg16
- **Port Configuration**: 
  - Frontend: `3000`
  - Backend: `8080` (default)
  - Database: `5433` → `5432`

## 🚀 Quick Start

### Prerequisites
- Java 21+
- Node.js 18+
- Docker & Docker Compose
- GitHub OAuth App credentials

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/ai-repo-assistant.git
   cd ai-repo-assistant
   ```

2. **Configure Environment Variables** (IMPORTANT!)
   
   See [ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md) for detailed setup instructions.
   
   Quick setup:
   ```bash
   # Root level (for Docker)
   cp .env.example .env
   
   # Backend
   cd backend
   cp .env.example .env
   # Edit .env and fill in your actual values
   
   # Frontend
   cd ../client
   cp .env.local.example .env.local
   # Edit .env.local if needed
   ```

3. **Start PostgreSQL**
   ```bash
   docker-compose up -d
   ```
   Verify it's running:
   ```bash
   docker ps | grep postgres
   ```

4. **Backend Setup**
   ```bash
   cd backend
   
   # Build and run
   ./mvnw spring-boot:run
   ```
   Backend will be available at `http://localhost:8080`
   
   ✅ Environment variables are automatically loaded from `.env` file

5. **Frontend Setup**
   ```bash
   cd client
   npm install
   npm run dev
   ```
   Frontend will be available at `http://localhost:3000`
   
   ✅ Environment variables are automatically loaded from `.env.local` file

## 📁 Project Structure

```
ai-repo-assistant/
├── backend/                     # Spring Boot REST API
│   ├── src/main/java/ez/
│   │   ├── controllers/         # REST API endpoints
│   │   ├── services/            # Business logic
│   │   ├── repository/          # Data access layer
│   │   ├── entity/              # JPA entities
│   │   ├── dto/                 # Data transfer objects
│   │   ├── security/            # OAuth2 & security config
│   │   ├── config/              # Spring configuration
│   │   └── exceptions/          # Custom exceptions
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── client/                      # Next.js Frontend
│   ├── app/
│   │   ├── layout.tsx           # Root layout
│   │   ├── page.tsx             # Home page
│   │   ├── auth/                # Authentication pages
│   │   ├── login/               # Login page
│   │   ├── chat/                # Chat interface
│   │   └── dashboard/           # User dashboard
│   ├── components/
│   │   ├── chat/                # Chat components
│   │   ├── dashboard/           # Dashboard components
│   │   ├── layout/              # Layout components
│   │   ├── providers/           # Context providers
│   │   └── ui/                  # Reusable UI components
│   ├── hooks/                   # Custom React hooks
│   ├── lib/                     # Utilities & helpers
│   ├── public/                  # Static assets
│   └── package.json
│
├── docker/
│   └── postgres/
│       └── init-extensions.sql  # PostgreSQL initialization
│
└── docker-compose.yml           # Docker orchestration
```

## 🔑 Key Features

### Authentication
- GitHub OAuth2 integration
- Secure session management
- Protected API endpoints

### Repository Management
- Index multiple repositories
- Extract repository metadata
- Track repository status

### AI Chat
- Semantic search using embeddings
- Context-aware responses
- Code understanding powered by GPT-4o-mini
- Real-time streaming responses

### Dashboard
- Repository overview & statistics
- Indexing status monitoring
- Settings management
- User profile management

## 🔧 Environment Configuration

**IMPORTANT**: All sensitive configuration should be stored in `.env` files, NOT in your source code.

See [ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md) for complete setup instructions.

### Backend Environment Variables (.env)

Create `backend/.env` from `backend/.env.example`:

```env
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5433/mydb
DB_USERNAME=postgres
DB_PASSWORD=your_secure_password

# AI/LLM
OPENROUTER_API_KEY=sk-or-v1-your-actual-key

# GitHub OAuth2 (get from https://github.com/settings/developers)
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret

# Application URLs
FRONTEND_URL=http://localhost:3000
CORS_ALLOWED_ORIGINS=http://localhost:3000

# Token Encryption (generate secure values!)
TOKEN_ENCRYPTOR_PASSWORD=your-secure-encryption-password
TOKEN_ENCRYPTOR_SALT=a1b2c3d4e5f67890
```

### Frontend Environment Variables (.env.local)

Create `client/.env.local` from `client/.env.local.example`:

```env
# Backend API URL
NEXT_PUBLIC_API_URL=http://localhost:8080

# Optional: GitHub OAuth Client ID
NEXT_PUBLIC_GITHUB_CLIENT_ID=your_github_client_id

# Application Settings
NEXT_PUBLIC_APP_NAME=AI Repository Assistant
NEXT_PUBLIC_DEBUG_MODE=false
```

### Root Environment Variables (.env)

Create `.env` for Docker Compose:

```env
# Database Configuration
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_NAME=mydb
```

### Security Best Practices

✅ **DO:**
- Copy `.env.example` to `.env` for local setup
- Store `.env` files in `.gitignore` (already configured)
- Use strong, unique passwords for each environment
- Rotate secrets periodically
- Use production secret vaults for deployment

❌ **DON'T:**
- Commit `.env` files to Git
- Share `.env` files via chat or email
- Use weak or default passwords in production
- Hardcode secrets in application code
- Use the same credentials across environments

## 🗄️ Database Schema

The application uses PostgreSQL with pgvector for:
- User accounts and authentication
- Repository metadata
- Vector embeddings for code context
- Chat history and sessions

Initial setup is handled by `docker/postgres/init-extensions.sql` which enables pgvector extension.

## 🧪 Testing

### Backend Tests
```bash
cd backend
./mvnw test
```

### Frontend Linting
```bash
cd client
npm run lint
```

## 📚 API Documentation

The backend exposes REST endpoints for:
- User authentication & management
- Repository operations (CRUD)
- Repository indexing
- Chat interactions
- Embedding search

Refer to backend controller classes for detailed endpoint documentation.

## 🐛 Known Issues & Limitations

- OAuth2 configuration requires valid GitHub credentials
- OpenRouter API key must be set for AI features
- Vector embeddings require pgvector PostgreSQL extension
- Real-time collaboration features not yet implemented

## 🔄 Development Workflow

1. Make changes to backend or frontend
2. Backend changes trigger automatic restart with Spring Boot DevTools
3. Frontend changes auto-refresh with Next.js Fast Refresh
4. Test changes locally before committing

## 📝 Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Support

For issues, questions, or suggestions, please open an issue on GitHub.

---

## 💡 Suggested GitHub Repository Name

**Recommended names:**
1. **`ai-repo-assistant`** - Clear, concise, and descriptive
2. **`github-ai-chat`** - Emphasizes the chat functionality
3. **`code-compass`** - Catchy name suggesting navigation through code
4. **`repo-ai-hub`** - Hub-focused name
5. **`codebase-companion`** - Friendly, descriptive name

**My top recommendation: `ai-repo-assistant`** - It's straightforward, SEO-friendly, and immediately conveys the project's purpose.

---

Made with ❤️ for developers who want to understand their code better
