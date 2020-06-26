package com.team1.iss.trial.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.team1.iss.trial.common.CommConstants;

@Entity
@DiscriminatorValue(CommConstants.UserType.AMDIN)
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Admin(int uid, String name, byte[] photo, int annualLeaveEntitlement) {
		super(uid, name, photo, annualLeaveEntitlement);
		// TODO Auto-generated constructor stub
	}



	public Admin(int uid, String name, byte[] photo, String password, int annualLeaveEntitlement) {
		super(uid, name, photo, password, annualLeaveEntitlement);
		// TODO Auto-generated constructor stub
	}



	public Admin(int uid) {
		super(uid);
		// TODO Auto-generated constructor stub
	}

	
}
