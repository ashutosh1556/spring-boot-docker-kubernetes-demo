# Docker Setup for Spring Boot Application with PostgreSQL

This guide explains how to run your Spring Boot application and PostgreSQL database in Docker containers.

## Prerequisites
- Docker installed and running
- Docker Compose installed (optional, for docker-compose approach)

## Architecture
- **postgres-db**: PostgreSQL 15 database container
  - Internal port: 5432
  - Mapped to host: 5432
  - Database name: studentsDB
  - Username: dbuser

- **spring-boot-app**: Spring Boot application container
  - Internal port: 8080
  - Mapped to host: 8080
  - Connects to postgres-db via Docker network

## Option 1: Using Docker Compose (Recommended)

### Start the containers:
```bash
docker-compose up -d
```

### View logs:
```bash
# All containers
docker-compose logs -f

# Specific container
docker-compose logs -f spring-app
docker-compose logs -f postgres
```

### Stop the containers:
```bash
docker-compose down
```

### Stop and remove volumes (clean slate):
```bash
docker-compose down -v
```

## Option 2: Using Manual Docker Commands

### Make the script executable and run:
```bash
chmod +x docker-run-commands.sh
./docker-run-commands.sh
```

### Or run commands manually:

1. **Create a Docker network:**
```bash
docker network create spring-postgres-network
```

2. **Start PostgreSQL container:**
```bash
docker run -d \
  --name postgres-db \
  --network spring-postgres-network \
  -e POSTGRES_DB=studentsDB \
  -e POSTGRES_USER=dbuser \
  -e POSTGRES_PASSWORD=postgres123 \
  -p 5432:5432 \
  postgres:15
```

3. **Start Spring Boot container:**
```bash
docker run -d \
  --name spring-boot-app \
  --network spring-postgres-network \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/studentsDB \
  -e SPRING_DATASOURCE_USERNAME=dbuser \
  -e SPRING_DATASOURCE_PASSWORD=postgres123 \
  -p 8080:8080 \
  your-username/spring-demo:v3
```

## Access Points

- **Spring Boot Application:** http://localhost:8080
- **PostgreSQL Database:** localhost:5432
  - Connect using any PostgreSQL client with credentials above

## Useful Commands

### Check running containers:
```bash
docker ps
```

### View Spring Boot logs:
```bash
docker logs -f spring-boot-app
```

### View PostgreSQL logs:
```bash
docker logs -f postgres-db
```

### Access PostgreSQL shell:
```bash
docker exec -it postgres-db psql -U dbuser -d studentsDB
```

### Access Spring Boot container bash:
```bash
docker exec -it spring-boot-app /bin/bash
```

### Stop containers:
```bash
docker stop spring-boot-app postgres-db
```

### Remove containers:
```bash
docker rm spring-boot-app postgres-db
```

### Remove network:
```bash
docker network rm spring-postgres-network
```

## Troubleshooting

### Check if containers are running:
```bash
docker ps -a
```

### Check network connectivity:
```bash
docker network inspect spring-postgres-network
```

### Test database connection from Spring Boot container:
```bash
docker exec -it spring-boot-app curl postgres-db:5432
```

### Recreate everything:
```bash
docker-compose down -v
docker-compose up -d --build
```

## Notes

- The PostgreSQL password is set to `postgres123` - change this for production
- Data persists in Docker volume `postgres-data`
- Containers communicate using the custom bridge network `spring-postgres-network`
- Health check ensures PostgreSQL is ready before starting Spring Boot app
