package com.example.demoauth.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoauth.models.Assignment;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.service.AssignmentService;

@ControllerAdvice 
@RestController
@RequestMapping("/api/employee/data")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentController {

	@Autowired
	AssignmentService assignmentService;

	@GetMapping("/all/assignment")
	public List<Assignment> getAllemployeesAssignment(Model model) {
		List<Assignment> all_assignment = assignmentService.getAllData();
		model.addAttribute("listAssignment", all_assignment);
		return all_assignment;
	}

	@DeleteMapping(value = "/{id}/assignment")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		assignmentService.delete(id);
		return ResponseEntity.ok(new MessageResponse("Employee assignment is deleted"));

	}

	@GetMapping(value = "/{id}/assignment")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<Assignment> getAssignmentById(@PathVariable(name = "id") Long id) {
		Optional<Assignment> assignment = assignmentService.getById(id);
		return assignment;
	}

	@PostMapping(value = "/edit/assignment")
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Assignment addData(@RequestBody Assignment assignment) {
		assignmentService.saveOrUpdate(assignment);
		return assignment;
	}

	
	@ExceptionHandler(RuntimeException.class)
	@PatchMapping("/assignment/{id}")
	public ResponseEntity<?> updateAssignment( @PathVariable(value = "id") Long assignmentId,
			 @RequestBody Assignment request, Model model) throws ResourceNotFoundException {
		Assignment assignment = assignmentService.getById(assignmentId)
				.orElseThrow(() -> new RuntimeException("Assignment not found: " + assignmentId));
		
		if (request.getId() == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No id of Assignment is entered!"));
		}

		if (request.getStatus() != null) {
			assignment.setStatus(request.getStatus());
		}
		if (request.getTask() != null) {
			assignment.setTask(request.getTask());
		}

		final Assignment updateAssignment = assignmentService.update(assignment, assignmentId);

		return ResponseEntity.ok(new MessageResponse("Assignment is updated!" + updateAssignment.toString()));

	}
}
