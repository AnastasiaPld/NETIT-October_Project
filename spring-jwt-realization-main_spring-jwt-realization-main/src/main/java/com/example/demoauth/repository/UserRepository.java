package com.example.demoauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoauth.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	User findByEmail(String email);

	@Modifying
	@Query(value = "DELETE FROM User WHERE email=:email")
	int deleteByEmail(@Param("email") String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	
}
