# Backend - AI Repository Assistant

A Spring Boot REST API backend that powers the AI Repository Assistant platform. This service handles authentication, repository management, vector embeddings, and AI-powered chat interactions.

## 📋 Overview

The backend provides:
- RESTful API endpoints for repository management
- GitHub OAuth2 authentication
- AI-powered semantic search using OpenAI embeddings
- Vector storage with PostgreSQL + pgvector
- User session management
- Repository indexing and metadata extraction

## 🛠️ Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 4.1.1 |
| Language | Java | 21 |
| Build Tool | Maven | Latest |
| Database | PostgreSQL | 16 |
| ORM | JPA + Hibernate | Latest |
| Authentication | Spring Security + OAuth2 | 4.1.1 |
| AI Integration | Spring AI | 2.0.1 |
| Vector Store | pgvector | Latest |

## 📦 Core Dependencies

### Core Spring Boot Starters
```xml
spring-boot-starter-web             # Web MVC & REST API
spring-boot-starter-data-jpa        # JPA & Database ORM
spring-boot-starter-security        # Security framework
spring-boot-starter-security-oauth2-client  # OAuth2 support
```

### AI & Vector Store
```xml
spring-ai-starter-model-openai           # OpenAI integration
spring-ai-starter-vector-store-pgvector  # pgvector support
```

### Database
```xml
postgresql          # PostgreSQL JDBC driver
spring-ai-bom      # Spring AI BOM for dependency management
```

### Development & Testing
```xml
lombok              # Reduce boilerplate code
spring-boot-starter-test       # Testing framework
spring-boot-starter-security-test  # Security testing
```

## 🏗️ Project Structure

```
backend/
├── src/main/java/ez/backend/
│   ├── BackendApplication.java       # Application entry point
│   │
│   ├── config/                       # Spring configuration
│   │   ├── SecurityConfig.java       # OAuth2 & security setup
│   │   ├── AIConfig.java            # Spring AI configuration
│   │   └── WebConfig.java           # Web MVC configuration
│   │
│   ├── controllers/                  # REST API endpoints
│   │   ├── ChatController.java       # Chat endpoints
│   │   ├── RepositoryController.java # Repository CRUD
│   │   ├── UserController.java       # User management
│   │   └── AuthController.java       # Authentication
│   │
│   ├── services/                     # Business logic
│   │   ├── ChatService.java          # Chat functionality
│   │   ├── RepositoryService.java    # Repository operations
│   │   ├── EmbeddingService.java     # Vector embeddings
│   │   ├── UserService.java          # User management
│   │   └── IndexingService.java      # Repository indexing
│   │
│   ├── repository/                   # JPA repositories
│   │   ├── RepositoryRepository.java
│   │   ├── UserRepository.java
│   │   ├── ChatRepository.java
│   │   └── EmbeddingRepository.java
│   │
│   ├── entity/                       # JPA entities
│   │   ├── User.java                 # User entity
│   │   ├── Repository.java           # Repository entity
│   │   ├── ChatSession.java          # Chat session
│   │   └── Embedding.java            # Vector embedding
│   │
│   ├── dto/                          # Data transfer objects
│   │   ├── ChatRequest.java
│   │   ├── ChatResponse.java
│   │   ├── RepositoryDTO.java
│   │   └── UserDTO.java
│   │
│   ├── security/                     # Security-related classes
│   │   ├── OAuth2SuccessHandler.java
│   │   ├── CustomUserDetails.java
│   │   └── JwtTokenProvider.java
│   │
│   └── exceptions/                   # Custom exceptions
│       ├── RepositoryNotFoundException.java
│       ├── UnauthorizedException.java
│       └── ChatException.java
│
├── src/main/resources/
│   ├── application.properties        # Main configuration
│   └── application-{profile}.properties  # Profile configs
│
├── src/test/java/                   # Unit & integration tests
├── pom.xml                          # Maven configuration
├── mvnw / mvnw.cmd                  # Maven wrapper
└── HELP.md                          # Spring Boot help docs
```

## 🚀 Getting Started

### Prerequisites
- Java 21+ installed
- Maven 3.9+
- PostgreSQL 16 running
- Docker (recommended for PostgreSQL)

### Installation

1. **Clone and navigate to backend**
   ```bash
   git clone https://github.com/yourusername/ai-repo-assistant.git
   cd ai-repo-assistant/backend
   ```

2. **Configure environment variables using .env file**
   ```bash
   # Copy template
   cp .env.example .env
   
   # Edit .env with your actual values
   nano .env  # or use your preferred editor
   ```
   
   Required configuration in `.env`:
   ```env
   DB_URL=jdbc:postgresql://localhost:5433/mydb
   DB_USERNAME=postgres
   DB_PASSWORD=your_secure_password
   OPENROUTER_API_KEY=sk-or-v1-your-actual-key
   GITHUB_CLIENT_ID=your_github_client_id
   GITHUB_CLIENT_SECRET=your_github_client_secret
   FRONTEND_URL=http://localhost:3000
   CORS_ALLOWED_ORIGINS=http://localhost:3000
   TOKEN_ENCRYPTOR_PASSWORD=generate-secure-value
   TOKEN_ENCRYPTOR_SALT=generate-secure-value
   ```
   
   See [.env.example](.env.example) for all available options.

3. **Build the project**
   ```bash
   ./mvnw clean package
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   Or after building:
   ```bash
   java -jar target/backend-0.0.1-SNAPSHOT.jar
   ```

5. **Verify startup**
   ```bash
   curl http://localhost:8080/health
   ```

## ⚙️ Configuration

### Environment Variables (.env file)

All configuration is managed through environment variables loaded from `.env` file.

**File**: `backend/.env`
**Template**: `backend/.env.example`

Spring Boot automatically loads environment variables in the following order:
1. System environment variables (highest priority)
2. `.env` file in the project root
3. Default values in `application.properties` (REMOVED for security)

### Configuration Reference

#### Database Configuration
| Variable | Purpose | Example |
|----------|---------|---------|
| `DB_URL` | PostgreSQL connection URL | `jdbc:postgresql://localhost:5433/mydb` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `your_secure_password` |

#### LLM Configuration
| Variable | Purpose | Example |
|----------|---------|---------|
| `OPENROUTER_API_KEY` | OpenRouter API key | `sk-or-v1-xxx...` |

#### GitHub OAuth2 Configuration
| Variable | Purpose | Source |
|----------|---------|--------|
| `GITHUB_CLIENT_ID` | GitHub OAuth App ID | [GitHub Settings](https://github.com/settings/developers) |
| `GITHUB_CLIENT_SECRET` | GitHub OAuth App Secret | [GitHub Settings](https://github.com/settings/developers) |

#### Application Configuration
| Variable | Purpose | Example |
|----------|---------|---------|
| `FRONTEND_URL` | Frontend base URL | `http://localhost:3000` |
| `CORS_ALLOWED_ORIGINS` | CORS allowed origins | `http://localhost:3000` |
| `TOKEN_ENCRYPTOR_PASSWORD` | Token encryption password | Generated secure value |
| `TOKEN_ENCRYPTOR_SALT` | Token encryption salt | Generated secure value |

### application.properties

The `application.properties` file references environment variables WITHOUT defaults:

```properties
# All values MUST be provided via environment variables
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.ai.openai.api-key=${OPENROUTER_API_KEY}
spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID}
spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET}

# ... more configuration
```

## 🔐 Security Configuration

The backend implements:
- **OAuth2 Client**: GitHub authentication
- **Spring Security**: Protected endpoints
- **CORS**: Configured for frontend origin via `CORS_ALLOWED_ORIGINS`
- **Token Encryption**: All tokens encrypted with `TOKEN_ENCRYPTOR_PASSWORD`
- **Session Management**: Secure HTTP-only cookies
- **HTTPS**: Recommended for production

### Important Security Notes

✅ **Best Practices:**
- All sensitive values stored in `.env` files
- `.env` files excluded from Git via `.gitignore`
- No hardcoded secrets in source code
- Environment-specific configuration
- Secure encryption keys for token storage

❌ **Never:**
- Commit `.env` files
- Share secrets via email or chat
- Use default/weak passwords
- Hardcode API keys in code
- Use same credentials across environments

### Security Endpoints
- `POST /auth/login` - GitHub OAuth login endpoint
- `GET /oauth2/authorization/github` - OAuth2 flow
- `GET /auth/callback` - OAuth2 callback handler
- `POST /auth/logout` - Logout endpoint
- Protected endpoints require authentication token

## 🤖 AI Integration

### Spring AI Configuration
- **Provider**: OpenAI via OpenRouter
- **Chat Model**: `gpt-4o-mini`
- **Embedding Model**: `text-embedding-3-small`
- **Vector Store**: PostgreSQL with pgvector

### Chat Flow
1. User sends message
2. Context retrieved from vector store
3. Message + context sent to LLM
4. Response streamed back to frontend
5. Chat saved to database

## 📊 Database Schema

### Key Entities

**User**
- Stores user profile & GitHub OAuth information
- Manages authentication state
- Links repositories

**Repository**
- Stores repository metadata
- Tracks indexing status
- Associated with User

**ChatSession**
- Stores conversation history
- Associated with Repository
- Persists messages & responses

**Embedding**
- Vector representations of code
- pgvector format
- Indexed for semantic search

## 🧪 Testing

Run unit and integration tests:
```bash
./mvnw test
```

Run with coverage:
```bash
./mvnw clean test jacoco:report
```

Test files location: `src/test/java/ez/backend/`

## 📝 API Endpoints

### Authentication
- `POST /auth/login` - GitHub OAuth login
- `POST /auth/logout` - Logout
- `GET /auth/me` - Current user info

### Repositories
- `GET /api/repositories` - List user's repositories
- `POST /api/repositories` - Add new repository
- `GET /api/repositories/{id}` - Get repository details
- `PUT /api/repositories/{id}` - Update repository
- `DELETE /api/repositories/{id}` - Delete repository
- `POST /api/repositories/{id}/index` - Start indexing

### Chat
- `POST /api/chat` - Send message
- `GET /api/chat/{sessionId}` - Get chat history
- `DELETE /api/chat/{sessionId}` - Delete session

### User
- `GET /api/user/profile` - Get user profile
- `PUT /api/user/profile` - Update profile
- `GET /api/user/settings` - Get settings
- `PUT /api/user/settings` - Update settings

## 🔧 Development

### Hot Reload
The project uses Spring Boot DevTools for automatic restart:
```bash
./mvnw spring-boot:run
```

### IDE Setup
- **IntelliJ IDEA**: Open `pom.xml` as project
- **VS Code**: Use extension "Extension Pack for Java"
- **Eclipse**: Use Maven Import

### Code Style
- Follow Google Java Style Guide
- Use Lombok for getters/setters
- 2-space indentation

## 📦 Building for Production

### Create JAR
```bash
./mvnw clean package -DskipTests
```

### Docker Image (if configured)
```bash
./mvnw spring-boot:build-image
```

### Environment Variables for Production
- `SPRING_DATASOURCE_URL` - Production database URL
- `SPRING_DATASOURCE_USERNAME` - DB username
- `SPRING_DATASOURCE_PASSWORD` - DB password
- `SPRING_AI_OPENAI_API_KEY` - OpenAI API key
- `GITHUB_CLIENT_ID` - GitHub OAuth ID
- `GITHUB_CLIENT_SECRET` - GitHub OAuth secret

## 🐛 Troubleshooting

### Database Connection Issues
```bash
# Test connection
psql -h localhost -p 5433 -U postgres -d mydb
```

### API Not Starting
- Check Java version: `java -version` (must be 21+)
- Check port 8080 availability: `netstat -tuln | grep 8080`
- Review logs: `./mvnw spring-boot:run` with full output

### OAuth2 Issues
- Verify GitHub OAuth app credentials
- Ensure redirect URI matches: `http://localhost:3000/auth/callback`
- Check application.properties for correct client ID/secret

## 📚 Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa)
- [Spring Security Guide](https://docs.spring.io/spring-security)
- [Spring AI Documentation](https://docs.spring.io/spring-ai)
- [OpenAI API Reference](https://openai.com/docs/api/chat)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -am 'Add feature'`
4. Push branch: `git push origin feature/amazing-feature`
5. Submit Pull Request

## 📄 License

MIT License - See LICENSE file for details

---

**Backend is ready to power your AI repository assistant!** 🚀
