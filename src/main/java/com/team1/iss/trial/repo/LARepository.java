package com.team1.iss.trial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.LA;

public interface LARepository extends JpaRepository<LA, Integer> {

	@Query(value = "SELECT a FROM LA a")
	public List<LA> findAllLeave();
	
//	@Query(value = "SELECT a FROM LA a where a.fromTime >= to_timestamp(, 'dd-mm-yyyy hh24:mi:ss') and a.toTime <= to_timestamp(, 'dd-mm-yyyy hh24:mi:ss')")
//	public List<LA> findAllLeaveByCurrentYear(long timestamp);//1593224802 current timestamp
	 
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.APPLIED)
	public List<LA> getAppliedLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.UPDATED)
	public List<LA> getUpdatedLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.DELETED)
	public List<LA> getDeletedLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.CANCELLED)
	public List<LA> getCancelledLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.REJECTED)
	public List<LA> getRejectedLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.APPROVED)
	public List<LA> getApprovedLA();
}
