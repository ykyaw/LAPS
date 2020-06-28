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
	

	List<User> findByName(String s);
	
//	@Query("Select u.username from User u")
//	ArrayList<String> findAllUsernames();


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
	
	@Modifying
	@Transactional
	@Query("update User set manager_id=:managerId where uid=:uid")
	public void updateUserManager(@Param("managerId") int managerId, @Param("uid") int uid);
	

	@Query(value = "select manager_id from User where uid=:uid", nativeQuery=true)
	public void findUserManagerId(@Param("uid") int employeeId);
	

	@Query(value = "SELECT um.name FROM user ue, user um where ue.manager_id=um.uid and ue.uid=?1", nativeQuery=true)
	public String findUserManagerName(@Param("uid") int employeeId);
	

	@Modifying
	@Transactional
	@Query("update User set name=:name, enabled=:enabled, user_type=:userType, annual_leave_entitlement=:al, medical_leave_entitlement=:ml where uid=:uid")
	public void updateUser(@Param("name")String name, @Param("enabled")boolean enabled, @Param("userType")String userType, @Param("al")int al,@Param("ml")int ml, @Param("uid") int uid); 
	

	@Query(value= "select uid from user where uid = (select max(uid) from user)", nativeQuery=true)
	public int findlatestUID();
	
	@Modifying
	@Transactional
	@Query("update User set medical_leave_entitlement=:medicalleaveentitlement")
	public void updateAllMedicalLeave(@Param("medicalleaveentitlement") int medicalleaveentitlement);
	
	@Modifying
	@Transactional
	@Query("update User set annual_leave_entitlement = annual_leave_entitlement + :ALprof where user_type != 'ADMIN' ")
	public void updateProfAnnualLeave(@Param("ALprof") int ALprof);
	
	@Modifying
	@Transactional
	@Query("update User set annual_leave_entitlement = annual_leave_entitlement + :ALadmin where user_type LIKE 'ADMIN' ")
	public void updateAdminAnnualLeave(@Param("ALadmin") int ALadmin);
	
};








