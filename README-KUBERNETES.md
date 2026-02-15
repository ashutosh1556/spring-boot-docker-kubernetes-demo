# Kubernetes Deployment Guide

## Prerequisites
1. Start Colima with Kubernetes:
```bash
colima start --kubernetes
```

2. Verify kubectl is working:
```bash
kubectl cluster-info
```

## Build & Deploy

### Step 1: Build the JAR
```bash
./mvnw clean package -DskipTests
```

### Step 2: Build Docker Image
```bash
docker build -t spring-demo:local .
```

### Step 3: Deploy to Kubernetes
```bash
kubectl apply -f k8s/
# If namespace error, run again: kubectl apply -f k8s/
```

### Step 4: Check Status
```bash
# Watch pods starting up
kubectl get pods -n spring-demo -w

# Check all resources
kubectl get all -n spring-demo
```

### Step 5: Access Application
Once pods are running (STATUS: Running, READY: 1/1 or 2/2):
- **Application**: http://localhost:30080
- **Health Check**: http://localhost:30080/actuator/health

## Useful Commands

### View Logs
```bash
# Spring Boot logs
kubectl logs -f deployment/spring-app -n spring-demo

# PostgreSQL logs
kubectl logs -f deployment/postgres -n spring-demo
```

### Restart Deployment
```bash
kubectl rollout restart deployment/spring-app -n spring-demo
```

### Scale Application
```bash
kubectl scale deployment/spring-app --replicas=3 -n spring-demo
```

### Delete Everything
```bash
kubectl delete -f k8s/
```

## Troubleshooting

### Pods not starting?
```bash
kubectl describe pod <pod-name> -n spring-demo
```

### Check events:
```bash
kubectl get events -n spring-demo --sort-by='.lastTimestamp'
```

### Access pod shell:
```bash
kubectl exec -it <pod-name> -n spring-demo -- /bin/sh
```

## What's Running?
- **Namespace**: spring-demo (isolated environment)
- **PostgreSQL**: 1 pod on port 5432
- **Spring Boot**: 2 pods on port 8080
- **Service**: NodePort 30080 (external access)
