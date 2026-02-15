package com.amazon.learningSpringBootApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.learningSpringBootApp.entity.StudentEntity;

/**
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
