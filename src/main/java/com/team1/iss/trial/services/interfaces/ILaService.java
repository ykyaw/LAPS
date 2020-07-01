package com.team1.iss.trial.services.interfaces;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;

import java.util.ArrayList;
import java.util.List;

/*
* Author: YC
* */
public interface ILaService {

     ArrayList<LA> findAll();

     LA getLaById(int id);

     void saveLA(LA la);

     void updateLA(LA la);

     void deleteLA(int id);

    List<LA> findLaByOwnerId(int uid);

    void calculateApplicationDuration(LA la);
}
