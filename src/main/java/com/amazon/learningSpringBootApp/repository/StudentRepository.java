package com.amazon.learningSpringBootApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.learningSpringBootApp.entity.StudentEntity;

/**
 * Repository interface for Student entity.
 * 
 * Extends JpaRepository to provide CRUD operations and pagination support.
 * Spring Data JPA automatically implements this interface at runtime.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
