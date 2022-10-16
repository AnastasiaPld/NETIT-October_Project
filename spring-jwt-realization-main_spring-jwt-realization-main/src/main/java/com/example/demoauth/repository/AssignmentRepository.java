package com.example.demoauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoauth.models.Assignment;
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	Optional<Assignment> findById(Long id);
}
