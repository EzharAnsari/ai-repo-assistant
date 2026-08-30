# Environment Variables Setup Guide

This document explains how to configure environment variables for the AI Repository Assistant project.

## Project Structure

```
ai-repo-assistant/
├── .env                          # Root-level env (docker-compose)
├── .env.example                  # Template for root env
├── backend/
│   ├── .env                      # Backend secrets & config
│   └── .env.example              # Backend template
├── client/
│   ├── .env.local                # Frontend config
│   └── .env.local.example        # Frontend template
└── docker-compose.yml            # Uses root .env
```

## Quick Start Setup

### 1. Backend Setup

```bash
cd backend
cp .env.example .env
# Edit .env and fill in your actual values:
# - DB_PASSWORD: PostgreSQL password
# - OPENROUTER_API_KEY: Your OpenRouter API key
# - GITHUB_CLIENT_ID: Your GitHub OAuth Client ID
# - GITHUB_CLIENT_SECRET: Your GitHub OAuth Client Secret
# - TOKEN_ENCRYPTOR_PASSWORD: Generate with: openssl rand -base64 32
# - TOKEN_ENCRYPTOR_SALT: Generate with: openssl rand -hex 8
```

### 2. Frontend Setup

```bash
cd client
cp .env.local.example .env.local
# Edit .env.local and fill in:
# - NEXT_PUBLIC_API_URL: http://localhost:8080 (for development)
# - NEXT_PUBLIC_GITHUB_CLIENT_ID: Same as backend
```

### 3. Docker Compose Setup

```bash
cd ..
cp .env.example .env
# Edit .env - usually defaults are fine for local development
```

## Environment Variable Reference

### Backend (.env)

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `DB_URL` | Yes | - | PostgreSQL connection URL |
| `DB_USERNAME` | Yes | - | Database username |
| `DB_PASSWORD` | Yes | - | Database password |
| `OPENROUTER_API_KEY` | Yes | - | OpenRouter API key for AI |
| `GITHUB_CLIENT_ID` | Yes | - | GitHub OAuth Client ID |
| `GITHUB_CLIENT_SECRET` | Yes | - | GitHub OAuth Client Secret |
| `FRONTEND_URL` | No | http://localhost:3000 | Frontend base URL |
| `CORS_ALLOWED_ORIGINS` | No | http://localhost:3000 | CORS allowed origins |
| `TOKEN_ENCRYPTOR_PASSWORD` | Yes | - | Password for token encryption |
| `TOKEN_ENCRYPTOR_SALT` | Yes | - | Salt for token encryption |

### Frontend (.env.local)

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `NEXT_PUBLIC_API_URL` | Yes | - | Backend API URL |
| `NEXT_PUBLIC_GITHUB_CLIENT_ID` | No | - | GitHub OAuth Client ID (optional) |
| `NEXT_PUBLIC_APP_NAME` | No | AI Repository Assistant | App display name |
| `NEXT_PUBLIC_DEBUG_MODE` | No | false | Enable debug logging |

### Docker Compose (.env)

| Variable | Required | Default | Description |
|----------|----------|---------|-------------|
| `DB_USERNAME` | No | postgres | PostgreSQL username |
| `DB_PASSWORD` | No | postgres | PostgreSQL password |
| `DB_NAME` | No | mydb | PostgreSQL database name |

## How to Load Environment Variables

### Backend (Spring Boot)

Spring Boot automatically loads environment variables and `.env` files using the following priority:

1. System environment variables (highest priority)
2. `.env` file in the project root
3. Application properties defaults (if any)

To run with Spring Boot:
```bash
cd backend
./mvnw spring-boot:run
```

The `.env` file will be automatically loaded.

### Frontend (Next.js)

Next.js automatically loads environment variables from:

1. `.env.local` (local development)
2. `.env.production` (production build)
3. System environment variables

To run development server:
```bash
cd client
npm run dev
```

The `.env.local` file will be automatically loaded.

### Docker Compose

Docker Compose loads environment variables from `.env` file in the same directory:

```bash
docker-compose up -d
```

## Important Security Notes

### ⚠️ DO NOT

- ❌ Commit `.env` files to Git
- ❌ Share `.env` files in chat/email
- ❌ Use weak passwords or default credentials in production
- ❌ Commit API keys or secrets to version control
- ❌ Use the same credentials across environments

### ✅ DO

- ✅ Copy `.env.example` to `.env` for your local setup
- ✅ Generate strong encryption keys for production
- ✅ Use different credentials for dev/staging/production
- ✅ Use `.gitignore` to exclude `.env` files
- ✅ Store production secrets in a secure vault (AWS Secrets Manager, HashiCorp Vault, etc.)

## Generating Secure Values

### Generate Strong Password
```bash
# OpenSSL (Linux/Mac)
openssl rand -base64 32

# PowerShell (Windows)
[Convert]::ToBase64String((1..32 | ForEach-Object {[byte](Get-Random -Max 256)}))
```

### Generate Encryption Salt
```bash
# OpenSSL
openssl rand -hex 8

# PowerShell
-join (1..8 | ForEach-Object {"{0:x1}" -f (Get-Random -Max 16)})
```

## Production Deployment

For production deployment:

1. **Use Environment Variables, NOT .env files**
   - Set environment variables directly in your deployment platform
   - AWS: Use Secrets Manager or Parameter Store
   - Heroku: Use Config Vars
   - Docker: Use compose override files or orchestration tools

2. **Never commit secrets to version control**

3. **Use secure secret management tools**
   - AWS Secrets Manager
   - HashiCorp Vault
   - Azure Key Vault
   - GitHub Secrets (for CI/CD)

4. **Rotate secrets periodically**

5. **Enable audit logging for secret access**

## Troubleshooting

### Variables not loading in Backend

1. Check `.env` file exists: `ls -la backend/.env`
2. Verify format: `KEY=VALUE` (no quotes)
3. Restart backend: `./mvnw spring-boot:run`

### Variables not loading in Frontend

1. Check `.env.local` file exists: `ls -la client/.env.local`
2. Restart dev server: `npm run dev`
3. Verify variables are prefixed with `NEXT_PUBLIC_` (only these are exposed to browser)

### Docker Compose not finding variables

1. Check `.env` in project root: `ls -la .env`
2. Verify file has correct variables
3. Recreate containers: `docker-compose down && docker-compose up -d`

## References

- [Spring Boot Environment Variables](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [Next.js Environment Variables](https://nextjs.org/docs/basic-features/environment-variables)
- [Docker Compose .env file](https://docs.docker.com/compose/environment-variables/set-environment-variables/)
- [Twelve-Factor App Config](https://12factor.net/config)
