package com.team1.iss.trial.services.interfaces;


import java.util.List;

import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;


public interface IEmployeeService {
	public List<LA> findAllLeave();
	public List<User> findAllUsers();
}
