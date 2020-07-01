package com.team1.iss.trial.services.interfaces;


import java.util.List;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;


public interface IEmployeeService {

	List<LA> findAllLeave();

	List<User> findAllUsers();

	float getAnnualApplicationBalance(LA la);

	float getMedicalApplicationBalance(LA la);

	float getCompensationApplicationBalance(LA la);
}
