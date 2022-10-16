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

import com.example.demoauth.models.EmployeeSchedule;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.repository.EmployeeDataRepository;
import com.example.demoauth.repository.EmployeeScheduleRepository;
import com.example.demoauth.service.EmployeeScheduleService;

@RestController
@RequestMapping("/api/employee/schedule")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeScheduleController {

	@Autowired EmployeeScheduleService employeeService;
	@Autowired
	EmployeeDataRepository employeeDataRepository;
	@Autowired
	EmployeeScheduleRepository employeeScheduleRepository;

	
	@PostMapping(value = "/edit")
	public EmployeeSchedule addData(@RequestBody EmployeeSchedule employeeSchedule) {
		employeeDataRepository.findAll();
		employeeService.saveOrUpdate(employeeSchedule);
		return employeeSchedule;
	}
	
	@PatchMapping(value = "/edit/{id}")
	public ResponseEntity<EmployeeSchedule>  updateSchedule(@RequestBody EmployeeSchedule request,@PathVariable(value = "id") Long id) {
		EmployeeSchedule employee = employeeService.getScheduleById(id).get();

		if (request.getDayOff() != 0) {
			employee.setDayOff(request.getDayOff());
		}
		if (request.getMotherhood() != null) {
			employee.setMotherhood(request.getMotherhood());
		}
		if (request.getSickLeave() != 0) {
			employee.setSickLeave(request.getSickLeave());
		}
		if (request.getVacationForTheYear() != 0) {
			employee.setVacationForTheYear(request.getVacationForTheYear());
		}
		if (request.getVacationTaken() != 0) {
			employee.setVacationTaken(request.getVacationTaken());
		}
		if (request.getWorkDays() != 0) {
			employee.setWorkDays(request.getWorkDays());
		}
		

		final EmployeeSchedule updatedSchedule = employeeService.save(employee);

		return ResponseEntity.ok(updatedSchedule);
	}
	
	@GetMapping("/all")
	public List<EmployeeSchedule> getAllemployeesData(Model model) {
		List<EmployeeSchedule> all_employees_schedule = employeeService.getAllSchedule();
		model.addAttribute("listUsers", all_employees_schedule);
		return all_employees_schedule;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return ResponseEntity.ok(new MessageResponse("Employee data is deleted"));

	}

	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<EmployeeSchedule> getUserById(@PathVariable(name = "id") Long id) {
		Optional<EmployeeSchedule> employee = employeeService.getScheduleById(id);
		return employee;
	}
}
