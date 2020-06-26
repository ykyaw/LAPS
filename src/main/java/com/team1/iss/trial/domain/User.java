package com.team1.iss.trial.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "user")
@DiscriminatorColumn(name = "userType",discriminatorType = DiscriminatorType.STRING, length = 10)
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	@Column
	private String name;
	@Column
	private byte[] photo;
	@Column
	private String password;
	@Column
	private int annualLeaveEntitlement;
	
	public User() {
		super();
	}
	
	public User(int uid) {
		super();
		this.uid = uid;
	}
	
	
	
	public User(int uid, String name, byte[] photo, String password, int annualLeaveEntitlement) {
		super();
		this.uid = uid;
		this.name = name;
		this.photo = photo;
		this.password = password;
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}

	
	public User(int uid, String name, byte[] photo, int annualLeaveEntitlement) {
		super();
		this.uid = uid;
		this.name = name;
		this.photo = photo;
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}
	
	

	public int getAnnualLeaveEntitlement() {
		return annualLeaveEntitlement;
	}

	public void setAnnualLeaveEntitlement(int annualLeaveEntitlement) {
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}

	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public byte[] getPhoto() {
		return photo;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
