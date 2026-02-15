# Kubernetes Secrets Configuration

## Important: Update Secrets Before Deployment

The `postgres-secret.yaml` contains base64-encoded default values. **You must update these before deploying to production.**

### Current Default Values (Base64 Encoded):
- `POSTGRES_DB`: studentsDB
- `POSTGRES_USER`: dbuser
- `POSTGRES_PASSWORD`: changeme

### How to Update Secrets:

1. **Encode your values:**
   ```bash
   echo -n "your-database-name" | base64
   echo -n "your-username" | base64
   echo -n "your-secure-password" | base64
   ```

2. **Update `postgres-secret.yaml`:**
   ```yaml
   data:
     POSTGRES_DB: <base64-encoded-db-name>
     POSTGRES_USER: <base64-encoded-username>
     POSTGRES_PASSWORD: <base64-encoded-password>
   ```

3. **Apply the secret:**
   ```bash
   kubectl apply -f k8s/postgres-secret.yaml
   ```

### Alternative: Create Secret from Command Line

```bash
kubectl create secret generic postgres-secret \
  --from-literal=POSTGRES_DB=studentsDB \
  --from-literal=POSTGRES_USER=your-username \
  --from-literal=POSTGRES_PASSWORD=your-secure-password \
  --namespace=spring-demo
```

## Security Best Practices

- Never commit real credentials to version control
- Use strong, unique passwords for production
- Consider using external secret management (AWS Secrets Manager, HashiCorp Vault, etc.)
- Rotate credentials regularly
- Use RBAC to restrict access to secrets
