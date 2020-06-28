package com.team1.iss.trial.services.impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.Admin;
import com.team1.iss.trial.domain.Employee;

import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.FormEditUser;
import com.team1.iss.trial.repo.ManagerRepository;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.interfaces.IAdminService;




@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	UserRepository uRepo;
	
	@Autowired
	ManagerRepository mRepo;
	

	@Override
	public boolean updateUserType(String userType, int uid) {
		uRepo.updateUserType(userType, uid);
		return true;
	}
	
	
	@Override
	public boolean updateUserManager(int managerId, int uid) {
		uRepo.updateUserManager(managerId, uid); 
		return true;
	}
	
	@Override
	public ArrayList<User> findAll() {
	    ArrayList<User> list = (ArrayList<User>) uRepo.findAll();
		return list;
	}
	
	@Override
	public FormEditUser editUser(int uid) {
		User u1 = uRepo.findById(uid).get();
		FormEditUser uf = new FormEditUser();		
		uf.setUid(u1.getUid());
		uf.setEmail(u1.getEmail());
		uf.setName(u1.getName());
		uf.setEnabled(u1.isEnabled());				
		uf.setUserType(u1.getUserType());
		uf.setAnnualLeaveEntitlement(u1.getAnnualLeaveEntitlement());
		uf.setMedicalLeaveEntitlement(u1.getMedicalLeaveEntitlement());
		return uf;
	}
	
	@Override
	public boolean convertuser(User user) {
		if(user.getUserType().equals(CommConstants.UserType.AMDIN))
		{
			User a1 = new Admin();
			a1.setAnnualLeaveEntitlement(user.getAnnualLeaveEntitlement());
			a1.setEmail(user.getEmail());
			a1.setMedicalLeaveEntitlement(user.getMedicalLeaveEntitlement());
			a1.setName(user.getName());
			a1.setPassword(user.getPassword());
			a1.setEnabled(user.isEnabled());
			uRepo.save(a1);
		} else if (user.getUserType().equals(CommConstants.UserType.EMPLOYEE))
		{
			User a1 = new Employee();
			a1.setAnnualLeaveEntitlement(user.getAnnualLeaveEntitlement());
			a1.setEmail(user.getEmail());
			a1.setMedicalLeaveEntitlement(user.getMedicalLeaveEntitlement());
			a1.setName(user.getName());
			a1.setPassword(user.getPassword());
			a1.setEnabled(user.isEnabled());
			uRepo.save(a1);
		} else {
			User a1 = new Manager();
			a1.setAnnualLeaveEntitlement(user.getAnnualLeaveEntitlement());
			a1.setEmail(user.getEmail());
			a1.setMedicalLeaveEntitlement(user.getMedicalLeaveEntitlement());
			a1.setName(user.getName());
			a1.setPassword(user.getPassword());
			a1.setEnabled(user.isEnabled());
			uRepo.save(a1);
		}
		return false;
	}
	
	@Override
	public boolean updateUser(FormEditUser fu) {
		uRepo.updateUser(fu.getName(), fu.isEnabled(), fu.getUserType(), fu.getAnnualLeaveEntitlement(), fu.getMedicalLeaveEntitlement(), fu.getUid());
		return false;
	}

	
	 @Override
	    public ArrayList<Manager> findAllManager() {
	        ArrayList<Manager> list = (ArrayList<Manager>) mRepo.findAll();
	        return list;
	    }
	 
		@Override
		public User findUserById(Integer id) {
			return uRepo.findById(id).get();
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


}

