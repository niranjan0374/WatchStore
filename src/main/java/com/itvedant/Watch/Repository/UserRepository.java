package com.itvedant.Watch.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.itvedant.Watch.Entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@RestResource(exported = false)
	Optional<User> findByEmail(String email);
	
	@RestResource(exported = true )
	<S extends User> S save(S entity);
}
