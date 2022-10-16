package com.example.demoauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoauth.models.EmployeePersonalData;
@Repository
public interface EmployeeDataRepository  extends JpaRepository<EmployeePersonalData, Long> {

	EmployeePersonalData findByname(String name);
	
	Optional<EmployeePersonalData> findById(Long id);
	
}
