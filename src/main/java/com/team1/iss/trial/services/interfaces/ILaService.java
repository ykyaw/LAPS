package com.team1.iss.trial.services.interfaces;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;

import java.util.ArrayList;
import java.util.List;

/*
* Author: YC
* */
public interface ILaService {

    public ArrayList<LA> findAll();
    public LA getLaById(int id);
    public void saveLA(LA la);
    public void updateLA(LA la);
    public void deleteLA(int id);
    List<LA> findLaByOwnerId(int uid);
}
