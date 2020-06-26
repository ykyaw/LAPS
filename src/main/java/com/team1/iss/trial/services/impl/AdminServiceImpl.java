package com.team1.iss.trial.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.interfaces.IAdminService;




@Service
public class AdminServiceImpl extends EmployeeServiceImpl implements IAdminService {

	@Autowired
	UserRepository uRepo;

	@Override
	public ArrayList<User> findAll() {
	    ArrayList<User> list = (ArrayList<User>) uRepo.findAll();
		return list;
	}

	@Override
	public boolean saveUser(User u) {
		if(uRepo.save(u)!=null) {
			return true;
		}
		else return false;

	}

	@Override
	public ArrayList<String> findAllUsernames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
//		ArrayList<User> list = (ArrayList<User>) uRepo.findByUsername(username);
//		return list.get(0);
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}




}
