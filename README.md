# Spring Boot Student Management System

A comprehensive learning project demonstrating modern Spring Boot development with Docker, Kubernetes, and CI/CD using GitHub Actions.

## 📚 What You'll Learn

- **Spring Boot 3.5.7** - RESTful API development
- **Spring Data JPA** - Database operations with Hibernate
- **PostgreSQL** - Relational database integration
- **Docker** - Containerization and multi-container orchestration
- **Kubernetes** - Container orchestration and deployment
- **GitHub Actions** - CI/CD pipeline automation
- **API Documentation** - OpenAPI/Swagger integration
- **Health Checks** - Spring Boot Actuator for monitoring

## 🏗️ Project Architecture

```
Student Management System
├── REST API Layer (Controllers)
├── Service Layer (Business Logic)
├── Repository Layer (Data Access)
├── Entity Layer (JPA Entities)
└── DTO Layer (Data Transfer Objects)
```

### Entity Relationships

```
StudentEntity (1) ──── (1) AddressEntity
                │              │
                │              └── (1) CoordinatesEntity
                │
                ├──── (1) ContactInfoEntity
                │
                └──── (*) EnrollmentEntity
```

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Docker** and **Docker Compose**
- **Kubernetes** (Minikube, Docker Desktop, or Colima)
- **Git**

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/learningSpringBootApp.git
cd learningSpringBootApp
```

### 2. Run Locally (Without Docker)

```bash
# Start PostgreSQL (using Docker)
docker run -d \
  --name postgres-local \
  -e POSTGRES_DB=studentsDB \
  -e POSTGRES_USER=ashutver \
  -e POSTGRES_PASSWORD=postgres123 \
  -p 5432:5432 \
  postgres:15

# Build and run the application
./mvnw clean package
./mvnw spring-boot:run
```

Access the application at: http://localhost:8080

### 3. Run with Docker Compose (Recommended)

```bash
# Build the JAR file
./mvnw clean package -DskipTests

# Build Docker image
docker build -t ashutver/spring-demo:v3 .

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

### 4. Run on Kubernetes

```bash
# Start Kubernetes (if using Colima)
colima start --kubernetes

# Build the application
./mvnw clean package -DskipTests

# Build Docker image
docker build -t spring-demo:local .

# Deploy to Kubernetes
kubectl apply -f k8s/

# Check status
kubectl get all -n spring-demo

# Access application
# Application: http://localhost:30080
# Health: http://localhost:30080/actuator/health
```

For detailed instructions, see:
- [Docker Setup Guide](README-DOCKER.md)
- [Kubernetes Deployment Guide](README-KUBERNETES.md)

## 📡 API Endpoints

### Student Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/students` | Get all students (paginated) |
| GET | `/students/{id}` | Get student by ID |
| POST | `/students` | Create new student |
| PUT | `/students/{id}` | Update entire student |
| PATCH | `/students/{id}` | Partial update student |
| DELETE | `/students/{id}` | Delete student |

### Query Parameters

- `page` - Page number (default: 0)
- `size` - Page size (default: 5)

### Example Requests

#### Get All Students (Paginated)
```bash
curl http://localhost:8080/students?page=0&size=5
```

#### Get Student by ID
```bash
curl http://localhost:8080/students/1
```

#### Create New Student
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "age": 23,
    "address": {
      "street": "123 Main St",
      "city": "Seattle",
      "state": "WA",
      "zipCode": "98101",
      "country": "USA",
      "coordinates": {
        "latitude": 47.6062,
        "longitude": -122.3321
      }
    },
    "contactInfo": {
      "primaryPhone": "+1-206-555-0100",
      "emergencyContact": "John Doe",
      "emergencyPhone": "+1-206-555-0101"
    },
    "enrollments": [{
      "program": "Bachelor of Science",
      "major": "Computer Science",
      "enrollmentDate": "2021-09-01",
      "status": "Active",
      "gpa": 3.8
    }]
  }'
```

#### Update Student (PUT)
```bash
curl -X PUT http://localhost:8080/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Updated",
    "email": "john.updated@example.com",
    "age": 23
  }'
```

#### Partial Update (PATCH)
```bash
curl -X PATCH http://localhost:8080/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newemail@example.com"
  }'
```

#### Delete Student
```bash
curl -X DELETE http://localhost:8080/students/1
```

## 📖 API Documentation

Interactive API documentation is available via Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🏥 Health Checks

Spring Boot Actuator provides health endpoints:

- **General Health**: http://localhost:8080/actuator/health
- **Liveness Probe**: http://localhost:8080/actuator/health/liveness
- **Readiness Probe**: http://localhost:8080/actuator/health/readiness

## 🗄️ Database Schema

The application automatically creates the following tables:

- `student_entity` - Main student information
- `address_entity` - Student addresses
- `coordinates_entity` - Geographic coordinates
- `contact_info_entity` - Contact information
- `enrollment_entity` - Course enrollments

Sample data is automatically loaded on startup (12 students).

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.5.7** - Application framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM framework
- **PostgreSQL 15** - Database
- **Lombok** - Boilerplate code reduction
- **ModelMapper** - Object mapping
- **Validation API** - Input validation

### API & Documentation
- **SpringDoc OpenAPI** - API documentation
- **Spring Boot Actuator** - Health monitoring

### Build & Deployment
- **Maven** - Build tool
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Kubernetes** - Container orchestration
- **GitHub Actions** - CI/CD (coming soon)

## 📁 Project Structure

```
learningSpringBootApp/
├── src/
│   ├── main/
│   │   ├── java/com/amazon/learningSpringBootApp/
│   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── DataLoader.java      # Sample data loader
│   │   │   │   └── ModelConfig.java     # Bean configurations
│   │   │   ├── controller/      # REST controllers
│   │   │   │   └── StudentController.java
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── StudentDTO.java
│   │   │   │   ├── AddressDTO.java
│   │   │   │   ├── ContactInfoDTO.java
│   │   │   │   ├── EnrollmentDTO.java
│   │   │   │   └── ...
│   │   │   ├── entity/          # JPA Entities
│   │   │   │   ├── StudentEntity.java
│   │   │   │   ├── AddressEntity.java
│   │   │   │   ├── ContactInfoEntity.java
│   │   │   │   └── ...
│   │   │   ├── repository/      # Data repositories
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/         # Business logic
│   │   │   │   ├── StudentService.java
│   │   │   │   └── impl/
│   │   │   │       └── StudentServiceImpl.java
│   │   │   └── LearningSpringBootAppApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Test classes
├── k8s/                         # Kubernetes manifests
│   ├── namespace.yaml
│   ├── app-deployment.yaml
│   ├── app-service.yaml
│   ├── app-configmap.yaml
│   ├── postgres-deployment.yaml
│   ├── postgres-service.yaml
│   └── postgres-secret.yaml
├── Dockerfile                   # Docker image definition
├── docker-compose.yml           # Multi-container setup
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```

## 🔧 Configuration

### Application Properties

Key configurations in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/studentsDB
spring.datasource.username=ashutver
spring.datasource.password=

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Pagination
spring.data.web.pageable.one-indexed-parameters=true

# Health Probes
management.endpoint.health.probes.enabled=true
```

### Environment Variables

Override properties using environment variables:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/studentsDB
SPRING_DATASOURCE_USERNAME=ashutver
SPRING_DATASOURCE_PASSWORD=postgres123
```

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report

# Skip tests during build
./mvnw clean package -DskipTests
```

## 🐳 Docker Commands

### Build Image
```bash
docker build -t ashutver/spring-demo:v3 .
```

### Run Container
```bash
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/studentsDB \
  -e SPRING_DATASOURCE_USERNAME=ashutver \
  -e SPRING_DATASOURCE_PASSWORD=postgres123 \
  ashutver/spring-demo:v3
```

### Docker Compose
```bash
# Start services
docker-compose up -d

# View logs
docker-compose logs -f spring-app

# Stop services
docker-compose down

# Remove volumes
docker-compose down -v
```

## ☸️ Kubernetes Commands

### Deploy Application
```bash
kubectl apply -f k8s/
```

### Check Status
```bash
# All resources
kubectl get all -n spring-demo

# Pods
kubectl get pods -n spring-demo

# Services
kubectl get svc -n spring-demo
```

### View Logs
```bash
# Spring Boot logs
kubectl logs -f deployment/spring-app -n spring-demo

# PostgreSQL logs
kubectl logs -f deployment/postgres -n spring-demo
```

### Scale Application
```bash
kubectl scale deployment/spring-app --replicas=3 -n spring-demo
```

### Delete Resources
```bash
kubectl delete -f k8s/
```

## 🔄 CI/CD with GitHub Actions

### Planned Pipeline

1. **Build** - Compile and package application
2. **Test** - Run unit and integration tests
3. **Docker Build** - Create Docker image
4. **Push to Registry** - Push to Docker Hub
5. **Deploy** - Deploy to Kubernetes cluster

### Setup Instructions

1. Create GitHub repository
2. Add secrets to repository:
   - `DOCKER_USERNAME`
   - `DOCKER_PASSWORD`
3. Push code to trigger pipeline

## 📝 Best Practices Demonstrated

### Code Organization
- ✅ Layered architecture (Controller → Service → Repository)
- ✅ DTO pattern for API contracts
- ✅ Entity-DTO separation
- ✅ Dependency injection with constructor injection

### Database
- ✅ JPA entity relationships (OneToOne, OneToMany)
- ✅ Cascade operations and orphan removal
- ✅ Pagination support
- ✅ Database initialization with sample data

### API Design
- ✅ RESTful endpoints
- ✅ Proper HTTP methods and status codes
- ✅ Request validation
- ✅ Pagination for list endpoints

### DevOps
- ✅ Containerization with Docker
- ✅ Multi-container orchestration
- ✅ Kubernetes deployment with health probes
- ✅ Configuration externalization
- ✅ Secrets management

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available for learning purposes.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Docker and Kubernetes communities
- All contributors and learners

## 📧 Contact

For questions or feedback, please open an issue in the GitHub repository.

---

**Happy Learning! 🚀**
