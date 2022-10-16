package com.example.demoauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoauth.models.EmployeeSchedule;
@Repository
public interface EmployeeScheduleRepository  extends JpaRepository<EmployeeSchedule, Long>{
	Optional<EmployeeSchedule> findById(Long id);

	EmployeeSchedule getScheduleById(Long id);
	
}
