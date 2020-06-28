package com.team1.iss.trial.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team1.iss.trial.domain.LA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Modifying
    @Transactional  //Spring transactional
    @Query("update LA set LA.uid = ?1 where uid=LA.uid")
    public void updateLA(@Param("la") LA la);

//    @Query("update LA set fromTime=:#{#la.fromTime},xxx=xxx where uid=:#{#la.uid}")
//    public void updateLA1(@Param("la") LA la);

//    @Query("update LA set LA.firstname = ?1, LA.lastname = ?2 where LA.uid = ?1")


//    void setUserInfoById(String firstname, String lastname, Integer userId);

}
