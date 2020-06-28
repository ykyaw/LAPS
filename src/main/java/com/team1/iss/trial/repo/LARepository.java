package com.team1.iss.trial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.LA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/*
 * Author: YC
 * */
public interface LARepository extends JpaRepository<LA, Integer>{

    @Query("FROM LA WHERE uid = ?1")
    public List<LA> findLADetailsByUid(int uid);

    @Query("FROM LA WHERE owner_id = ?1")
    public List<LA> findLADetailsByOwnerId(int ownerid);

    @Query("FROM LA WHERE status = ?1")
    public List<LA> findLADetailsByStatus(String status);

//    @Modifying
//    @Transactional  //Spring transactional
//    @Query("update LA set LA.uid = ?1 where uid=LA.uid")
//    public void updateLA(@Param("la") LA la);

//    @Query("update LA set fromTime=:#{#la.fromTime},xxx=xxx where uid=:#{#la.uid}")
//    public void updateLA1(@Param("la") LA la);

//    @Query("update LA set LA.firstname = ?1, LA.lastname = ?2 where LA.uid = ?1")


//    void setUserInfoById(String firstname, String lastname, Integer userId);
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
