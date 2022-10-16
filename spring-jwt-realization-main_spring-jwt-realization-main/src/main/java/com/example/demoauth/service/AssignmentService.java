package com.example.demoauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoauth.models.Assignment;
import com.example.demoauth.repository.AssignmentRepository;
@Service
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;

	public Optional<Assignment> getById(Long id) {

		return assignmentRepository.findById(id);
	}

	
	public List<Assignment> getAllData()   
	{  
	List<Assignment> assignment = new ArrayList<Assignment>();  
	assignmentRepository.findAll().forEach(ass -> assignment.add(ass));  
	return assignment;  
	}  
	
	public void saveOrUpdate(Assignment assignment)   
	{  
		assignmentRepository.save(assignment);  
	}  
	
	public void delete(Long id)   
	{  
		assignmentRepository.deleteById(id);  
	} 
	
	public Assignment update(Assignment assignment, Long id)   
	{  
		return assignmentRepository.save(assignment);  
	} 
}
