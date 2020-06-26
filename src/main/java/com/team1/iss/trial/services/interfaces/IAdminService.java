package com.team1.iss.trial.services.interfaces;

import java.util.ArrayList;

import com.team1.iss.trial.domain.User;



public interface IAdminService {
	
	public ArrayList<User> findAll();
	public boolean saveUser(User u);
	public ArrayList<String> findAllUsernames();
	public User findUserByUsername(String username);
	public User findUserById(Integer id);

}
