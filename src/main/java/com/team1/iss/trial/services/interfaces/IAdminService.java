package com.team1.iss.trial.services.interfaces;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.UserForm;



public interface IAdminService {
	
	public boolean updateUserType(String userType, int uid);
	public boolean updateUserManager(int managerId, int uid);
	public String findUserManagerby(int uid);
	public ArrayList<UserForm> findAllwithManagerName();
	
	public boolean saveNewUser(User user);
	
	public ArrayList<User> findAll();
	public boolean saveUser(User u);
	public ArrayList<String> findAllUsernames();
	public User findUserByUsername(String username);
	public User findUserById(Integer id);
	public ArrayList<Manager> findAllManager();
	public int findlatestUID();

	public boolean updateAllMedicalLeave(int medical_leave_entitlement);
	public boolean updateProfAnnualLeave(int profAL);
	public boolean updateAdminAnnualLeave(int adminAL);
	
}

	

