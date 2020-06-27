package com.team1.iss.trial.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.team1.iss.trial.common.CommConstants;

@Entity
@DiscriminatorValue(CommConstants.UserType.EMPLOYEE)
public class Employee extends User{

	@ManyToOne
	@JoinColumn(name = "managerId",insertable = false, updatable = false)
	private Manager manager;
	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<LA> laList;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Employee(int uid, String name, byte[] photo, String email, int annualLeaveEntitlement, boolean enabled,
			List<OverTime> overtimeList,Manager manager,List<LA> laList) {
		super(uid, name, photo, email, annualLeaveEntitlement, enabled, overtimeList);
		this.manager = manager;
		this.laList = laList;
	}



	public Employee(int uid, String name, byte[] photo, String password, String email, int annualLeaveEntitlement,
			boolean enabled, List<OverTime> overtimeList,Manager manager,List<LA> laList) {
		super(uid, name, photo, password, email, annualLeaveEntitlement, enabled, overtimeList);
		this.manager = manager;
		this.laList = laList;
	}


	public Employee(int uid) {
		super(uid);
		// TODO Auto-generated constructor stub
	}


	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<LA> getLaList() {
		return laList;
	}

	public void setLaList(List<LA> laList) {
		this.laList = laList;
	}
	
	
	
	

}
