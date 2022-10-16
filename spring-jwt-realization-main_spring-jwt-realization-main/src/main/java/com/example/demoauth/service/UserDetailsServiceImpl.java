package com.example.demoauth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoauth.DemoauthApplication;
import com.example.demoauth.models.Role;
import com.example.demoauth.models.User;
import com.example.demoauth.repository.RoleRepository;
import com.example.demoauth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(DemoauthApplication.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
	}

	public List<User> getAll() {
		List<User> result = userRepository.findAll();
		logger.info("IN getAll - {} users found", result.size());
		return result;
	}

	public User findById(Long id) {
		User result = userRepository.findById(id).orElse(null);

		if (result == null) {
			logger.warn("IN findById - no user found by id: {}", id);
			return null;
		}

		logger.info("IN findById - user: {} found by id: {}", result);
		return result;
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
		logger.info("IN delete - user with id: {} successfully deleted");
	}


	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
		logger.info("IN delete - user with id: {} successfully deleted");
	}

	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

}
