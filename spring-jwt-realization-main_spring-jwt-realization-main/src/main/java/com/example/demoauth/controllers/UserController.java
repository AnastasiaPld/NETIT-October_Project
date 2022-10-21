package com.example.demoauth.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoauth.DemoauthApplication;
import com.example.demoauth.models.Role;
import com.example.demoauth.models.User;
import com.example.demoauth.pojo.MessageResponse;
import com.example.demoauth.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	Logger logger = LoggerFactory.getLogger(DemoauthApplication.class);

	@Autowired
	UserDetailsServiceImpl userService;

	@GetMapping("/users")
	public List<User> getAllUsers(Model model) {
		List<User> all_users = userService.getAll();
		model.addAttribute("listUsers", all_users);
		return all_users;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		logger.trace("User is deleted");
		userService.delete(id);
		return ResponseEntity.ok(new MessageResponse("User is deleted"));

	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
		User user = userService.findById(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		User result = userService.findById(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.findById(id);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		userService.save(user);

		return "redirect:/users";
	}
	 
}
