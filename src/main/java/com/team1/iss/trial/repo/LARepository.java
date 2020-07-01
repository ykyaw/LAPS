package com.team1.iss.trial.repo;

import java.util.ArrayList;
import java.util.List;

import com.team1.iss.trial.common.utils.TimeUtil;
import org.springframework.data.jpa.repository.*;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.LA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/*
 * Author: YC
 * */
public interface LARepository extends JpaRepository<LA, Integer>, JpaSpecificationExecutor<LA> {

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
	List<LA> findAllLeave();
	
	@Query(value = "SELECT a FROM LA a where a.fromTime>=:currentYear and a.owner.uid=:ownerId")
	List<LA> findAllLeaveByOwnerId(@Param("ownerId") int ownerId,@Param("currentYear") long currentYear);//1593224802 current timestamp
	 
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.APPLIED)
	public List<LA> getAppliedLA();
	
	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.UPDATED)
	public List<LA> getUpdatedLA();

	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.DELETED)
	public List<LA> getDeletedLA();

	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.CANCELLED, nativeQuery = true)
	public List<LA> getCancelledLA();

	@Query(value = "SELECT a FROM LA a where a.status="+CommConstants.ApplicationStatus.REJECTED)
	public List<LA> getRejectedLA();

	@Query(value = "SELECT * FROM la  where status="+CommConstants.ApplicationStatus.APPROVED ,nativeQuery = true)
	public List<LA> getApprovedLA();

	@Query(value = "SELECT a FROM LA a, User u where a.owner.uid=u.uid AND u.manager.uid=?1 AND a.status in ('APPLIED','UPDATED') order by a.owner.uid")
	public List<LA> getPendingLA(int manageruid);
	
	@Query(value = "SELECT a from LA a where owner_id=?1 order by uid desc")
	public ArrayList<LA> findLAByOwnerId(int uid);

	@Query(value = "SELECT a FROM LA a where a.fromTime>=:currentYear and a.owner.uid=:ownerId and a.status='APPROVED' and a.type='ANNUAL_LEAVE'")
	List<LA> findAllApprovedAnnualLeaveByOwnerId(@Param("ownerId") int ownerId, @Param("currentYear") Long currentYear);

	@Query(value = "SELECT a FROM LA a where a.fromTime>=:currentYear and a.owner.uid=:ownerId and a.status='APPROVED' and a.type='MEDICAL_LEAVE'")
	List<LA> findAllApprovedMedicalLeaveByOwnerId(@Param("ownerId") int ownerId, @Param("currentYear") Long currentYear);

	@Query(value = "SELECT a FROM LA a where a.fromTime>=:currentYear and a.owner.uid=:ownerId and a.status='APPROVED' and a.type='COMPENSATION_LEAVE'")
	List<LA> findAllApprovedCompensationLeaveByOwnerId(@Param("ownerId") int ownerId, @Param("currentYear") Long currentYear);
}
