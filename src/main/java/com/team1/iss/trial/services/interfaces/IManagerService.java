package com.team1.iss.trial.services.interfaces;

import java.util.ArrayList;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.OverTime;

public interface IManagerService {

	public ArrayList<LA> findPendingApplications();
	public ArrayList<LA> findAllLeave();
	public LA findLAByID(Integer uid);
	public void saveLA(LA la);
	public void saveOverTime(OverTime ot);
	public ArrayList<OverTime> findClaims();
//	public ArrayList<LA> findEmployeeLeaveByEmployeeId(int uid);
}
