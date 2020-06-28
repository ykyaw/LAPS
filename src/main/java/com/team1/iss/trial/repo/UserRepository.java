package com.team1.iss.trial.repo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team1.iss.trial.domain.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByName(String s);
	
//	@Query("Select u.username from User u")
//	ArrayList<String> findAllUsernames();



@Modifying 
@Transactional
@Query ("update User set user_type=:userType where uid=:uid")
public void updateUserType(@Param("userType") String userType,@Param("uid") int uid);

}