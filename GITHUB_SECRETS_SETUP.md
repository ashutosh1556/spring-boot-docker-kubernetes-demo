# GitHub Secrets Setup Guide

This guide explains how to configure GitHub Secrets for the CI/CD pipeline to automatically build and push Docker images.

## 🔐 What Are GitHub Secrets?

GitHub Secrets are encrypted environment variables that you can use in GitHub Actions workflows. They keep sensitive information (like passwords and API tokens) secure and separate from your code.

## 📋 Required Secrets for This Project

You need to add **2 secrets** for the CI/CD pipeline to work:

1. **DOCKER_USERNAME** - Your Docker Hub username
2. **DOCKER_PASSWORD** - Your Docker Hub password or access token (recommended)

## 🚀 Step-by-Step Setup

### Step 1: Get Your Docker Hub Credentials

#### Option A: Use Access Token (Recommended - More Secure)

1. Go to [Docker Hub](https://hub.docker.com/)
2. Log in to your account
3. Click on your username (top right) → **Account Settings**
4. Click **Security** in the left sidebar
5. Click **New Access Token**
6. Enter a description: `GitHub Actions CI/CD`
7. Select permissions: **Read, Write, Delete**
8. Click **Generate**
9. **IMPORTANT:** Copy the token immediately (you won't see it again!)

#### Option B: Use Password (Less Secure)

Simply use your Docker Hub password (not recommended for production).

### Step 2: Add Secrets to GitHub Repository

1. **Go to your repository on GitHub:**
   ```
   https://github.com/YOUR_USERNAME/learningSpringBootApp
   ```

2. **Navigate to Settings:**
   - Click the **Settings** tab (top right of repository page)
   - If you don't see Settings, you may not have admin access

3. **Access Secrets:**
   - In the left sidebar, expand **Secrets and variables**
   - Click **Actions**

4. **Add DOCKER_USERNAME:**
   - Click **New repository secret** (green button)
   - Name: `DOCKER_USERNAME`
   - Secret: Enter your Docker Hub username (e.g., `your-username`)
   - Click **Add secret**

5. **Add DOCKER_PASSWORD:**
   - Click **New repository secret** again
   - Name: `DOCKER_PASSWORD`
   - Secret: Paste your Docker Hub access token or password
   - Click **Add secret**

### Step 3: Verify Secrets Are Added

You should now see both secrets listed:
```
DOCKER_USERNAME
DOCKER_PASSWORD
```

**Note:** You can't view the secret values after adding them (for security), but you can update or delete them.

## ✅ Testing the CI/CD Pipeline

Once secrets are added, the pipeline will automatically run when you:

1. **Push to master/main branch:**
   ```bash
   git add .
   git commit -m "test: trigger CI/CD pipeline"
   git push origin master
   ```

2. **Check the workflow:**
   - Go to the **Actions** tab in your repository
   - You should see the "CI/CD Pipeline" running
   - Click on it to see detailed logs

## 🔍 What Happens in the Pipeline?

### Job 1: Build and Test
- ✅ Checks out code
- ✅ Sets up Java 21
- ✅ Builds with Maven
- ✅ Runs tests
- ✅ Uploads JAR artifact

### Job 2: Docker Build and Push (uses secrets)
- ✅ Downloads JAR artifact
- ✅ Logs into Docker Hub using `DOCKER_USERNAME` and `DOCKER_PASSWORD`
- ✅ Builds Docker image
- ✅ Pushes to Docker Hub with tags:
  - `your-username/spring-demo:master`
  - `your-username/spring-demo:master-<git-sha>`
  - `your-username/spring-demo:latest`

### Job 3: Deploy (Optional)
- ✅ Placeholder for Kubernetes deployment

## 🐛 Troubleshooting

### Error: "Invalid username or password"

**Cause:** Incorrect Docker Hub credentials

**Solution:**
1. Verify your Docker Hub username is correct
2. If using access token, make sure it has Read/Write permissions
3. Try logging in manually: `docker login -u YOUR_USERNAME`
4. Update the secrets in GitHub

### Error: "Secret not found"

**Cause:** Secret name mismatch

**Solution:**
1. Check secret names are exactly: `DOCKER_USERNAME` and `DOCKER_PASSWORD`
2. Names are case-sensitive
3. No extra spaces in names

### Pipeline doesn't run

**Cause:** Workflow file issues or branch mismatch

**Solution:**
1. Check `.github/workflows/ci-cd.yml` exists
2. Verify you're pushing to `master` or `main` branch
3. Check Actions tab is enabled in repository settings

## 🔒 Security Best Practices

### ✅ DO:
- Use Docker Hub access tokens instead of passwords
- Set token expiration dates
- Use minimal required permissions
- Rotate tokens regularly
- Delete unused tokens

### ❌ DON'T:
- Commit secrets to code
- Share secrets in public channels
- Use the same token for multiple projects
- Give tokens more permissions than needed

## 📸 Visual Guide

### Adding a Secret:

```
Repository → Settings → Secrets and variables → Actions → New repository secret

┌─────────────────────────────────────────┐
│ Name *                                  │
│ ┌─────────────────────────────────────┐ │
│ │ DOCKER_USERNAME                     │ │
│ └─────────────────────────────────────┘ │
│                                         │
│ Secret *                                │
│ ┌─────────────────────────────────────┐ │
│ │ your-username                       │ │
│ └─────────────────────────────────────┘ │
│                                         │
│         [Add secret]                    │
└─────────────────────────────────────────┘
```

## 🎯 Expected Results

After setup, every push to master will:

1. ✅ Build your Spring Boot application
2. ✅ Run all tests
3. ✅ Create Docker image
4. ✅ Push to Docker Hub automatically
5. ✅ Tag with branch name, commit SHA, and 'latest'

You can then pull your image:
```bash
docker pull your-username/spring-demo:latest
```

## 📚 Additional Resources

- [GitHub Secrets Documentation](https://docs.github.com/en/actions/security-guides/encrypted-secrets)
- [Docker Hub Access Tokens](https://docs.docker.com/docker-hub/access-tokens/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)

## 🆘 Need Help?

If you encounter issues:
1. Check the Actions tab for detailed error logs
2. Verify secrets are correctly named
3. Test Docker Hub login manually
4. Open an issue in the repository

---

**Security Note:** Never share your Docker Hub password or access tokens publicly!
