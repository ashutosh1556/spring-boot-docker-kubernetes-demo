package com.amazon.learningSpringBootApp.config;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.amazon.learningSpringBootApp.entity.*;
import com.amazon.learningSpringBootApp.repository.StudentRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            studentRepository.save(createStudent("John Doe", "john.doe@example.com", 22, "123 Main St", "Seattle", "WA", "98101", "USA", 47.6062, -122.3321, "+1-206-555-0100", "+1-206-555-0101", "Jane Doe", "+1-206-555-0102", "Bachelor of Science", "Computer Science", LocalDate.of(2021, 9, 1), "Active", 3.8));
            studentRepository.save(createStudent("Alice Smith", "alice.smith@example.com", 24, "456 Oak Ave", "Portland", "OR", "97201", "USA", 45.5152, -122.6784, "+1-503-555-0200", null, "Bob Smith", "+1-503-555-0201", "Master of Science", "Data Science", LocalDate.of(2022, 1, 15), "Active", 3.9));
            studentRepository.save(createStudent("Bob Johnson", "bob.johnson@example.com", 21, "789 Pine Rd", "San Francisco", "CA", "94102", "USA", 37.7749, -122.4194, "+1-415-555-0300", "+1-415-555-0301", "Mary Johnson", "+1-415-555-0302", "Bachelor of Arts", "Business Administration", LocalDate.of(2020, 9, 1), "Active", 3.5));
            studentRepository.save(createStudent("Emma Wilson", "emma.wilson@example.com", 23, "321 Elm St", "Austin", "TX", "78701", "USA", 30.2672, -97.7431, "+1-512-555-0400", "+1-512-555-0401", "Tom Wilson", "+1-512-555-0402", "Bachelor of Science", "Software Engineering", LocalDate.of(2021, 1, 10), "Active", 3.7));
            studentRepository.save(createStudent("Michael Brown", "michael.brown@example.com", 25, "654 Maple Dr", "Boston", "MA", "02101", "USA", 42.3601, -71.0589, "+1-617-555-0500", null, "Sarah Brown", "+1-617-555-0501", "Master of Business Administration", "Finance", LocalDate.of(2020, 8, 20), "Active", 3.6));
            studentRepository.save(createStudent("Sophia Davis", "sophia.davis@example.com", 22, "987 Cedar Ln", "Denver", "CO", "80201", "USA", 39.7392, -104.9903, "+1-303-555-0600", "+1-303-555-0601", "James Davis", "+1-303-555-0602", "Bachelor of Arts", "Psychology", LocalDate.of(2021, 9, 5), "Active", 3.4));
            studentRepository.save(createStudent("William Martinez", "william.martinez@example.com", 26, "147 Birch Ave", "Miami", "FL", "33101", "USA", 25.7617, -80.1918, "+1-305-555-0700", "+1-305-555-0701", "Linda Martinez", "+1-305-555-0702", "Master of Science", "Artificial Intelligence", LocalDate.of(2019, 1, 15), "Active", 3.9));
            studentRepository.save(createStudent("Olivia Garcia", "olivia.garcia@example.com", 21, "258 Spruce St", "Phoenix", "AZ", "85001", "USA", 33.4484, -112.0740, "+1-602-555-0800", null, "Carlos Garcia", "+1-602-555-0801", "Bachelor of Science", "Biology", LocalDate.of(2022, 8, 25), "Active", 3.5));
            studentRepository.save(createStudent("James Rodriguez", "james.rodriguez@example.com", 24, "369 Willow Rd", "Chicago", "IL", "60601", "USA", 41.8781, -87.6298, "+1-312-555-0900", "+1-312-555-0901", "Maria Rodriguez", "+1-312-555-0902", "Bachelor of Engineering", "Mechanical Engineering", LocalDate.of(2020, 9, 1), "Active", 3.6));
            studentRepository.save(createStudent("Isabella Lee", "isabella.lee@example.com", 23, "741 Ash Blvd", "New York", "NY", "10001", "USA", 40.7128, -74.0060, "+1-212-555-1000", "+1-212-555-1001", "David Lee", "+1-212-555-1002", "Bachelor of Arts", "English Literature", LocalDate.of(2021, 1, 20), "Active", 3.7));
            studentRepository.save(createStudent("Ethan Taylor", "ethan.taylor@example.com", 25, "852 Poplar Way", "Los Angeles", "CA", "90001", "USA", 34.0522, -118.2437, "+1-213-555-1100", null, "Nancy Taylor", "+1-213-555-1101", "Master of Science", "Cybersecurity", LocalDate.of(2020, 1, 10), "Active", 3.8));
            studentRepository.save(createStudent("Mia Anderson", "mia.anderson@example.com", 22, "963 Fir Ct", "Philadelphia", "PA", "19101", "USA", 39.9526, -75.1652, "+1-215-555-1200", "+1-215-555-1201", "Robert Anderson", "+1-215-555-1202", "Bachelor of Science", "Chemistry", LocalDate.of(2021, 9, 1), "Active", 3.5));
        }
    }
    
    private StudentEntity createStudent(String name, String email, Integer age, String street, String city, String state, String zip, String country, Double lat, Double lon, String phone1, String phone2, String emergency, String emergencyPhone, String program, String major, LocalDate enrollDate, String status, Double gpa) {
        StudentEntity student = new StudentEntity();
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);
        
        AddressEntity address = new AddressEntity();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zip);
        address.setCountry(country);
        address.setStudent(student);
        
        CoordinatesEntity coordinates = new CoordinatesEntity();
        coordinates.setLatitude(lat);
        coordinates.setLongitude(lon);
        coordinates.setAddress(address);
        address.setCoordinates(coordinates);
        
        student.setAddress(address);
        
        ContactInfoEntity contactInfo = new ContactInfoEntity();
        contactInfo.setPrimaryPhone(phone1);
        contactInfo.setSecondaryPhone(phone2);
        contactInfo.setEmergencyContact(emergency);
        contactInfo.setEmergencyPhone(emergencyPhone);
        contactInfo.setStudent(student);
        student.setContactInfo(contactInfo);
        
        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setProgram(program);
        enrollment.setMajor(major);
        enrollment.setEnrollmentDate(enrollDate);
        enrollment.setStatus(status);
        enrollment.setGpa(gpa);
        enrollment.setStudent(student);
        student.getEnrollments().add(enrollment);
        
        return student;
    }
}
