package com.team1.iss.trial.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.repo.LARepository;
import com.team1.iss.trial.repo.ManagerRepository;
import com.team1.iss.trial.services.interfaces.IManagerService;

@Service
@Primary
public class ManagerServiceImpl extends EmployeeServiceImpl implements IManagerService {

//	@Autowired
//	LARepository laRepo;
//	
//	@Autowired
//	ManagerRepository mRepo;

	@Override
	public ArrayList<LA> findPendingApplications() {
		ArrayList<LA> applicationlistForApproval= (ArrayList<LA>)laRepo.getPendingLA();
		return applicationlistForApproval;
	}

	@Override
	public ArrayList<LA> findAllLeave() {
		ArrayList<LA> listofallLA=(ArrayList<LA>)laRepo.findAllLeave();
		return listofallLA;
	}

	@Override
	public LA findLAByID(Integer uid) {
		return laRepo.findLADetailsByUid(uid).get(0);
	}

	@Override
	public void saveLA(LA la) {
		laRepo.save(la);
	}
}
