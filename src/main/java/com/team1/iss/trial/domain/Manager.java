package com.team1.iss.trial.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.team1.iss.trial.common.CommConstants;

@Entity
@DiscriminatorValue(CommConstants.UserType.MANAGER)
public class Manager extends Employee{
	
	@OneToMany(mappedBy = "manager")
	private List<Employee> employees;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(int uid, String name, byte[] photo, int annualLeaveEntitlement, Manager manager, List<LA> laList,List<Employee> employees) {
		super(uid, name, photo, annualLeaveEntitlement, manager, laList);
		this.employees=employees;
	}

	public Manager(int uid, String name, byte[] photo, String password, int annualLeaveEntitlement, Manager manager,
			List<LA> laList,List<Employee> employees) {
		super(uid, name, photo, password, annualLeaveEntitlement, manager, laList);
		this.employees=employees;
	}


	public Manager(int uid) {
		super(uid);
		// TODO Auto-generated constructor stub
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	

}
