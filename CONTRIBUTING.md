# Contributing to Spring Boot Student Management System

Thank you for your interest in contributing! This document provides guidelines and instructions for contributing to this project.

## 🎯 Ways to Contribute

- 🐛 Report bugs
- 💡 Suggest new features
- 📝 Improve documentation
- 🔧 Submit bug fixes
- ✨ Add new features
- 🧪 Write tests

## 🚀 Getting Started

### 1. Fork the Repository

Click the "Fork" button at the top right of the repository page.

### 2. Clone Your Fork

```bash
git clone https://github.com/YOUR_USERNAME/learningSpringBootApp.git
cd learningSpringBootApp
```

### 3. Add Upstream Remote

```bash
git remote add upstream https://github.com/ORIGINAL_OWNER/learningSpringBootApp.git
```

### 4. Create a Branch

```bash
git checkout -b feature/your-feature-name
```

## 💻 Development Setup

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL 15 (or use Docker)

### Local Development

```bash
# Start PostgreSQL
docker-compose up -d postgres

# Run the application
./mvnw spring-boot:run

# Run tests
./mvnw test
```

## 📋 Coding Standards

### Java Code Style

- Follow standard Java naming conventions
- Use meaningful variable and method names
- Keep methods small and focused
- Add JavaDoc comments for public APIs
- Use Lombok annotations to reduce boilerplate

### Example:

```java
/**
 * Service for managing student operations.
 * 
 * @author Your Name
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    
    /**
     * Retrieves a student by ID.
     * 
     * @param id Student ID
     * @return Student details
     * @throws IllegalArgumentException if student not found
     */
    @Override
    public StudentDTO getStudentById(Long id) {
        // Implementation
    }
}
```

### Commit Messages

Follow the conventional commits specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Examples:**

```bash
feat(api): add endpoint for student search by email

fix(database): resolve connection pool timeout issue

docs(readme): update Docker setup instructions

test(service): add unit tests for StudentService
```

## 🧪 Testing

### Write Tests

- Write unit tests for new features
- Ensure existing tests pass
- Aim for good test coverage

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=StudentServiceTest

# Run with coverage
./mvnw test jacoco:report
```

### Test Structure

```java
@SpringBootTest
class StudentServiceTest {
    
    @Autowired
    private StudentService studentService;
    
    @Test
    void shouldCreateStudent() {
        // Given
        AddNewStudentRequestDTO request = new AddNewStudentRequestDTO();
        // ... setup
        
        // When
        StudentDTO result = studentService.createStudent(request);
        
        // Then
        assertNotNull(result.getId());
        assertEquals("John Doe", result.getName());
    }
}
```

## 📝 Pull Request Process

### 1. Update Your Branch

```bash
git fetch upstream
git rebase upstream/master
```

### 2. Run Tests

```bash
./mvnw clean test
```

### 3. Push Changes

```bash
git push origin feature/your-feature-name
```

### 4. Create Pull Request

- Go to your fork on GitHub
- Click "New Pull Request"
- Select your feature branch
- Fill in the PR template

### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation update
- [ ] Refactoring

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] Code follows project style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings generated
- [ ] Tests pass locally
```

## 🐛 Reporting Bugs

### Before Submitting

- Check existing issues
- Verify it's reproducible
- Collect relevant information

### Bug Report Template

```markdown
**Describe the bug**
Clear description of the bug

**To Reproduce**
Steps to reproduce:
1. Go to '...'
2. Click on '...'
3. See error

**Expected behavior**
What you expected to happen

**Screenshots**
If applicable

**Environment:**
- OS: [e.g., macOS, Windows, Linux]
- Java Version: [e.g., 21]
- Spring Boot Version: [e.g., 3.5.7]
- Docker Version: [e.g., 24.0.0]

**Additional context**
Any other relevant information
```

## 💡 Suggesting Features

### Feature Request Template

```markdown
**Is your feature request related to a problem?**
Clear description of the problem

**Describe the solution you'd like**
Clear description of desired solution

**Describe alternatives you've considered**
Alternative solutions or features

**Additional context**
Any other relevant information
```

## 📚 Documentation

### Update Documentation

When adding features, update:

- README.md - Main documentation
- API documentation - Swagger/OpenAPI
- Code comments - JavaDoc
- README-DOCKER.md - Docker-specific docs
- README-KUBERNETES.md - Kubernetes-specific docs

## 🔍 Code Review Process

### What We Look For

- ✅ Code quality and readability
- ✅ Test coverage
- ✅ Documentation
- ✅ Performance considerations
- ✅ Security best practices
- ✅ Backward compatibility

### Review Timeline

- Initial review: Within 2-3 days
- Follow-up reviews: Within 1-2 days
- Merge: After approval and CI passes

## 🎓 Learning Resources

### Spring Boot
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

### Docker & Kubernetes
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)

### Best Practices
- [RESTful API Design](https://restfulapi.net/)
- [Clean Code Principles](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882)

## 📞 Getting Help

- 💬 Open a discussion on GitHub
- 🐛 Create an issue for bugs
- 📧 Contact maintainers

## 🙏 Thank You!

Your contributions make this project better for everyone. We appreciate your time and effort!

---

**Happy Contributing! 🎉**
