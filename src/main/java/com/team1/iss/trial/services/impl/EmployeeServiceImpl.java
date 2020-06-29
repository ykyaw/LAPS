package com.team1.iss.trial.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.repo.EmployeeRepository;
import com.team1.iss.trial.repo.LARepository;
import com.team1.iss.trial.services.interfaces.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	 @Autowired
	 LARepository laRepo;

	 @Autowired
	 UserRepository userRepository;

	 
	 
	 @Override
	 public List<LA> findAllLeave() {
			return (List<LA>) laRepo.findAllLeave();
	 }
	
	 public List<LA> getAppliedLA() {
			return (List<LA>) laRepo.getAppliedLA();
	 }
	 
	 public List<LA> getUpdatedLA() {
			return (List<LA>) laRepo.getUpdatedLA();
	 }
	 
	 public List<LA> getDeletedLA() {
			return (List<LA>) laRepo.getDeletedLA();
	 }
	 
	 public List<LA> getCancelledLA() {
			return (List<LA>) laRepo.getCancelledLA();
	 }
	 
	 public List<LA> getRejectedLA() {
			return (List<LA>) laRepo.getRejectedLA();
	 }
	 
	 public List<LA> getApprovedLA() {
			return (List<LA>) laRepo.getApprovedLA();
	 }
	 
	 @Autowired
	 EmployeeRepository eRepo;

	 @Override
	 public List<User> findAllUsers() {
		 List<User> users = userRepository.findAll();
		 return users;
	 }

}
