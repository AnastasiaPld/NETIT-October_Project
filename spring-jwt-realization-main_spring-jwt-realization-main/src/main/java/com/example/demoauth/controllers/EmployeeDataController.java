package com.example.demoauth.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoauth.models.EmployeePersonalData;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.repository.EmployeeDataRepository;
import com.example.demoauth.repository.EmployeeScheduleRepository;
import com.example.demoauth.service.EmployeeDataService;
import com.example.demoauth.service.EmployeeScheduleService;



@RestController
@RequestMapping("/api/employee/data")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeDataController {

	@Autowired
	EmployeeDataService employeeDataService;

	@Autowired
	EmployeeScheduleService employeeScheduleService;

	@Autowired
	EmployeeDataRepository employeeDataRepository;

	@Autowired
	EmployeeScheduleRepository employeeScheduleRepository;

	@GetMapping("/all")
	public List<EmployeePersonalData> getAllemployeesData(Model model) {
		List<EmployeePersonalData> all_employees_data = employeeDataService.getAllData();
		model.addAttribute("listUsers", all_employees_data);
		return all_employees_data;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		employeeDataService.delete(id);
		return ResponseEntity.ok(new MessageResponse("Employee data is deleted"));

	}

	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<EmployeePersonalData> getUserById(@PathVariable(name = "id") Long id) {
		Optional<EmployeePersonalData> employee = employeeDataService.getDataById(id);
		return employee;
	}

	@PostMapping(value = "/edit")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public EmployeePersonalData addData(@RequestBody EmployeePersonalData employeeData) {
		employeeDataService.saveOrUpdate(employeeData);
		return employeeData;
	}

	@PostMapping(value = "/edit/data")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public EmployeePersonalData addDataAdditionally(@RequestBody EmployeePersonalData employeeData) {
		employeeDataService.saveOrUpdate(employeeData);

		return employeeData;
	}

	@PatchMapping("/employees/{id}")
	public ResponseEntity<EmployeePersonalData> updateEmployee(@PathVariable(value = "id") Long employeeId,
			 @RequestBody EmployeePersonalData request) throws ResourceNotFoundException {
		EmployeePersonalData employee = employeeDataService.getDataById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		if (request.getName() != null) {
			employee.setName(request.getName());
		}
		if (request.getSurname() != null) {
			employee.setSurname(request.getSurname());
		}
		if (request.getStartWorkDay() != null && !request.getStartWorkDay().isEmpty()) {
			employee.setStartWorkDay(request.getStartWorkDay());
		}
		if (request.getExperience() != 0) {
			employee.setExperience(request.getExperience());
		}
		if (request.getSalary() != null) {
			employee.setSalary(request.getSalary());
		}
		if (request.getEmail() != null) {
			employee.setEmail(request.getEmail());
		}
		

		final EmployeePersonalData updatedEmployee = employeeDataService.update(employee, employeeId);

		return ResponseEntity.ok(updatedEmployee);

	}
}
