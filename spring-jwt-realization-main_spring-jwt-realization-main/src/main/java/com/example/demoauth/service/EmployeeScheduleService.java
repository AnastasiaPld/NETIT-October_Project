package com.example.demoauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoauth.models.EmployeeSchedule;
import com.example.demoauth.repository.EmployeeScheduleRepository;

@Service
public class EmployeeScheduleService {

	@Autowired
	EmployeeScheduleRepository scheduleRepository;

	public List<EmployeeSchedule> getAllSchedule() {
		List<EmployeeSchedule> employees = new ArrayList<EmployeeSchedule>();
		scheduleRepository.findAll().forEach(emp -> employees.add(emp));
		return employees;
	}

	public Optional<EmployeeSchedule> getScheduleById(Long id) {

		return scheduleRepository.findById(id);
	}

	public EmployeeSchedule getScheduleBySpecifyId(Long id) {

		return scheduleRepository.getScheduleById(id);
	}

	public void saveOrUpdate(EmployeeSchedule employeeSchedule) {
		scheduleRepository.save(employeeSchedule);
	}

	public EmployeeSchedule save(EmployeeSchedule employeeSchedule) {
		return scheduleRepository.save(employeeSchedule);
	}

	public void delete(Long id) {
		scheduleRepository.deleteById(id);
	}

}
