# Quick Setup Guide - Environment Variables

Follow these steps to configure your environment variables after cloning the repository.

## 1️⃣ Backend Setup (5 minutes)

### Step 1: Copy the template
```bash
cd backend
cp .env.example .env
```

### Step 2: Get your credentials

Before filling in `.env`, you'll need:

**GitHub OAuth App** (2 minutes):
1. Go to https://github.com/settings/developers
2. Click "New OAuth App" or use existing app
3. Copy `Client ID` and `Client Secret`
4. Set Authorization callback URL to: `http://localhost:3000/auth/callback`

**OpenRouter API Key** (1 minute):
1. Go to https://openrouter.ai/keys
2. Create new API key (if don't have one)
3. Copy the key

**Generate Secure Encryption Keys** (1 minute):
```bash
# On Linux/Mac
openssl rand -base64 32  # This is TOKEN_ENCRYPTOR_PASSWORD
openssl rand -hex 8      # This is TOKEN_ENCRYPTOR_SALT

# On Windows PowerShell
[Convert]::ToBase64String((1..32 | ForEach-Object {[byte](Get-Random -Max 256)}))
-join (1..8 | ForEach-Object {"{0:x1}" -f (Get-Random -Max 16)})
```

### Step 3: Edit backend/.env
```bash
# Open in your editor (nano, vim, VS Code, etc.)
nano .env
```

Fill in these values:
```env
# Database - keep as is for local dev
DB_URL=jdbc:postgresql://localhost:5433/mydb
DB_USERNAME=postgres
DB_PASSWORD=postgres

# Your OpenRouter API key (from step 2)
OPENROUTER_API_KEY=sk-or-v1-your-actual-key-here

# Your GitHub OAuth credentials (from step 2)
GITHUB_CLIENT_ID=your_github_client_id_here
GITHUB_CLIENT_SECRET=your_github_client_secret_here

# Keep these for local dev
FRONTEND_URL=http://localhost:3000
CORS_ALLOWED_ORIGINS=http://localhost:3000

# Your generated secure values (from step 2)
TOKEN_ENCRYPTOR_PASSWORD=your-secure-password-here
TOKEN_ENCRYPTOR_SALT=your-secure-salt-here
```

### Step 4: Save and verify
```bash
# Test if backend can read the .env
cat .env | head -5
```

---

## 2️⃣ Frontend Setup (2 minutes)

### Step 1: Copy the template
```bash
cd ../client  # from backend directory
cp .env.local.example .env.local
```

### Step 2: Edit client/.env.local
```bash
# Open in your editor
nano .env.local
```

Minimum required:
```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

Optional (usually not needed for local dev):
```env
NEXT_PUBLIC_GITHUB_CLIENT_ID=your_github_client_id_here
NEXT_PUBLIC_APP_NAME=AI Repository Assistant
NEXT_PUBLIC_DEBUG_MODE=false
```

### Step 3: Save
```bash
# Verify file was created
cat .env.local
```

---

## 3️⃣ Docker Compose Setup (1 minute)

### Step 1: Copy template to root
```bash
cd ..  # Go to project root
cp .env.example .env
```

### Step 2: Verify (no changes needed for local dev)
```bash
cat .env
```

Should show:
```env
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_NAME=mydb
```

---

## 4️⃣ Start Everything

### Step 1: Start PostgreSQL
```bash
docker-compose up -d
```

Verify it's running:
```bash
docker ps | grep postgres
```

### Step 2: Start Backend
```bash
cd backend
./mvnw spring-boot:run
```

Wait for: `Started BackendApplication in X seconds`

### Step 3: Start Frontend (new terminal)
```bash
cd client
npm install  # Only first time
npm run dev
```

### Step 4: Open in browser
- Frontend: http://localhost:3000
- Backend: http://localhost:8080

---

## 🚨 Important Security Notes

### DO NOT
- ❌ Commit `.env` files
- ❌ Share `.env` files via email/chat
- ❌ Use real passwords shown in public

### DO
- ✅ Keep `.env` files local only
- ✅ Use strong passwords in production
- ✅ Rotate credentials periodically
- ✅ Never share API keys

### Verify .gitignore is working
```bash
# These should all show .env is ignored
git check-ignore .env
git check-ignore backend/.env
git check-ignore client/.env.local
```

Should output the file path (meaning it's ignored).

---

## ❓ Troubleshooting

### Backend won't start
```bash
# Check if .env exists
ls -la backend/.env

# Check if variables are set
cat backend/.env | grep "DB_URL"

# Try running with explicit port
export SERVER_PORT=8080
./mvnw spring-boot:run
```

### Frontend won't start
```bash
# Check if .env.local exists
ls -la client/.env.local

# Check if API URL is set
cat client/.env.local | grep "API_URL"

# Clear node_modules and reinstall
rm -rf node_modules package-lock.json
npm install
npm run dev
```

### Docker PostgreSQL won't start
```bash
# Check if running
docker ps | grep postgres

# Check logs
docker logs postgres_container

# Restart
docker-compose down
docker-compose up -d
```

### Can't find OpenRouter API key
1. Visit: https://openrouter.ai/keys
2. Create account if needed
3. Click "Create Key"
4. Copy the key starting with `sk-or-v1-`

### Can't find GitHub OAuth credentials
1. Visit: https://github.com/settings/developers
2. Click "New OAuth App" (or edit existing)
3. Fill in:
   - Application name: "AI Repository Assistant"
   - Homepage URL: `http://localhost:3000`
   - Authorization callback URL: `http://localhost:3000/auth/callback`
4. Copy Client ID and generate Client Secret

---

## 📚 Next Steps

After setup is complete:
1. Read [ENVIRONMENT_SETUP.md](ENVIRONMENT_SETUP.md) for detailed info
2. Read [README.md](README.md) for project overview
3. Check [backend/README.md](backend/README.md) for API documentation
4. Check [client/CLIENT_README.md](client/CLIENT_README.md) for frontend details

---

## ✅ Setup Verification Checklist

- [ ] Docker is installed and running
- [ ] PostgreSQL container is running (`docker-compose up -d`)
- [ ] Backend `.env` file exists and has all required values
- [ ] Frontend `.env.local` file exists with `NEXT_PUBLIC_API_URL`
- [ ] GitHub OAuth credentials are valid and set
- [ ] OpenRouter API key is valid and set
- [ ] Backend starts successfully (`./mvnw spring-boot:run`)
- [ ] Frontend starts successfully (`npm run dev`)
- [ ] Can access http://localhost:3000 in browser
- [ ] Can access http://localhost:8080 in browser
- [ ] `.env` files are NOT in Git staging area (`git status`)

---

**Time to complete: 10-15 minutes**

**Having issues? Check ENVIRONMENT_SETUP.md for detailed troubleshooting.**
