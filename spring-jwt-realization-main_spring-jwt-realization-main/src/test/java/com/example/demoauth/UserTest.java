package com.example.demoauth;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demoauth.models.ERole;
import com.example.demoauth.models.User;
import com.example.demoauth.repository.UserRepository;

@SpringBootTest
@AutoConfigureWebTestClient
public class UserTest {

	@Autowired
	UserRepository userRepo;
	
@AfterEach
public void cleanUp() {
	
	
}
	
    @Test
   public void testCreateUser() {
    	Set<String> roles = new HashSet<>();
		roles.add(ERole.ROLE_ADMIN.toString());
        User user = new User();
        user.setEmail("testEmail");
        user.setPassword("testPassword");
        user.setUsername("testUsername");
     
        JSONObject jo = new JSONObject(user);
        WebTestClient.bindToServer().baseUrl("http://localhost:8080/api").build().post()
            .uri("/auth/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(jo.toString())
            .exchange()
            .expectStatus().isOk();
  
        boolean ifExist= userRepo.existsByUsername("testUsername");
       if(ifExist) { 
        userRepo.delete(user);
       }
  
    }
    
    
}
