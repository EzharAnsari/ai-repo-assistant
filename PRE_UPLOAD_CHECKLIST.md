# Pre-Upload to GitHub Checklist

Use this checklist to verify everything is secure and properly configured before uploading to GitHub.

---

## 🧭 REPOSITORY VERIFICATION

- [ ] Git repository is initialized
  ```bash
  git rev-parse --is-inside-work-tree
  # Should return: true
  ```

- [ ] Remote repository is configured
  ```bash
  git remote -v
  # Should show your GitHub origin URL
  ```

---

## 🔒 SECURITY VERIFICATION

### .env Files (CRITICAL)
- [ ] `backend/.env` exists and is NOT in Git
  ```bash
  git check-ignore backend/.env  # Should output: backend/.env
  ```

- [ ] `client/.env.local` exists and is NOT in Git
  ```bash
  git check-ignore client/.env.local  # Should output: client/.env.local
  ```

- [ ] `.env` exists (root) and is NOT in Git
  ```bash
  git check-ignore .env  # Should output: .env
  ```

- [ ] No `.env` files staged for commit
  ```bash
  git status  # Should NOT list any .env files
  ```

### .gitignore Files
- [ ] Root `.gitignore` created and contains .env patterns
  ```bash
  cat .gitignore | grep "\.env"  # Should show .env patterns
  ```

- [ ] `backend/.gitignore` contains .env exclusions
  ```bash
  cat backend/.gitignore | grep "\.env"  # Should show patterns
  ```

- [ ] `client/.gitignore` contains .env* pattern
  ```bash
  cat client/.gitignore | grep "\.env"  # Should show pattern
  ```

### Hardcoded Secrets
- [ ] No API keys in `application.properties`
  ```bash
  grep -i "sk-or-v1" backend/src/main/resources/application.properties
  # Should return NOTHING (no matches)
  ```

- [ ] No OAuth secrets in `application.properties`
  ```bash
  grep -E "(Ov23|b802be6b)" backend/src/main/resources/application.properties
  # Should return NOTHING (no matches)
  ```

- [ ] No default passwords in `application.properties`
  ```bash
  grep ":postgres" backend/src/main/resources/application.properties
  # Should return NOTHING (no matches)
  ```

- [ ] No encryption keys visible in code
  ```bash
  grep -E "(ez-local-encrypt|a1b2c3d4)" backend/src/main/resources/application.properties
  # Should return NOTHING (no matches)
  ```

---

## ✅ ENVIRONMENT CONFIGURATION

### Backend Configuration
- [ ] `backend/.env.example` exists (template - safe to commit)
- [ ] `backend/.env` exists (local - DO NOT commit)
- [ ] All required variables filled in `backend/.env`:
  - [ ] `DB_URL`
  - [ ] `DB_USERNAME`
  - [ ] `DB_PASSWORD`
  - [ ] `OPENROUTER_API_KEY`
  - [ ] `GITHUB_CLIENT_ID`
  - [ ] `GITHUB_CLIENT_SECRET`
  - [ ] `FRONTEND_URL`
  - [ ] `CORS_ALLOWED_ORIGINS`
  - [ ] `TOKEN_ENCRYPTOR_PASSWORD` (strong value)
  - [ ] `TOKEN_ENCRYPTOR_SALT` (generated value)

### Frontend Configuration
- [ ] `client/.env.local.example` exists (template - safe to commit)
- [ ] `client/.env.local` exists (local - DO NOT commit)
- [ ] `NEXT_PUBLIC_API_URL` set to `http://localhost:8080` (for local dev)
- [ ] Other NEXT_PUBLIC_* variables optional but set

### Docker Configuration
- [ ] `.env.example` exists in root (template - safe to commit)
- [ ] `.env` exists in root (local - DO NOT commit)
- [ ] Contains database configuration:
  - [ ] `DB_USERNAME`
  - [ ] `DB_PASSWORD`
  - [ ] `DB_NAME`

---

## 🧪 FUNCTIONALITY VERIFICATION

### Prerequisites Running
- [ ] Docker daemon is running
- [ ] PostgreSQL container started
  ```bash
  docker-compose up -d
  # Verify: docker ps | grep postgres
  ```

### Backend Verification
- [ ] Backend starts without errors
  ```bash
  cd backend
  ./mvnw spring-boot:run
  # Should show: Started BackendApplication in X seconds
  ```

- [ ] Backend responds to health check
  ```bash
  curl http://localhost:8080/health
  # Should return: {"status":"UP"}
  ```

- [ ] No hardcoded secrets in logs
  - [ ] Check backend logs for API keys (should NOT appear)
  - [ ] Check backend logs for passwords (should NOT appear)

### Frontend Verification
- [ ] Frontend starts without errors (new terminal)
  ```bash
  cd client
  npm install  # If needed
  npm run dev
  # Should show: ▲ Next.js X.XX.X
  ```

- [ ] Frontend loads in browser
  - [ ] Open http://localhost:3000
  - [ ] Page loads without errors
  - [ ] Check browser console (F12) for errors

- [ ] API connectivity works
  - [ ] Frontend can reach backend
  - [ ] No CORS errors in console
  - [ ] Check Network tab in DevTools

---

## 📋 FILES TO COMMIT

### Safe to Commit (.example files)
- [x] `backend/.env.example`
- [x] `client/.env.local.example`
- [x] `.env.example`
- [x] `ENVIRONMENT_SETUP.md`
- [x] `QUICK_SETUP.md`
- [x] `.gitignore` (root level)
- [x] Updated `README.md`
- [x] Updated `backend/README.md`
- [x] Updated `client/CLIENT_README.md`
- [x] Updated `backend/.gitignore`
- [x] Updated `client/.gitignore`
- [x] Updated `docker-compose.yml`
- [x] Updated `backend/src/main/resources/application.properties`

### DO NOT COMMIT (in .gitignore)
- [ ] `backend/.env` (local secrets)
- [ ] `client/.env.local` (local config)
- [ ] `.env` (root - docker compose secrets)
- [ ] `node_modules/`
- [ ] `.next/`
- [ ] `target/`
- [ ] `.idea/`
- [ ] `.vscode/`

---

## 🔐 Git Commands to Run

### Verify Nothing Sensitive is Staged
```bash
# Check status
git status

# Should show NO .env files in "Changes to be committed"
# Should show .env files in "Untracked files" (if visible)
```

### Add Safe Files
```bash
# Add documentation
git add ENVIRONMENT_SETUP.md QUICK_SETUP.md PRE_UPLOAD_CHECKLIST.md

# Add .example files
git add backend/.env.example client/.env.local.example .env.example

# Add updated files
git add README.md backend/README.md client/CLIENT_README.md
git add .gitignore backend/.gitignore client/.gitignore
git add backend/src/main/resources/application.properties
git add docker-compose.yml
```

### Verify Before Commit
```bash
# Review what's staged
git diff --cached

# Make sure NO .env files appear
git status | grep ".env"  # Should return NOTHING
```

### Make Initial Commit
```bash
git commit -m "chore: implement security best practices

- Move sensitive configuration to .env files
- Remove hardcoded secrets from source code
- Add comprehensive environment variable documentation
- Update .gitignore with proper exclusions
- Add ENVIRONMENT_SETUP.md and setup guides"
```

---

## 🚀 Before First GitHub Push

### Final Security Check
```bash
# List ALL files that would be committed
git ls-files

# Search for sensitive patterns
git ls-files | xargs grep -l "sk-or-v1"  # Should find NOTHING
git ls-files | xargs grep -l "OPENROUTER_API_KEY:" # Should find NOTHING
git ls-files | xargs grep -i "client.secret" # Should find NOTHING
```

### Review GitHub Repo Settings
- [ ] Set repository to PRIVATE (if sensitive)
- [ ] Enable branch protection rules
- [ ] Enable "Require status checks to pass"
- [ ] Add branch protection to `main`
- [ ] Review collaborator access

---

## 📝 Sample Pre-Push Verification

Run this script to verify everything:

```bash
#!/bin/bash
echo "🔒 Pre-Push Security Verification"
echo "=================================="

echo "1. Checking .env files are not staged..."
if git diff --cached --name-only | grep -E "\.env"; then
    echo "❌ FAIL: .env files are staged!"
    exit 1
fi
echo "✅ PASS: No .env files staged"

echo "2. Checking for hardcoded secrets in tracked files..."
if git ls-files | xargs grep -l "sk-or-v1"; then
    echo "❌ FAIL: API key found in tracked files!"
    exit 1
fi
echo "✅ PASS: No API keys in tracked files"

echo "3. Checking .gitignore properly configured..."
if ! git check-ignore .env >/dev/null 2>&1; then
    echo "❌ FAIL: .env not in .gitignore!"
    exit 1
fi
echo "✅ PASS: .env is properly ignored"

echo ""
echo "🎉 All security checks passed!"
echo "Ready to push to GitHub!"
```

Save as `pre-push-check.sh`, run with `bash pre-push-check.sh`

---

## ✨ You're Ready!

Once all checkboxes are complete:

```bash
# Push to GitHub
git push -u origin main

# Verify on GitHub
# - Check no .env files are visible
# - Check new documentation appears
# - Check README updated correctly
```

---

## 🆘 Emergency: Secrets Accidentally Committed?

If you discover secrets were committed:

1. **DO NOT PUSH YET**

2. **Remove from Git history:**
   ```bash
   git rm --cached .env backend/.env client/.env.local
   git commit -m "remove: accidentally committed .env files"
   ```

3. **Regenerate ALL credentials immediately**:
   - GitHub OAuth credentials
   - OpenRouter API key
   - Database password
   - Encryption keys

4. **Update all .env files** with new credentials

5. **Then push:**
   ```bash
   git push
   ```

---

**Last Updated**: August 30, 2026

**Status**: Ready after Git is initialized and the unchecked items above are verified.

**Next**: Run this checklist, commit the safe files, then push to GitHub.
