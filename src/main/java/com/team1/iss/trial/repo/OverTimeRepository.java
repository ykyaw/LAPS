package com.team1.iss.trial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.iss.trial.domain.OverTime;
import org.springframework.data.repository.query.Param;

public interface OverTimeRepository  extends JpaRepository<OverTime, Integer>{
	
	@Query(value = "SELECT a FROM OverTime a")
	public List<OverTime> findAllClaims();

	@Query("select o from OverTime o where o.owner.uid=:ownerId")
    List<OverTime> findByOwnerId(@Param("ownerId") int ownerId);

	@Query("select o from OverTime o where o.owner.uid=:ownerId and o.startTime>=:currentYear and o.status='APPROVED'")
    List<OverTime> findCurrentYearApprovedOverTimeByOwnerId(@Param("ownerId") int ownerId,@Param("currentYear") Long currentYear);
}
