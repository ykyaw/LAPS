package com.team1.iss.trial.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.common.utils.TimeUtil;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.PublicHoliday;
import com.team1.iss.trial.repo.LARepository;
import com.team1.iss.trial.repo.PublicHolidayRepository;
import com.team1.iss.trial.services.interfaces.ILaService;

/*
 * Author: YC
 * */
@Service
public class LaServiceImpl implements ILaService {

    @Autowired
    LARepository larepo;

    @Autowired
    PublicHolidayRepository publicHolidayRepository;

    //Return all the LAs
    @Override
    public ArrayList<LA> findAll() {
        ArrayList<LA> list = (ArrayList<LA>) larepo.findAll();
        return list;
    }

    //Return Single LA
    @Override
    public LA getLaById(int id) {
        return larepo.findById(id).get();
    }

    //Save the LA
    @Override
    public void saveLA(LA la) {
        larepo.save(la);
    }

    //Update the LA
    @Override
    public void updateLA(LA la) {
//        larepo.updateLA(la);
//        ArrayList<LA> list = (ArrayList<LA>) larepo.findAll();
//        for (LA la_in : list) {
//            if (la_in.getUid() == la.getUid()) {
//                la_in.setStatus(la.getStatus());
//                la_in.setContact(la.getContact());
//                la_in.setReasons(la.getReasons());
//                la_in.setRejectReason(la.getRejectReason());
//                la_in.setOwner(la.getOwner());
//                la_in.setFromTime(la.getFromTime());
//                la_in.setToTime(la.getToTime());
//                la_in.setDissemination(la.getDissemination());
//                la_in.setType(la.getType());
//            }
//        }
    }

    //Remove the LA
    public void deleteLA(int id) {
        larepo.delete(larepo.findById(id).get());
    }

    @Override
    public List<LA> findLaByOwnerId(int uid) {
        List<LA> las = larepo.findAllLeaveByOwnerId(uid, TimeUtil.getYearStartTime(TimeUtil.getCurrentTimestamp()));
        for (LA la : las) {
            calculateApplicationDuration(la);
        }

        return las;
    }
    
    @Override
    public Page<LA> findLaByOwnerIdPageable(Pageable pageable, int uid) {
    	Page<LA> las = larepo.findAllLeaveByOwnerId(pageable, uid,TimeUtil.getYearStartTime(TimeUtil.getCurrentTimestamp()) );    	
        return las;
    }


    @Override
    public void calculateApplicationDuration(LA la) {
        long fromTime = la.getFromTime();
        long toTime = la.getToTime();
        float l = (toTime - fromTime) / (long)(1000 * 60 * 60 * 24);
        float duration =  ((toTime - fromTime) / (long) (1000 * 60 * 60 * 12))/2.0f;
        if (la.getType().equals(CommConstants.LeaveType.ANNUAL_LEAVE)) {
            if (duration <= 14) {
                List<PublicHoliday> holidays =
                        publicHolidayRepository.findCurrentYearPublicHolidays(TimeUtil.getYearStartTime(TimeUtil.getCurrentTimestamp()))
                        .stream()
                        .filter(holiday->{
                            return !TimeUtil.getWeek(holiday.getDay()).equals("Saturday")&&!TimeUtil.getWeek(holiday.getDay()).equals("Sunday");
                        })
                        .collect(Collectors.toList());
                for (long i = fromTime; i <= toTime; i += 1000 * 60 * 60 * 24) {
                    if (TimeUtil.getWeek(i).equals("Saturday") || TimeUtil.getWeek(i).equals("Sunday")) {
                        duration--;
                    }
                    for (PublicHoliday holiday : holidays) {
                        if(holiday.getDay()==fromTime){
                            duration--;
                        }
                    }

                }
            }
        }
        la.setDuration(duration);
    }
}
