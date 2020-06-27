package com.team1.iss.trial.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OverTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	@Column
	private long startTime;
	@Column
	private long endTime;
	@ManyToOne
	@JoinColumn(name = "ownerId",insertable = false, updatable = false)
	private Employee owner;
	
	

	public OverTime(int uid, long startTime, long endTime, Employee owner) {
		super();
		this.uid = uid;
		this.startTime = startTime;
		this.endTime = endTime;
		this.owner = owner;
	}

	public OverTime() {
		super();
	}

	public OverTime(int uid) {
		super();
		this.uid = uid;
	}
	
	

	
	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	

}
