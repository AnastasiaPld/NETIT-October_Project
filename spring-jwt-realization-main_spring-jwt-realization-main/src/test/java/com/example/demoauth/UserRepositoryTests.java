package com.example.demoauth;

import static org.mockito.ArgumentMatchers.any;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demoauth.models.ERole;
import com.example.demoauth.models.Role;
import com.example.demoauth.models.User;
import com.example.demoauth.repository.UserRepository;
import com.example.demoauth.service.UserDetailsServiceImpl;
@SpringBootTest
public class UserRepositoryTests {

	@MockBean
	UserRepository userRepo;

	@MockBean
	UserDetailsServiceImpl userService;

	@Test
	public void testCreateUser() {
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(ERole.ROLE_ADMIN));
		User userTest = new User("userNameTest", "emailTest", "passwordTest", roles);
		Mockito.when(userRepo.save(any())).thenReturn(userTest);
		
		userRepo.save(userTest);
		
		Mockito.when(userRepo.findByEmail(any())).thenReturn(userTest);
		User userReturned = userRepo.findByEmail("emailTest");
		
		Assertions.assertNotNull(userReturned);
		Assertions.assertTrue(userTest.equals(userReturned));
	}
}
