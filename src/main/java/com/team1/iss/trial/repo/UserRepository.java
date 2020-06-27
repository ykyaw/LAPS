package com.team1.iss.trial.repo;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
//	@Modifying
//	@Transactional
//	@Query("update user set user_type = :#{#user.userType} "
//			+ ", annual_leave_entitlement=:#{#user.annualLeaveEntitlement} "
//			+ ", email=:#{#user.email} "
//			+ ", enabled=#{#user.enabled} "
//			+ ", medical_leave_entitlement=:#{#user.medicalLeaveEntitlement} "
//			+ ", name=:#{#user.name} "
//			+ ", password=:#{#user.password} "
//			+ ", photo=:#{#user.photo} "
//			+ ",manager_id=:#{#user.manager.uid} "
//			+ "where uid=:#{#user.uid}")
//	public void updateUserType(@Param("user") User user);
	

	@Modifying
	@Transactional
	@Query("update User set user_type=:userType where uid=:uid")
	public void updateUserType(@Param("userType") String userType,@Param("uid") int uid);

}
