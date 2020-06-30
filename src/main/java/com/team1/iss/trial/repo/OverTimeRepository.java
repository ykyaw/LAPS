package com.team1.iss.trial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team1.iss.trial.domain.OverTime;

public interface OverTimeRepository  extends JpaRepository<OverTime, Integer>{
	
	@Query(value = "SELECT a FROM OverTime a")
	public List<OverTime> findAllClaims();
}
