package com.team1.iss.trial.services.impl;

import com.team1.iss.trial.common.utils.TimeUtil;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.OverTime;
import com.team1.iss.trial.repo.OverTimeRepository;
import com.team1.iss.trial.services.interfaces.IOverTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: YC
 * */
@Service
public class OverTimeServiceImpl implements IOverTimeService {

	@Override
	public ArrayList<OverTime> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Autowired
    OverTimeRepository otrepo;
    
    //Save the OverTime
	@Override
	public void saveOt(OverTime ot) {
		otrepo.save(ot);
	}
	
	//Update the OverTime
	@Override
	public void updateOt(OverTime ot) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public List<OverTime> findOtByOwnerId(int uid) {
		List<OverTime> ots=otrepo.findByOwnerId(uid);
		return ots;
	}
}
