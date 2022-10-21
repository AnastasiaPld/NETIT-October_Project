package com.example.demoauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoauth.models.EmployeePersonalData;
import com.example.demoauth.repository.EmployeeDataRepository;

@Service
public class EmployeeDataService {

	@Autowired
	EmployeeDataRepository employeeDataRepository;

	public List<EmployeePersonalData> getAllData() {
		List<EmployeePersonalData> employees = new ArrayList<EmployeePersonalData>();
		employeeDataRepository.findAll().forEach(emp -> employees.add(emp));
		return employees;
	}

	public Optional<EmployeePersonalData> getDataById(Long id) {

		return employeeDataRepository.findById(id);
	}

	public EmployeePersonalData saveOrUpdate(EmployeePersonalData employeeData) {
		return employeeDataRepository.save(employeeData);
	}

	public EmployeePersonalData save(EmployeePersonalData employeeData) {
		return employeeDataRepository.save(employeeData);
	}

	public void delete(EmployeePersonalData employeeData) {
		employeeDataRepository.delete(employeeData);
	}

	public void delete(Long id) {
		employeeDataRepository.deleteById(id);
	}
	public EmployeePersonalData update(EmployeePersonalData employeeData, Long id) {
		return employeeDataRepository.save(employeeData);
	}

	public EmployeePersonalData findByName(String name) {
		return employeeDataRepository.findByname(name);
	}
}
