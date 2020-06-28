package com.team1.iss.trial.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.UserForm;
import com.team1.iss.trial.repo.ManagerRepository;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.interfaces.IAdminService;




@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	UserRepository uRepo;
	

	@Override
	public boolean updateUserType(String userType, int uid) {
		uRepo.updateUserType(userType, uid);
		return true;
	}
	
	@Override
	public boolean updateUserManager(int uid, int managerId) {
		uRepo.updateUserManager(managerId, uid); 
		return true;
	}
	
	@Override
	public ArrayList<User> findAll() {
	    ArrayList<User> list = (ArrayList<User>) uRepo.findAll();
		return list;
	}
	
	@Override
	public ArrayList<UserForm> findAllwithManagerName() {
		ArrayList<User> list = (ArrayList<User>) uRepo.findAll();
		ArrayList<UserForm> listform = new ArrayList<>();
		for(User u : list) {
			UserForm uf = new UserForm();
			uf.setAnnualLeaveEntitlement(u.getAnnualLeaveEntitlement());
			uf.setEmail(u.getEmail());
			uf.setEnabled(u.isEnabled());
			uf.setMedicalLeaveEntitlement(u.getMedicalLeaveEntitlement());
			uf.setName(u.getName());
			uf.setUid(u.getUid());
			uf.setUserType(u.getUserType());
			uf.setManagerName(uRepo.findUserManagerName(u.getUid()));
			listform.add(uf);
		}
		return listform;
	}
	

	@Override
	public boolean saveUser(User u) {
		if(uRepo.save(u)!=null) {
			return true;
		}
		else return false;
	}
	
	@Override
	public String findUserManagerby(int uid) {
		return uRepo.findUserManagerName(uid);
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
