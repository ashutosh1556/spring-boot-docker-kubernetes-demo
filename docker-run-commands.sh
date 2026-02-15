#!/bin/bash

# Alternative approach: Manual Docker commands (without docker-compose)

echo "Creating Docker network..."
docker network create spring-postgres-network

echo "Starting PostgreSQL container..."
docker run -d \
  --name postgres-db \
  --network spring-postgres-network \
  -e POSTGRES_DB=studentsDB \
  -e POSTGRES_USER=ashutver \
  -e POSTGRES_PASSWORD=postgres123 \
  -p 5432:5432 \
  -v postgres-data:/var/lib/postgresql/data \
  postgres:15

echo "Waiting for PostgreSQL to be ready..."
sleep 10

echo "Starting Spring Boot application container..."
docker run -d \
  --name spring-boot-app \
  --network spring-postgres-network \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/studentsDB \
  -e SPRING_DATASOURCE_USERNAME=ashutver \
  -e SPRING_DATASOURCE_PASSWORD=postgres123 \
  -p 8080:8080 \
  ashutver/spring-demo:v3

echo "Containers started successfully!"
echo "PostgreSQL is accessible at localhost:5432"
echo "Spring Boot app is accessible at localhost:8080"
echo ""
echo "To check logs:"
echo "  docker logs -f spring-boot-app"
echo "  docker logs -f postgres-db"
echo ""
echo "To stop containers:"
echo "  docker stop spring-boot-app postgres-db"
echo ""
echo "To remove containers:"
echo "  docker rm spring-boot-app postgres-db"
