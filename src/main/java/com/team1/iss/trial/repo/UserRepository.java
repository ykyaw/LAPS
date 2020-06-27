package com.team1.iss.trial.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.iss.trial.domain.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByName(String s);
	
//	@Query("Select u.username from User u")
//	ArrayList<String> findAllUsernames();

}
