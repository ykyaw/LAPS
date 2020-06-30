package com.team1.iss.trial.services.interfaces;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.OverTime;

import java.util.ArrayList;
import java.util.List;

public interface IOverTimeService {

    public ArrayList<OverTime> findAll();
    public void saveOt(OverTime ot);
    public void updateOt(OverTime ot);
    List<OverTime> findOtByOwnerId(int uid);
}
